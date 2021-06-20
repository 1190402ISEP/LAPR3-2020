package lapr.project.controller;

import lapr.project.data.PharmacyOwnerDB;

import java.util.List;

/**
 * The type Pharmacy owner controller.
 */
public class PharmacyOwnerController {

    private final PharmacyOwnerDB pharmacyOwnerDB;

    /**
     * Instantiates a new Pharmacy owner controller.
     */
    public PharmacyOwnerController(){
        pharmacyOwnerDB = new PharmacyOwnerDB();
    }

    /**
     * Instantiates a new Pharmacy owner controller.
     *
     * @param pharmacyOwnerDB the pharmacy owner db
     */
    public PharmacyOwnerController(PharmacyOwnerDB pharmacyOwnerDB){
        this.pharmacyOwnerDB = pharmacyOwnerDB;
    }

    /**
     * Create pharmacy owner boolean.
     *
     * @param email the email
     * @param nif   the nif
     * @param name  the name
     * @return the boolean
     */
    public boolean createPharmacyOwner(String email, int nif, String name){
        return pharmacyOwnerDB.pharmacyOwnerHandlerCreate(email, nif, name);
    }

    /**
     * Show owners list.
     *
     * @return the list
     */
    public List<String> showOwners () {
        return pharmacyOwnerDB.show();
    }
}
