package lapr.project.model;

import lapr.project.controller.task.ChangeStatesTask;
import lapr.project.controller.task.DroneDeliveryPlanningTask;
import lapr.project.controller.task.ScooterDeliveryPlanningTask;
import lapr.project.data.PlanningDB;

import java.io.IOException;
import java.util.LinkedList;


/**
 * The type Stop for charging.
 */
public class StopForCharging  {

    private final LinkedList<String> remainingPath;
    private final int gastoEnergeticoRestante;
    private final float tempoRestante;
    private final float distanciaRestante;
    private final int courierNIF;
    private final Integer scooterID;
    private final int pharmacyID;
    private final int idDelivery;
    private final PlanningDB db;
    private final Integer droneID;

    /**
     * Instantiates a new Stop for charging.
     *
     * @param remainingPath           the remaining path
     * @param gastoEnergeticoRestante the gasto energetico restante
     * @param tempoRestante           the tempo restante
     * @param distanciaRestante       the distancia restante
     * @param courierNIF              the courier nif
     * @param scooterID               the scooter id
     * @param pharmacyID              the pharmacy id
     * @param idDelivery              the id delivery
     * @param db                      the db
     */
    public StopForCharging(LinkedList<String> remainingPath, float gastoEnergeticoRestante, float tempoRestante, float distanciaRestante, int courierNIF, int scooterID, int pharmacyID, int idDelivery, PlanningDB db) {
        this.remainingPath = remainingPath;
        this.gastoEnergeticoRestante = (int) gastoEnergeticoRestante+1;
        this.tempoRestante = tempoRestante;
        this.distanciaRestante = distanciaRestante;
        this.courierNIF = courierNIF;
        this.scooterID = scooterID;
        this.pharmacyID = pharmacyID;
        this.idDelivery = idDelivery;
        this.db = db;
        droneID = -1;
    }

    /**
     * Instantiates a new Stop for charging.
     *
     * @param remainingPath           the remaining path
     * @param gastoEnergeticoRestante the gasto energetico restante
     * @param tempoRestante           the tempo restante
     * @param distanciaRestante       the distancia restante
     * @param droneID                 the drone id
     * @param pharmacyID              the pharmacy id
     * @param idDelivery              the id delivery
     * @param db                      the db
     */
    public StopForCharging(LinkedList<String> remainingPath, float gastoEnergeticoRestante, float tempoRestante, float distanciaRestante, int droneID, int pharmacyID, int idDelivery, PlanningDB db) {
        this.remainingPath = remainingPath;
        this.gastoEnergeticoRestante = (int) gastoEnergeticoRestante+1;
        this.tempoRestante = tempoRestante;
        this.distanciaRestante = distanciaRestante;
        this.droneID = droneID;
        scooterID = -1;
        courierNIF = -1;
        this.pharmacyID = pharmacyID;
        this.idDelivery = idDelivery;
        this.db = db;
    }

    /**
     * Run boolean.
     *
     * @param cst the cst
     * @return the boolean
     */
    public boolean run(ChangeStatesTask cst) {

        boolean flag = false;
        Integer chargingStationID;
        if (courierNIF == -1) {
            chargingStationID = db.getAvailableChargingStation(pharmacyID, VehicleType.DRONE);
            if (chargingStationID != 0) {
                try {
                    //db.putPlaceUnavailable
                    float actualBatteryWH = db.getActualBatteryVehicle(droneID, pharmacyID);
                    cst.parkingAndCharginDrones(droneID, pharmacyID, (int) (gastoEnergeticoRestante- actualBatteryWH) +6);
                    //indisponivel
                    flag = true;
                } catch(IOException e) {
                    //e.printStackTrace();
                }
            }
        } else {
            chargingStationID = db.getAvailableChargingStation(pharmacyID, VehicleType.SCOOTER);
            if (chargingStationID != 0) {
                try {
                    //db.putPlaceUnavailable
                    float actualBatteryWH = db.getActualBatteryVehicle(scooterID, pharmacyID);
                    cst.parkingAndChargingScooter(scooterID, pharmacyID, (int) (gastoEnergeticoRestante-actualBatteryWH)+6);
                    //indisponivel
                    flag = true;
                } catch(IOException e) {
                    //e.printStackTrace();
                }
            }
        }

        voltarAOrigem();

        return flag;
    }

    /**
     * Voltar a origem boolean.
     *
     * @return the boolean
     */
    public boolean voltarAOrigem() {


        db.updateDelivery(gastoEnergeticoRestante, tempoRestante, distanciaRestante, idDelivery);



        if (scooterID != -1) {
            db.takeVehicleEnergy(scooterID, gastoEnergeticoRestante, pharmacyID);
            ChangeStatesTask task = new ChangeStatesTask(scooterID, courierNIF, pharmacyID);
            return ScooterDeliveryPlanningTask.changeStates(task);

        } else {
            db.takeVehicleEnergy(droneID, gastoEnergeticoRestante, pharmacyID);
            ChangeStatesTask task = new ChangeStatesTask(droneID, db, pharmacyID);
            return DroneDeliveryPlanningTask.changeStateDrone(task);
        }


    }
}
