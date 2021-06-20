package lapr.project.controller.task;

import lapr.project.data.PlanningDB;
import lapr.project.model.DeliveryPlanning;
import lapr.project.model.VehicleType;
import lapr.project.ui.Print;

import java.io.IOException;
import java.util.*;

/**
 * The type Order back pharmacy task.
 */
public class OrderBackPharmacyTask {

    private final PlanningDB db;
    private final DeliveryPlanning plan;

    private Boolean isTrue;

    /**
     * Gets true.
     *
     * @return the true
     */
    public Boolean getisTrue() {
        return isTrue;
    }

    /**
     * Sets true.
     *
     * @param aTrue the a true
     */
    public void setisTrue(Boolean aTrue) {
        isTrue = aTrue;
    }


    /**
     * Instantiates a new Order back pharmacy task.
     *
     * @param db the db
     * @param dp the dp
     */
    public OrderBackPharmacyTask(PlanningDB db, DeliveryPlanning dp) {
        this.db = db;
        this.plan = dp;
    }

    /**
     * Run boolean.
     *
     * @param productsNoStock the products no stock
     * @param pharmacyID      the pharmacy id
     * @param orderID         the order id
     * @return the boolean
     */
    public boolean run(Map<String, Integer> productsNoStock, int pharmacyID, int orderID) throws IOException {
        setisTrue(null);

        String currentCords = db.getCoordinatesByPharmacyID(pharmacyID);

        float Totalpayload = db.getTotalPayloadByPart(productsNoStock, pharmacyID);

        Map<Integer, String> allFarmacias = db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.SCOOTER);

        int pharmacyID2 = DeliveryPlanning.getIdClosestByOrigDest(currentCords, allFarmacias);

        String pharmacyCords = db.getCoordinatesByPharmacyID(pharmacyID2);

        LinkedList<String> cords = new LinkedList<>();
        cords.add(pharmacyCords);

        int courierNIF = db.getAvailableCourier(pharmacyID);

        int scooterID = db.getAvailableScooter(courierNIF, pharmacyID, Totalpayload);

        LinkedList<String> remainingPath = new LinkedList<>();

        float actualBatteryWH = db.getActualBatteryVehicle(scooterID, pharmacyID);

        List<Integer> newlist = new ArrayList<>();
        newlist.add(orderID);

        Map<String, Float> pesosPorEncomenda = new HashMap<>();

        LinkedList<String> path = (LinkedList<String>) plan.mostEficientPath(new LinkedList<>(cords), currentCords, VehicleType.SCOOTER, actualBatteryWH,  Totalpayload, pesosPorEncomenda,allFarmacias, remainingPath);
        //float distance = plan.pathWeight(path);

        //float time = DeliveryPlanning.calculateSpentTime(distance, VehicleType.SCOOTER);

        float energyCost = DeliveryPlanning.calculateEnergyCost(path, Totalpayload, VehicleType.SCOOTER, pesosPorEncomenda, new HashMap<>());

        boolean worked1 = db.changeStates(scooterID, courierNIF, pharmacyID);

        boolean worked2 = db.changeStock(productsNoStock, pharmacyID2);

        boolean worked3 = db.takeVehicleEnergy(scooterID, energyCost, pharmacyID);

        setisTrue(worked1 && worked2 && worked3);

        ChangeStatesTask task = new ChangeStatesTask(scooterID, courierNIF, pharmacyID);

        Print.sendOrderBackNotice(orderID, courierNIF, pharmacyID, pharmacyID2, Totalpayload, productsNoStock);

        db.createPharmacyOrder(orderID, pharmacyID);

        return OrderBackPharmacyTask.changeStates(task);
    }

    /**
     * Change states boolean.
     *
     * @param task the task
     * @return the boolean
     */
    public static boolean changeStates(ChangeStatesTask task) {
        return task.run();
    }
}