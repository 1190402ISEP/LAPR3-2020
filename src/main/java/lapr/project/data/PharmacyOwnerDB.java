package lapr.project.data;


import lapr.project.ui.Logger;
import oracle.jdbc.OracleTypes;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Pharmacy owner db.
 */
public class PharmacyOwnerDB extends DataHandler {


    /**
     * Pharmacy owner handler create boolean.
     *
     * @param email the email
     * @param nif   the nif
     * @param name  the name
     * @return the boolean
     */
    public boolean pharmacyOwnerHandlerCreate(String email, int nif, String name) {
        return createPharmacyOwner(email, nif, name);
    }


    private boolean createPharmacyOwner(String email, int nif, String name) {
        String password = "";
        try {
            EmailHandler eh = new EmailHandler();
            password = "po";
            eh.sendEmail(email, "Your Password", "Here you have your password " + password);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CallableStatement callS = null;

        try {

            callS = getConnection().prepareCall("{call createPharmacyOwner(?,?,?,?)}");
            callS.setString(1, email);
            callS.setInt(2, nif);
            callS.setString(3, name);
            callS.setString(4, password);

            callS.execute();

            return true;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                assert callS != null;
                callS.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /** public String generatePassword() {
        int leftLimit = 48; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 7;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }/**

    /**
     * Show list.
     *
     * @return the list
     */
    public List<String> show() {
        List<String> owners = new ArrayList<>();

        try ( CallableStatement statement = getConnection().prepareCall("{ ? = call show() }" ) ){

            statement.registerOutParameter( 1, OracleTypes.CURSOR );
            statement.execute();

            ResultSet result = (ResultSet) statement.getObject( 1 );

            while ( result.next() ) {
                owners.add( result.getString( 1 ) );
            }

        } catch ( SQLException exception ) {
            Logger.log( "Failed to uphold query: " + exception.getErrorCode() );
        } finally {
           closeAll();
        }

        return owners;
    }
}
