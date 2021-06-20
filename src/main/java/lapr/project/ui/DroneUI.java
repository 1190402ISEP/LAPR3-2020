package lapr.project.ui;

import lapr.project.controller.DroneController;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleUtils;
import lapr.project.model.files.ReadFiles;
import lapr.project.model.utils.Utils;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The type Drone ui.
 */
public class DroneUI {
    /**
     * Drone Controller
     */
    private static final DroneController dc = new DroneController();
    /**
     * End of a log
     */
    private static final String END="-------------------------------------------------------------------";

    /**
     * Read file array list.
     *
     * @return the array list
     * @throws FileNotFoundException the file not found exception
     */
    public static ArrayList<String[]> readFile() throws FileNotFoundException {
        return ReadFiles.readFileScenario(Utils.getPath() + "ReadDroneScenario.txt");
    }

    /**
     * Read all drone scenarios.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public static void readAllDroneScenarios() throws FileNotFoundException {
        ArrayList<String[]> dronesList = readFile();

        for (String[] drone : dronesList) {
            switch (drone[0]) {
                case "c": //create drone
                    createDrone(drone);
                    break;
                case "u": //update drone
                    updateDrone(drone);
                    break;
                case "r": //remove drone
                    removeDrone(drone);
                    break;
            }
        }
    }

    /**
     * Create drone.
     *
     * @param drone the drone
     */
    public static void createDrone(String[] drone) {
        Logger.log("---------------------------Adding a Drone-------------------------------");

        Logger.log(String.format("Drone information: %d, with:\nBattery Capacity: "
                        + "%.2f| Max Payload: %.2f\n\n", Integer.parseInt(drone[3]), Float.parseFloat(drone[1]),
                Double.parseDouble(drone[2])));
        dc.insertDrone(Float.parseFloat(drone[1]), Double.parseDouble(drone[2]), Integer.parseInt(drone[3]), Integer.parseInt(drone[4]));
        Logger.log(END);

    }

    /**
     * Remove drone.
     *
     * @param drone the drone
     */
    public static void removeDrone(String[] drone) {
        Logger.log("Removing a Drone:\n");
        Logger.log("Insert the Pharmacy ID:\n");
        Logger.log("\nInsert the id of the drone to remove\n");
        for (Vehicle pos : dc.getAllDrones(Integer.parseInt(drone[2]))
        ) {
            Logger.log(pos.toString() + "\n");
        }
        Logger.log(String.format("Dou you really want to remove the Drone with id %d from the Pharmacy: %d ?\n",
                Integer.parseInt(drone[1]), Integer.parseInt(drone[2])));
        dc.removeDrone(Integer.parseInt(drone[1]), Integer.parseInt(drone[2]));
        Logger.log(END);

    }

    /**
     * Update drone.
     *
     * @param drone the drone
     */
    public static void updateDrone(String[] drone) {
        Logger.log("Updating a Drone:\n");
        Logger.log("Insert the Pharmacy ID:\n");
        Logger.log("\nInsert the id of the drone to Update\n");
        for (Vehicle pos : dc.getAllDrones(Integer.parseInt(drone[4]))) {
            Logger.log(pos.toString() + "\n");
        }
        Logger.log("Insert the new Battery Capacity:\n");
        Logger.log("Insert the Max Payload:\n");
        Logger.log("Insert The Actual Battery:\n");
        Logger.log("\n\nInsert the number corresponding to the actual Drone state:\n");
        VehicleUtils.listarStates();
        Logger.log(String.format("\nDo you really want to Update the Drone with id %d, from the pharmacy %d?\n with:" +
                        "Battery Capacity: %.2f| Max Payload %.2f| Actual Battery: %.2f|State: %s\n\n",
                Integer.parseInt(drone[1]), Integer.parseInt(drone[4]), Float.parseFloat(drone[2]),
                Double.parseDouble(drone[3]), Float.parseFloat(drone[5]),
                VehicleUtils.getStateByID(Integer.parseInt(drone[6]))));

        dc.updateDrone(Integer.parseInt(drone[1]), Float.parseFloat(drone[2]), Double.parseDouble(drone[3]),
                Integer.parseInt(drone[4]), Float.parseFloat(drone[5]), Integer.parseInt(drone[6]));
        Logger.log(END);

    }

}
