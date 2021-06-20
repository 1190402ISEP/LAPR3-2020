package lapr.project.ui;

import lapr.project.controller.ScooterController;
import lapr.project.model.Scooter;
import lapr.project.model.VehicleUtils;
import lapr.project.model.files.ReadFiles;
import lapr.project.model.utils.Utils;


import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The type Scooter ui.
 */
public class ScooterUI {
    /**
     * Scooter Controller
     */
    private static final ScooterController sc = new ScooterController();
    /**
     * End String of the Log
     */
    private static final String END = "----------------------------------------------------------------";

    /**
     * Read file array list.
     *
     * @return the array list
     * @throws FileNotFoundException the file not found exception
     */
    public static ArrayList<String[]> readFile() throws FileNotFoundException {
        return ReadFiles.readFileScenario(Utils.getPath() + "ReadScooterScenario.txt");
    }

    /**
     * Read all scooter scenarios.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public static void readAllScooterScenarios() throws FileNotFoundException {
        ArrayList<String[]> ScootersList = readFile();

        for (String[] scooter : ScootersList) {
            switch (scooter[0]) {
                case "c": //create Scooter
                    createScotter(scooter);
                    break;
                case "u": //update Scooter
                    updateScooter(scooter);
                    break;
                case "r": //remove Scooter
                    removeScooter(scooter);
                    break;
            }
        }
    }

    /**
     * Create scotter.
     *
     * @param Scooter the scooter
     */
    public static void createScotter(String[] Scooter) {
        Logger.log("----------------------Adding Scooter------------------------------");

        Logger.log(String.format("Scooter information to the pharmacy %d?\n" +
                        "QrCode: %d |Battery Capacity: %f| Max Payload: %f\n", Integer.parseInt(Scooter[4]),
                Integer.parseInt(Scooter[1]), Float.parseFloat(Scooter[2]), Double.parseDouble(Scooter[3])));

        sc.insertScooter(Integer.parseInt(Scooter[1]), Float.parseFloat(Scooter[2]), Double.parseDouble(Scooter[3]),
                Integer.parseInt(Scooter[4]), Integer.parseInt(Scooter[5]));
        Logger.log(END);

        Logger.log("---------------------------------------------------------------------");


    }

    /**
     * Remove scooter.
     *
     * @param RemScooter the rem scooter
     */
    public static void removeScooter(String[] RemScooter) {
        Logger.log("Removing a Scooter\n");
        Logger.log("Insert the Pharmacy ID:\n");
        Logger.log("\nInsert the id of the Scooter to remove\n");
        for (Scooter pos : sc.getAllScooters(Integer.parseInt(RemScooter[2]))
        ) {
            Logger.log(pos.toString() + "\n");
        }
        Logger.log(String.format("Do you really want to remove the scooter with id %d, from the pharmacy %d?\n",
                Integer.parseInt(RemScooter[1]), Integer.parseInt(RemScooter[2])));
        sc.removeScooter(Integer.parseInt(RemScooter[1]), Integer.parseInt(RemScooter[2]));
        Logger.log(END);

    }

    /**
     * Update scooter.
     *
     * @param Scooter the scooter
     */
    public static void updateScooter(String[] Scooter) {
        Logger.log("Updating a Scooter\n");
        Logger.log("Insert The Pharmacy ID:\n");
        Logger.log("\nInsert the id of the Scooter to Update\n");
        for (Scooter pos : sc.getAllScooters(Integer.parseInt(Scooter[5]))) {
            Logger.log(pos.toString() + "\n");
        }
        Logger.log("Insert 1 if have qr code 0 if not:\n");
        Logger.log("Insert The new Battery Capacity:\n");
        Logger.log("Insert the new MaxPayload\n");
        Logger.log("Insert the Actual Battery:\n");
        Logger.log("\n\nInsert the number corresponding to the actual scooter state:\n");
        VehicleUtils.listarStates();
        Logger.log(String.format("\nDo you really want to Update the scooter with id %d, from the pharmacy %d?\n with:" +
                        "Qr Code: %d| Battery Capacity: %.2f| Max Payload %.2f| Actual Battery: %.2f|State: %s\n\n",
                Integer.parseInt(Scooter[1]), Integer.parseInt(Scooter[5]), Integer.parseInt(Scooter[2]),
                Float.parseFloat(Scooter[3]), Double.parseDouble(Scooter[4]), Float.parseFloat(Scooter[6]),
                VehicleUtils.getStateByID(Integer.parseInt(Scooter[7]))));

        sc.updateScooter(Integer.parseInt(Scooter[1]), Integer.parseInt(Scooter[2]), Float.parseFloat(Scooter[3]),
                Double.parseDouble(Scooter[4]), Integer.parseInt(Scooter[5]), Float.parseFloat(Scooter[6]),
                Integer.parseInt(Scooter[7]));
        Logger.log(END);
    }

}
