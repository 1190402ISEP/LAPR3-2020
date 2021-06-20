package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.model.Drone;
import lapr.project.ui.Logger;

import java.util.List;

/**
 * The type Drone controller.
 */
public class DroneController {
    /**
     * Drone DB
     */
    private final DroneDB droneDB;


    /**
     * Instantiates a new Drone controller.
     */
    public DroneController() {
        droneDB = new DroneDB();

    }

    /**
     * Instantiates a new Drone controller.
     *
     * @param copy the copy
     */
    public DroneController(DroneDB copy) {
        droneDB = copy;
    }


    /**
     * Insert drone drone.
     *
     * @param batteryCapacity the battery capacity
     * @param maxPayload      the max payload
     * @param idPh            the id ph
     * @return the drone
     */
    public Drone insertDrone(float batteryCapacity, double maxPayload, int idPh, int battery) {
        Drone drone = new Drone(batteryCapacity, maxPayload);
        drone.setId(droneDB.droneHandlerAdd(drone, idPh, battery));
        if(drone.getId()!=0){
            Logger.log(String.format("New Drone:%n%s %n",drone.toString()));
        }
        return drone;
    }

    /**
     * Gets all drones.
     *
     * @param phID the ph id
     * @return the all drones
     */
    public List<Drone> getAllDrones(int phID) {
        return droneDB.getAllDrones(phID);
    }


//--------------------------------------------------------

    /**
     * Remove drone boolean.
     *
     * @param id   the id
     * @param phID the ph id
     * @return the boolean
     */
    public boolean removeDrone(int id, int phID) {

        droneDB.getAllDrones(phID);
        return droneDB.droneHandlerRemove(id, phID);
    }

    /**
     * Update drone boolean.
     *
     * @param id              the id
     * @param batteryCapacity the battery capacity
     * @param maxPayload      the max payload
     * @param phID            the ph id
     * @param battery         the battery
     * @param state           the state
     * @return the boolean
     */
//--------------------------------------------------------
    public boolean updateDrone(int id, float batteryCapacity, double maxPayload, int phID, float battery, int state) {

        droneDB.getAllDrones(phID);

        return droneDB.droneHandlerUpdate(id, batteryCapacity, state, battery, maxPayload, phID);

    }
}
