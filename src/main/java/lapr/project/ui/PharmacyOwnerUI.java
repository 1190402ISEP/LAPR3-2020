package lapr.project.ui;

import lapr.project.controller.PharmacyOwnerController;
import lapr.project.model.files.ReadFiles;
import lapr.project.model.utils.Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Pharmacy owner ui.
 */
public class PharmacyOwnerUI {

    private static final PharmacyOwnerController pOC = new PharmacyOwnerController();

    /**
     * Read all pharmacy owners scenarios.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public static void readAllPharmacyOwnersScenarios() throws FileNotFoundException {

        ArrayList<String[]> list = ReadFiles.readFileScenario(Utils.getPath() + "ReadPharmacyOwnerScenario.txt");

        assert list != null;
        for (String[] op : list) {
            if ("c".equals(op[0])) { //create pharmacy owner
                createPharmacyOwner(op);
            }
        }

    }

    /**
     * Create pharmacy owner.
     *
     * @param data the data
     */
    public static void createPharmacyOwner(String[] data) {
        pOC.createPharmacyOwner(data[1], Integer.parseInt(data[2]), data[3]);
    }

    /**
     * Loop.
     *
     *
     * @throws FileNotFoundException the file not found exception
     */
    public void loop() throws FileNotFoundException {
        ArrayList<String[]> matriz = ReadFiles.readFileScenario(Utils.getPath() + "PharmacyOwnerUserScenario.txt");


        assert matriz != null;
        String[] op = matriz.get(0);

        for (String s : op) {
            switch (s) {
                case "1": //new product etc...

                    ProductUI.readAllProductScenarios();
                    break;
                case "0":
                    return;
            }
        }


    }

    /**
     * Show owners.
     */
    public static void showOwners() {
        StringBuilder builder = new StringBuilder();
        List<String> owners = pOC.showOwners();

        builder.append("---------------------------------------------\n");

        if (owners.isEmpty()) {
            builder.append(" No owners found.\n");
        } else {


            Utils.listar(owners);

        }

        builder.append("-----------------------------------------------------\n");

        Logger.log(builder.toString());


    }
}
