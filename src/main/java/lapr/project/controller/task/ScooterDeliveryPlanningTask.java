package lapr.project.controller.task;

import lapr.project.data.EmailHandler;
import lapr.project.data.PlanningDB;
import lapr.project.model.DeliveryPlanning;
import lapr.project.model.OrdersQueue;
import lapr.project.model.StopForCharging;
import lapr.project.model.VehicleType;
import lapr.project.ui.Logger;
import lapr.project.model.utils.Utils;
import lapr.project.ui.Print;


import java.io.IOException;
import java.util.*;

/**
 * The type Scooter delivery planning task.
 */
public class ScooterDeliveryPlanningTask {

    private final PlanningDB db;
    private final DeliveryPlanning plan;
    private Boolean isTrue = false;

    /**
     * Instantiates a new Scooter delivery planning task.
     *
     * @param db   the db
     * @param plan the plan
     */
    public ScooterDeliveryPlanningTask(PlanningDB db, DeliveryPlanning plan) {
        this.db = db;
        this.plan = plan;
    }

    /**
     * Instantiates a new Scooter delivery planning task.
     */
    public ScooterDeliveryPlanningTask() {
        this.db = new PlanningDB();
        this.plan = new DeliveryPlanning();
    }

    /**
     * Run.
     */
    public void run(VehicleType type) throws Exception {
        Map<Integer, List<Integer>> orders = OrdersQueue.getOrders();

        if (orders.keySet().isEmpty()) {
            Logger.log("There are no orders to deliver!");
            setIsTrue(false);
        }

        for (Integer pharmacyID : orders.keySet()) {
            List<Integer> pharmacyOrders = orders.get(pharmacyID);

            sendEmailInformingDelivery(pharmacyOrders, db); //mandar email

            if (pharmacyOrders == null || pharmacyOrders.isEmpty()) {
                continue;
            }
            List<String> cords = db.getCordsByOrders(pharmacyOrders);

            Map<String, Float> pesosPorEncomenda = db.getPesosPorEncomenda(pharmacyOrders);

            float payload = DeliveryPlanning.getTotalPayload(pesosPorEncomenda);


            String pharmacyCords = db.getCoordinatesByPharmacyID(pharmacyID);


            if (type.equals(VehicleType.DRONE)) {
                DroneDeliveryPlanningTask droneTask = new DroneDeliveryPlanningTask();
                setIsTrue(droneTask.plan(pharmacyOrders, pharmacyID, cords, pharmacyCords, payload, db, plan));
                continue;
            }


            int courierNIF = db.getAvailableCourier(pharmacyID);

            if (courierNIF == 0) {
                DroneDeliveryPlanningTask droneTask = new DroneDeliveryPlanningTask();
                Logger.log("Courier unavailable, attempted drone delivery ... ");
                setIsTrue(droneTask.plan(pharmacyOrders, pharmacyID, cords, pharmacyCords, payload, db, plan));
                continue;
            }

            int scooterID = db.getAvailableScooter(courierNIF, pharmacyID, payload);
            if (scooterID == 0) {
                DroneDeliveryPlanningTask droneTask = new DroneDeliveryPlanningTask();
                Logger.log("Scooter unavailable, attempted drone delivery ... ");
                setIsTrue(droneTask.plan(pharmacyOrders, pharmacyID, cords, pharmacyCords, payload, db, plan));
                continue;
            }


            float actualBatteryWH = db.getActualBatteryVehicle(scooterID, pharmacyID);


            Map<Integer, String> allFarmacias = db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.SCOOTER);


            LinkedList<String> remainingPath = new LinkedList<>();


            LinkedList<String> path = (LinkedList<String>) plan.shortestPath(VehicleType.SCOOTER, new LinkedList<>(cords), pharmacyCords, pharmacyCords);
            LinkedList<String> mostEficiente = null;
            if (path != null) {
                mostEficiente = (LinkedList<String>) plan.mostEficientPath(new LinkedList<>(cords), pharmacyCords, VehicleType.SCOOTER, actualBatteryWH, payload, new HashMap<>(pesosPorEncomenda), allFarmacias, remainingPath);

            }

            if (mostEficiente == null || mostEficiente.isEmpty()) {
                DroneDeliveryPlanningTask droneTask = new DroneDeliveryPlanningTask();
                Logger.log("It is impossible to deliver with the scooters available. Attempt to use drones ... ");
                setIsTrue(droneTask.plan(pharmacyOrders, pharmacyID, cords, pharmacyCords, payload, db, plan));
                continue;
            }

            float distance = plan.pathWeight(mostEficiente);

            float time = DeliveryPlanning.calculateSpentTime(distance, DeliveryPlanning.getVelocidadeScooter()); //em horas

            Map<String[], Float> costs = new HashMap<>();

            float energyCost = DeliveryPlanning.calculateEnergyCost(mostEficiente, payload, VehicleType.SCOOTER, pesosPorEncomenda, costs);

            float ramainingPayload = DeliveryPlanning.getActualPayload();

            boolean changestates = db.changeStates(scooterID, courierNIF, pharmacyID);

            Integer deliveryID = db.insertDelivery(courierNIF, scooterID, pharmacyID, distance, time, energyCost);

            String outputFileName = Utils.getDateUnderscore() + "-ScooterDelivery#" + deliveryID;

            Print.printFileDelivery(mostEficiente, path, distance, time, energyCost, deliveryID, outputFileName, actualBatteryWH, payload, scooterID, courierNIF, pharmacyCords, costs);

            db.takeVehicleEnergy(scooterID, energyCost, pharmacyID);

            boolean update = db.updateOrdersDeliveryID(pharmacyOrders, deliveryID);

            if (!remainingPath.isEmpty()) {
                String actualFarmacia = remainingPath.removeFirst();
                String startingFarmacia = remainingPath.removeLast();

                LinkedList<String> fullRemainingPath = (LinkedList<String>) plan.mostEficientPath(VehicleType.SCOOTER, remainingPath, actualFarmacia, startingFarmacia, ramainingPayload, new HashMap<>(pesosPorEncomenda));
                Map<String[], Float> costs2 = new HashMap<>();
                float gastoEnergeticoRestante = DeliveryPlanning.calculateEnergyCost(fullRemainingPath, ramainingPayload, VehicleType.SCOOTER, pesosPorEncomenda, costs2);
                float distanciaRestante = plan.pathWeight(fullRemainingPath);
                float tempoRestante = DeliveryPlanning.calculateSpentTime(distanciaRestante, DeliveryPlanning.getVelocidadeScooter());

                Print.printRemainingDeliveryPath(outputFileName, actualFarmacia, fullRemainingPath, gastoEnergeticoRestante, distanciaRestante, tempoRestante, VehicleType.SCOOTER, costs2);

                StopForCharging stop = new StopForCharging(fullRemainingPath, gastoEnergeticoRestante, tempoRestante, distanciaRestante, courierNIF, scooterID, pharmacyID, deliveryID, db);
                stop.run(new ChangeStatesTask(new PlanningDB()));


            } else {
                ChangeStatesTask task = new ChangeStatesTask(scooterID, courierNIF, pharmacyID);
                boolean timerYes = ScooterDeliveryPlanningTask.changeStates(task);


                setIsTrue(changestates && update && timerYes);

            }

            Print.printPhisics(outputFileName, VehicleType.SCOOTER);


            db.updateOrderStateToDelivered(deliveryID);


        }


        OrdersQueue.clearQueue();

    }

    public static boolean sendEmailInformingDelivery(List<Integer> pharmacyOrders, PlanningDB db) {


        EmailHandler eh = null;
        try {
            eh = new EmailHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int orderID : pharmacyOrders) {

            String destinatario = db.getClientEmailByOrder(orderID);

            eh.sendEmail(destinatario, "Delivery for order #" + orderID, "Your order with the ID-" + orderID + " is on the way.");

        }


        return true;
    }


    /**
     * Change states boolean.
     *
     * @param task the task to perform
     * @return the boolean
     */
    public static boolean changeStates(ChangeStatesTask task) {
        return task.run();
    }

    /**
     * Gets is true.
     *
     * @return the is true
     */
    public Boolean getIsTrue() {
        return isTrue;
    }

    /**
     * Sets is true.
     *
     * @param isTrue the is true
     */
    public void setIsTrue(Boolean isTrue) {
        this.isTrue = isTrue;
    }
}
