package lapr.project.controller;

import lapr.project.data.AddressDB;
import lapr.project.model.Address;

/**
 * The type Address controller.
 */
public class AddressController {

    private final AddressDB addressDB;

    /**
     * Instantiates a new Address controller.
     *
     * @param addressDB the address db
     */
    public AddressController( AddressDB addressDB ) {
        this.addressDB = addressDB;
    }

    /**
     * Create address boolean.
     *
     * @param address the address
     * @return the boolean
     */
    public boolean createAddress( Address address ) {
        return addressDB.createAddress( address );
    }
}
