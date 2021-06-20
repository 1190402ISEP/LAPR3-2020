package lapr.project.ui;

import lapr.project.controller.PharmacyController;
import lapr.project.model.Address;

/**
 * The type Address ui.
 */
public class AddressUI {

	private final PharmacyController controller;

    /**
     * Instantiates a new Address ui.
     */
    public AddressUI() {
		controller = new PharmacyController();
	}

    /**
     * Create address.
     *
     * @param address the address
     */
    public void createAddress ( Address address ) {
		StringBuilder builder = new StringBuilder();
		boolean created = controller.createAddress( address );
		String message = created ? " Created successfully.\n" : "There was a problem.\n";

		builder.append("/--------------------------------------------------------\\\n");
		builder.append("  Creating address with coordinates: \n");
		builder.append( address.getCoordinates() ).append("\n");
		builder.append( message );
		builder.append("\\--------------------------------------------------------/\n");

		Logger.log( builder.toString() );
	}
}
