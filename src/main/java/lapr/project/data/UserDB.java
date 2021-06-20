package lapr.project.data;


import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type User db.
 */
public class UserDB  extends DataHandler{
    private static final int CLIENT_TYPE = 1;
    private static final int PHARMACYOWNER_TYPE = 2;

    /**
     * Gets user paper.
     *
     * @param email    the email
     * @param password the password
     * @param UserType the user type
     * @return the user paper
     */
    public int getUserPaper(String email, String password, int UserType) {
        String definer;
        if(CLIENT_TYPE == UserType) {
            definer = "{  ? = call funcGetUserPaperClient(?,?) }";
        } else if (PHARMACYOWNER_TYPE == UserType){
            definer = "{  ? = call funcGetUserPaperPharmacyOwner(?,?) }";
        } else {
            return 0;
        }

        CallableStatement callStmt;
        try {
            callStmt = getConnection().prepareCall(definer);

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, email);
            callStmt.setString(3, password);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                return rSet.getInt(1);
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Bad Log in");
    }

    /**
     * Add client boolean.
     *
     * @param email       the email
     * @param NIF         the nif
     * @param name        the name
     * @param password    the password
     * @param coordinates the coordinates
     * @param street      the street
     * @param postalCode  the postal code
     * @param doorNumber  the door number
     * @return the boolean
     * @throws SQLException the sql exception
     */
    public boolean addClient(String email, int NIF, String name, String password, String coordinates, String street, String postalCode, int doorNumber) throws SQLException {


            CallableStatement callStmt = getConnection().prepareCall("{ call addClient(?,?,?,?,?,?,?,?) }");

            callStmt.setString(1, email);
            callStmt.setInt(2, NIF);
            callStmt.setString(3, name);
            callStmt.setString(4, password);
            callStmt.setString(5, coordinates);
            callStmt.setString(6, street);
            callStmt.setString(7, postalCode);
            callStmt.setInt(8, doorNumber);

            callStmt.execute();

            closeAll();
            return true;
    }
}
