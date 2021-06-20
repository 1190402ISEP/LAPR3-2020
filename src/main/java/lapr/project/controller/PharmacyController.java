package lapr.project.controller;

import lapr.project.data.AddressDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.UserSession;
import lapr.project.model.Address;
import lapr.project.model.Pharmacy;
import lapr.project.model.User;

import java.util.List;

/**
 * The type Pharmacy controller.
 */
public class PharmacyController {

    private final PharmacyDB pharmacyDB;
    private final AddressDB addressDB;


    /**
     * Instantiates a new Pharmacy controller.
     */
    public PharmacyController() {
        this.addressDB = new AddressDB();
        this.pharmacyDB = new PharmacyDB();
    }

    /**
     * Instantiates a new Pharmacy controller.
     *
     * @param pharmacyDB the pharmacy db
     * @param addressDB  the address db
     */
    public PharmacyController(PharmacyDB pharmacyDB, AddressDB addressDB) {
        this.pharmacyDB = pharmacyDB;
        this.addressDB = addressDB;
    }

    /**
     * Create pharmacy int.
     *
     * @param ownerEmail  the owner email
     * @param designation the designation
     * @param address     the address
     * @return the int
     */
    public int createPharmacy(String ownerEmail, String designation, Address address) {
        return pharmacyDB.createPharmacy(ownerEmail, designation, address) ;
    }

    /**
     * Update pharmacy boolean.
     *
     * @param ownerEmail  the owner email
     * @param designation the designation
     * @param address     the address
     * @return the boolean
     */
    public boolean updatePharmacy(String ownerEmail, String designation, Address address) {
        return pharmacyDB.update(ownerEmail, designation, address);
    }

    /**
     * Gets all pharmacies.
     *
     * @return the all pharmacies
     */
    public List<Pharmacy> getAllPharmacies() {
        return pharmacyDB.getAllPharmacies();
    }

    /**
     * Gets pharmacy by user email.
     *
     * @return the pharmacy by user email
     */
    public Pharmacy getPharmacyByUserEmail() {
        User user = UserSession.getInstance().getUser();
        String email = user.getEmail();
        return pharmacyDB.getPharmacyByUserEmail(email);
    }

    /**
     * Create address boolean.
     *
     * @param address the address
     * @return the boolean
     */
    public boolean createAddress(Address address) {
        return addressDB.createAddress(address);
    }

}
