package lapr.project.ui;

import lapr.project.controller.ManageCourierController;
import lapr.project.controller.PharmacyController;
import lapr.project.model.CourierState;
import lapr.project.model.Courier;
import lapr.project.model.Pharmacy;
import lapr.project.model.files.ReadFiles;
import lapr.project.model.utils.Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Manage courier ui.
 */
public class ManageCourierUI {

    private static final ManageCourierController c = new ManageCourierController();
    private static final PharmacyController p = new PharmacyController();

    /**
     * Read file array list.
     *
     * @return the array list
     * @throws FileNotFoundException the file not found exception
     */
    public static ArrayList<String[]> readFile() throws FileNotFoundException {
        return ReadFiles.readFileScenario(Utils.getPath()+"ReadCourierScenario.txt");
    }

    /**
     * Read all courier scenarios.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public static void readAllCourierScenarios() throws FileNotFoundException {
        ArrayList<String[]> CouriersList = readFile();

        for(String[] courier : CouriersList) {
            switch (courier[0]) {
                case "c":
                    createCourier(courier);
                    break;
                case "u":
                    updateCourier(courier);
                    break;
            }
        }
    }

    /**
     * Create courier.
     *
     * @param Courier the courier
     */
    public static void createCourier(String[] Courier)  {
        Logger.log("Adding Courier");
        Logger.log("Insert the pharmacy's id:");
        List<Pharmacy> pharmacys = p.getAllPharmacies();
        Utils.listar(pharmacys);

        Logger.log("Insert the favourite vehicle of the courier:");
        Logger.log(String.format("Do you confirm to add this courirer to the pharmacy with the id: %d? with:" +
                        "Name: %s | Email: %s| Nif: %d| Niss: %s| Vehicle Id: %d|", Integer.parseInt(Courier[5]),Courier[1],Courier[2],Integer.parseInt(Courier[3]),Courier[4],Integer.parseInt(Courier[6])));

        c.addCourier(Courier[1],Courier[2],Integer.parseInt(Courier[3]),Courier[4],Integer.parseInt(Courier[5]),Integer.parseInt(Courier[6]));
        Logger.log("\n------------------------------------------------------------------------------------\n");
    }

    /**
     * Update courier.
     *
     * @param Courier the courier
     */
    public static void updateCourier(String[] Courier)  {

        Logger.log("Updating a Courier\n");
        Logger.log("Insert The Pharmacy's ID:\n");
        List<Pharmacy> pharmacys = p.getAllPharmacies();
        Utils.listar(pharmacys);
        Logger.log("\nInsert the nif of the Courier to Update\n");
        for (Courier pos : c.getCourierList(Integer.parseInt(Courier[5]))) {
            Logger.log(pos.toString() + "\n");
        }
        Logger.log("Insert new name:\n");
        Logger.log("Insert new email:\n");
        Logger.log("Insert new nif:\n");
        Logger.log("Insert new niss:\n");
        Logger.log("Insert the new vehicle id:\n");
        Logger.log("Insert new Courier state\n");
        for (CourierState pos : CourierState.values()) {
            Logger.log(pos.getId() + " " + pos.toString() + "\n");
        }

        Logger.log(String.format("Do you really want to Update the Courier with the nif %d in the list, from the pharmacy %d?\n with:" +
                        "Name: %s | Email: %s| Nif: %d| Niss: %s| Vehicle Id: %d| Courier State: %s|\n\n",
                Integer.parseInt(Courier[3]),Integer.parseInt(Courier[5]),Courier[1],Courier[2],Integer.parseInt(Courier[3]),Courier[7],Integer.parseInt(Courier[6]), Utils.getStateByID(Integer.parseInt(Courier[4]))));

        c.updateCourier(Courier[1],Courier[2],Integer.parseInt(Courier[3]),Courier[7],Integer.parseInt(Courier[4]),Integer.parseInt(Courier[5]),Integer.parseInt(Courier[6]));
        Logger.log("\n_______________________\n");
    }

}
