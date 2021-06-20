package lapr.project.data;

import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Pharmacy db.
 */
public class PharmacyDB extends DataHandler {

    /**
     * Gets all pharmacies.
     *
     * @return the all pharmacies
     */
    public List<Pharmacy> getAllPharmacies() {
        List<Pharmacy> pharmacys = new ArrayList<>();

        try (CallableStatement callStmt =
                     getConnection().prepareCall("{ ? = call getAllPharmacys() }")) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                int id = rSet.getInt(1);
                String designation = rSet.getString(2);
                String address = rSet.getString(3);
                String pharmacyOwner = rSet.getString(4);
                pharmacys.add(new Pharmacy(id, designation, address, pharmacyOwner));
            }

            return pharmacys;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        throw new IllegalArgumentException("No Data Found");

    }

    /**
     * Create pharmacy int.
     *
     * @param pharmacyOwner the pharmacy owner
     * @param designation   the designation
     * @param address       the address
     * @return the int
     */
    public int createPharmacy(String pharmacyOwner, String designation, Address address) {
        Integer pharmacyID;
        CallableStatement callStmt = null;
        if (!pharmacyOwner.isEmpty() && !designation.isEmpty() && address != null) {
            try {
                callStmt = getConnection().prepareCall("{ ? = call createPharmacy(?,?,?) }");

                callStmt.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt.setString(2, pharmacyOwner);
                callStmt.setString(3, address.getCoordinates());
                callStmt.setString(4, designation);

                callStmt.execute();

                pharmacyID = (Integer) callStmt.getObject(1);

                return pharmacyID;
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
        }
        return -1;

    }

    /**
     * Update boolean.
     *
     * @param pharmacyOwner the pharmacy owner
     * @param designation   the designation
     * @param address       the address
     * @return the boolean
     */
    public boolean update(String pharmacyOwner, String designation, Address address) {
        boolean isUpdated = false;

        if (!pharmacyOwner.isEmpty() && !designation.isEmpty() && address != null) {

            try (CallableStatement callStmt = getConnection().prepareCall("{ call updatePharmacy(?,?,?) }")) {

                callStmt.setString(1, pharmacyOwner);
                callStmt.setString(2, address.getCoordinates());
                callStmt.setString(3, designation);
                callStmt.execute();

                isUpdated = true;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isUpdated;
    }


    /**
     * Gets pharmacy by user email.
     *
     * @param email the email
     * @return the pharmacy by user email
     */
    public Pharmacy getPharmacyByUserEmail(String email) {
        CallableStatement callStmt;
        Pharmacy pharmacy = null;

        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacyByUserEmail(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, email);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int id = rSet.getInt(1);
                String designation = rSet.getString(2);
                String adress = rSet.getString(3);
                String pharmacyOwner = rSet.getString(4);

                closeAll();
                pharmacy = new Pharmacy(id, designation, adress, pharmacyOwner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pharmacy;
    }
}
