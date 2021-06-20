package lapr.project.ui;

import lapr.project.controller.PharmacyController;
import lapr.project.model.Address;
import lapr.project.model.Pharmacy;
import lapr.project.model.files.ReadFiles;
import lapr.project.model.utils.Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Pharmacy ui.
 */
public class PharmacyUI {

    /**
     * Read all pharmacy scenarios.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public static void readAllPharmacyScenarios() throws FileNotFoundException {

        ArrayList<String[]> list = ReadFiles.readFileScenario(Utils.getPath() + "ReadPharmacyScenario.txt");

        assert list != null;
        for (String[] op : list) {
            if ("c".equals(op[0])) { //create pharmacy
                listOwners();
                createPharmacy(op[1], op[2], new Address(op[3], op[4], op[5], Integer.parseInt(op[6])));
            }
            if ("li".equals(op[0])) { //create pharmacy
                listPharmacies();
            }
            if ("u".equals(op[0])) { //create pharmacy
                listPharmacies();
                updatePharmacy( op[1], op[2], new Address( op[3], op[4], op[5], Integer.parseInt(op[6]) ) );
            }
        }
    }

    /**
     * List owners.
     */
    public static void listOwners() {
        PharmacyOwnerUI.showOwners();
    }

    /**
     * List pharmacies.
     */
    public static void listPharmacies () {
        PharmacyController controller = new PharmacyController();
        StringBuilder output = new StringBuilder();

        List<Pharmacy> pharmacyList = controller.getAllPharmacies();

        output.append("-----------------------------------------------------------------\n");

        if ( pharmacyList.isEmpty() ) {
            output.append( "Couldn't retrieve the pharmacyList.\n");
        } else {
            output.append( "\n " );
            Utils.listar( pharmacyList );
        }

        output.append("-----------------------------------------------------------------\n");

        Logger.log( output.toString() );
    }

    /**
     * Create pharmacy.
     *
     * @param ownerEmail  the owner email
     * @param designation the designation
     * @param address     the address
     */
    public static void createPharmacy(String ownerEmail, String designation, Address address) {
        StringBuilder output = new StringBuilder();

        listOwners();

        PharmacyController controller = new PharmacyController();

        controller.createAddress(address);

        int id = controller.createPharmacy(ownerEmail, designation, address);

        output.append("\n/--------------------------------------------------------\\");
        output.append("  \nCreating pharmacy with owner: ");
        output.append(ownerEmail);

        if (id > 0) {
            output.append("\n\n Pharmacy created successfuly wiht id ").append(id);
        } else {
            output.append("\n\n Failed to create pharmacy...");
        }

        output.append("\n\\--------------------------------------------------------/\n");

        Logger.log(output.toString());
    }

    /**
     * Update pharmacy.
     *
     * @param ownerEmail  the owner email
     * @param designation the designation
     * @param address     the address
     */
    public static void updatePharmacy (String ownerEmail, String designation, Address address) {
        StringBuilder output = new StringBuilder();

        PharmacyController controller = new PharmacyController();

        boolean isUpdated = controller.updatePharmacy( ownerEmail, designation, address );

        output.append("\n/--------------------------------------------------------\\");
        output.append("\n  Updating pharmacy with owner: ");
        output.append( ownerEmail );

        if ( isUpdated ) {
            output.append("\n\n Pharmacy updated successfully.");
        } else {
            output.append("\n\n Failed to update pharmacy...");
        }

        output.append("\n\\--------------------------------------------------------/\n");

        Logger.log( output.toString() );
    }
}
