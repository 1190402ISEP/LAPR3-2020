package lapr.project.ui;

import lapr.project.controller.ParkController;
import lapr.project.model.Park;
import lapr.project.model.ParkPlace;
import lapr.project.model.files.ReadFiles;
import lapr.project.model.utils.Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Park ui.
 */
public class ParkUI {

    public static void readAllScenarios() {
        try {
            ArrayList<String[]> list = ReadFiles.readFileScenario(Utils.getPath() + "ReadParkScenario.txt");

            if (list.isEmpty()) {
                Logger.log("Could't load desired scene...");
            } else {

                for (String[] op : list) {
                    if (op[0].charAt(0) == '#') {
                        continue;
                    }
                    switch (op[0]) {

                        case "c":
                            createPark(new Park(Integer.parseInt(op[1]),
                                    Integer.parseInt(op[2]),
                                    Integer.parseInt(op[3]),
                                    Integer.parseInt(op[4]),
                                    Integer.parseInt(op[5])));
                            break;

                        case "s":
                            showParks();
                            break;

                        case "u":
                            updatePark(new Park(Integer.parseInt(op[1]),
                                    Integer.parseInt(op[2]),
                                    Integer.parseInt(op[3]),
                                    Integer.parseInt(op[4]),
                                    Integer.parseInt(op[5])));
                            break;

                        case "r":
                            removePark(Integer.parseInt(op[1]));
                            break;

                        case "cp":
                            createParkPlace(
                                    new ParkPlace(
                                            Integer.parseInt(op[1]),
                                            Integer.parseInt(op[2]) == 1), Integer.parseInt(op[3]) == 1);
                            break;

                    }
                }
            }

        } catch (FileNotFoundException e) {
            Logger.log(" Couldn't open the scene file...");
        }
    }

    /**
     * Show parks.
     */
    public static void showParks() {
        ParkController parkController = new ParkController();
        Logger.log("/--------------------------------------------------------\\%n");
        Logger.log(" Parks currently open: %n");
        List<Park> parkList = parkController.showParks();
        Utils.listar(parkList);
        Logger.log("\\--------------------------------------------------------/%n");
    }

    /**
     * Create park.
     *
     * @param park the park
     */
    public static void createPark(Park park) {
        ParkController parkController = new ParkController();
        StringBuilder builder = new StringBuilder();
        boolean created = parkController.createPark(park);
        String message = created ? " Created successfully.\n" : "There was a problem.\n";

        builder.append("----------------------------------------------------------------------------\n");
        builder.append("  Creating park in pharmacy with ID: \n");
        builder.append(park.getPharmacyID()).append("\n");
        builder.append(message);
        builder.append("--------------------------------------------------------------------------------\n");

        Logger.log(builder.toString());
    }

    /**
     * Update park.
     *
     * @param park the park
     */
    public static void updatePark(Park park) {
        ParkController parkController = new ParkController();
        StringBuilder builder = new StringBuilder();
        boolean updated = parkController.updatePark(park);
        String message = updated ? " Updated successfully." : "There was a problem.";

        builder.append("/--------------------------------------------------------\\");
        builder.append("  Updating park in pharmacy with ID: ");
        builder.append(park.getPharmacyID()).append("\n");
        builder.append(message);
        builder.append("\\--------------------------------------------------------/");

        Logger.log(builder.toString());
    }

    /**
     * Remove park.
     *
     * @param parkId the park id
     */
    public static void removePark(int parkId) {
        ParkController parkController = new ParkController();
        StringBuilder builder = new StringBuilder();
        boolean removed = parkController.removePark(parkId);
        String message = removed ? " Removed successfully." : "There was a problem.";

        builder.append("/--------------------------------------------------------\\");
        builder.append("  Removing park with ID: ");
        builder.append(parkId).append("\n");
        builder.append(message);
        builder.append("\\--------------------------------------------------------/");

        Logger.log(builder.toString());
    }

    public static void createParkPlace(ParkPlace place, boolean isCharging) {
        ParkController parkController = new ParkController();
        StringBuilder builder = new StringBuilder();
        boolean created = parkController.createParkPlace(place, isCharging);
        String message = created ? " Created successfully." : "There was a problem.";

        builder.append("/--------------------------------------------------------\\\n");
        builder.append("  Creating place with ID: \n");
        builder.append(place.getId()).append("\n");
        builder.append(message);
        builder.append("\\--------------------------------------------------------/\n");

        Logger.log(builder.toString());
    }


}
