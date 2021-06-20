package lapr.project.data;

import lapr.project.model.Address;

import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 * The type Address db.
 */
public class AddressDB extends DataHandler {

    /**
     * Create address boolean.
     *
     * @param address the address
     * @return the boolean
     */
    public boolean createAddress(Address address) {

        boolean isCreated = false;
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call createAddress(?,?,?,?) }");
            callStmt.setString(1, address.getCoordinates());
            callStmt.setString(2, address.getStreet());
            callStmt.setString(3, address.getPostalCode());
            callStmt.setInt(4, address.getDoorNumber());
            callStmt.execute();

            isCreated = true;


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert callStmt != null;
                callStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isCreated;
    }

}