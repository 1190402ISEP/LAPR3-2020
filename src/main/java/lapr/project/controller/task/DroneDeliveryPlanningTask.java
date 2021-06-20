package lapr.project.controller.task;


import lapr.project.data.PlanningDB;
import lapr.project.model.DeliveryPlanning;
import lapr.project.model.StopForCharging;
import lapr.project.model.VehicleType;
import lapr.project.model.utils.Utils;
import lapr.project.ui.Logger;
import lapr.project.ui.Print;

import java.util.*;

/**
 * The type Drone delivery planning task.
 */
public class DroneDeliveryPlanningTask {


    /**
     * Plan boolean.
     *
     * @param pharmacyOrders the pharmacy orders
     * @param pharmacyID     the pharmacy id
     * @param cords          the cords
     * @param pharmacyCords  the pharmacy cords
     * @param payload        the payload
     * @param db             the db
     * @param plan           the plan
     * @return the boolean
     */
    public boolean plan(List<Integer> pharmacyOrders, int pharmacyID, List<String> cords, String pharmacyCords, float payload, PlanningDB db, DeliveryPlanning plan) throws Exception {


        Integer droneID = db.getAvailableDrone(pharmacyID, payload);
        if (droneID == null) {
            throw new Exception("There are no drones available at the pharmacy " + pharmacyID);
        }

        float actualBatteryWH = db.getActualBatteryVehicle(droneID, pharmacyID);

        Map<Integer, String> allFarmacias = db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.DRONE);

        LinkedList<String> remainingPath = new LinkedList<>();

        Map<String, Float> pesosPorEncomenda = db.getPesosPorEncomenda(pharmacyOrders);
        List<String> shortestPathDrone = plan.shortestPath(VehicleType.DRONE, new LinkedList<>(cords), pharmacyCords, pharmacyCords);
        List<String> mostEficientPathDrone = plan.mostEficientPath(new LinkedList<>(cords), pharmacyCords, VehicleType.DRONE, actualBatteryWH, payload, new HashMap<>(pesosPorEncomenda), allFarmacias, remainingPath);
        if (mostEficientPathDrone == null) {
            Logger.log("It is impossible to deliver with the drones available.");
            return false;
        }
        float distanceDrone = plan.pathWeight(mostEficientPathDrone);

        Map<String[], Float> costs = new HashMap<>();
        float energyCost = DeliveryPlanning.calculateEnergyCost(mostEficientPathDrone, payload, VehicleType.DRONE, pesosPorEncomenda, costs);
        float remainingPayload = DeliveryPlanning.getActualPayload();
        float time = DeliveryPlanning.getSpentTimeLastDronePath();

        boolean[] pass = new boolean[1];
        pass[0] = true;

        int idDelivey = finalSteps(db, droneID, pharmacyID, pharmacyOrders, distanceDrone, time, energyCost, pass);
        String outputFileName = Utils.getDateUnderscore() + "-DroneDelivery#" + idDelivey;

        Print.printFileDelivery((LinkedList<String>) mostEficientPathDrone, (LinkedList<String>) shortestPathDrone, distanceDrone, time, energyCost, idDelivey, outputFileName, actualBatteryWH, payload, droneID, 0, pharmacyCords, costs);

        if (!remainingPath.isEmpty())
            pass[0] = tratarRemainingPath(remainingPath, droneID, pharmacyID, plan, pesosPorEncomenda, remainingPayload, idDelivey, db, outputFileName);
        Print.printPhisics(outputFileName, VehicleType.DRONE); //da print a dados fisicos dos calculos
        db.updateOrderStateToDelivered(idDelivey); // dar update as encomendas para entregue
        return pass[0];

    }

    public static boolean tratarRemainingPath(LinkedList<String> remaining, int droneID, int pharmacyID, DeliveryPlanning plan, Map<String, Float> pesosPorEncomenda, float remainingPayload, int idDelivery, PlanningDB db, String outputFileName) {
        String actualFarmacia = remaining.removeFirst();
        String startingFarmacia = remaining.removeLast();

        LinkedList<String> completeRemainingPath = (LinkedList<String>) plan.mostEficientPath(VehicleType.DRONE, remaining, actualFarmacia, startingFarmacia, remainingPayload, new HashMap<>(pesosPorEncomenda));
        Map<String[], Float> costs2 = new HashMap<>();
        float gastoEnergeticoRestante = DeliveryPlanning.calculateEnergyCost(completeRemainingPath, remainingPayload, VehicleType.DRONE, pesosPorEncomenda, costs2);
        float distanciaRestante = plan.pathWeight(completeRemainingPath);
        float tempoRestante = DeliveryPlanning.getSpentTimeLastDronePath();

        boolean flag = stopForCharging(new StopForCharging(completeRemainingPath, gastoEnergeticoRestante, tempoRestante, distanciaRestante, droneID, pharmacyID, idDelivery, db));

        if (flag) {
            Print.printRemainingDeliveryPath(outputFileName, actualFarmacia, completeRemainingPath, gastoEnergeticoRestante, distanciaRestante, tempoRestante, VehicleType.DRONE, costs2);

        }

        return flag;

    }

    public static boolean stopForCharging(StopForCharging stop) {
        return stop.run(new ChangeStatesTask(new PlanningDB()));

    }

    /**
     * Final steps int.
     *
     * @param db             the db
     * @param droneID        the drone id
     * @param pharmacyID     the pharmacy id
     * @param pharmacyOrders the pharmacy orders
     * @param distanceDrone  the distance drone
     * @param time           the time
     * @param energyCost     the energy cost
     * @param pass           the pass
     * @return the int
     */
    public int finalSteps(PlanningDB db, int droneID, int pharmacyID, List<Integer> pharmacyOrders, float distanceDrone, float time, float energyCost, boolean[] pass) {
        db.changeStates(droneID, 0, pharmacyID);

        Integer idDelivery = db.insertDelivery(0, droneID, pharmacyID, distanceDrone, time, energyCost);


        db.takeVehicleEnergy(droneID, energyCost, pharmacyID);

        db.updateOrdersDeliveryID(pharmacyOrders, idDelivery);

        ChangeStatesTask task = new ChangeStatesTask(droneID, db, pharmacyID);

        pass[0] = changeStateDrone(task);


        return idDelivery;
    }

    /**
     * Change state drone boolean.
     *
     * @param task the task
     * @return the boolean
     */
    public static boolean changeStateDrone(ChangeStatesTask task) {
        return task.run();
    }
}
