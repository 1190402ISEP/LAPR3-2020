package lapr.project.data;

import lapr.project.model.Courier;
import lapr.project.model.CourierState;
import lapr.project.ui.Logger;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Courier db.
 */
public class CourierDB extends DataHandler{

    private final String LITERAL = "\n_______________________";

    /**
     * New courier courier.
     *
     * @param name  the name
     * @param email the email
     * @param nif   the nif
     * @param niss  the niss
     * @return the courier
     */
    public Courier newCourier(String name, String email, int nif, String niss) {
        return new Courier(name,email,nif,niss);
    }

    /**
     * Add courier handler boolean.
     *
     * @param c          the c
     * @param pharmacyId the pharmacy id
     * @param vehicleId  the vehicle id
     * @return the boolean
     */
    public boolean addCourierHandler(Courier c, int pharmacyId, int vehicleId) {
        return addCourier(c.getName(), c.getEmail(), c.getNif(), c.getNiss(), 1, pharmacyId, vehicleId);
    }

    /**
     * Add courier boolean.
     *
     * @param name       the name
     * @param email      the email
     * @param nif        the nif
     * @param niss       the niss
     * @param state      the state
     * @param pharmacyId the pharmacy id
     * @param vehicleId  the vehicle id
     * @return the boolean
     */
    public boolean addCourier(String name, String email, int nif, String niss, int state, int pharmacyId,int vehicleId) {
        boolean added = false;
        CallableStatement callStmt = null;

        try {

            callStmt = getConnection().prepareCall("{ call addCourier(?,?,?,?,?,?,?) }");

            callStmt.setString(1, name);
            callStmt.setString(2, email);
            callStmt.setInt(3, nif);
            callStmt.setString(4, niss);
            callStmt.setInt(5, state);
            callStmt.setInt(6, pharmacyId);
            callStmt.setInt(7, vehicleId);

            callStmt.execute();
            added = true;


            Logger.log("Courier Created with sucess!!!" + LITERAL);

        } catch (SQLException e) {
            Logger.log("Error adding courier:\n");
            Logger.log(e.getMessage());
            Logger.log(LITERAL );
        }finally {
            try {


                assert callStmt != null;

                callStmt.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }
        return added;

    }


    /**
     * Gets courier list.
     *
     * @param pharmacyId the pharmacy id
     * @return the courier list
     */
    public List<Courier> getCourierList(int pharmacyId) {
        List<Courier> couriers = new ArrayList<>();

        CallableStatement callStmt=null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getCourierList(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, pharmacyId);
            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                String name = rSet.getString(1);
                String email = rSet.getString(2);
                int nif = rSet.getInt(3);
                String niss = rSet.getString(4);
                int state = rSet.getInt(5);
                CourierState finalState = getStateByID(state);
                couriers.add(new Courier(name,email,nif,niss,finalState));
            }

            return couriers;
        } catch (SQLException e) {
            Logger.log("Error getting courier list:\n");
            Logger.log(e.getMessage());
            Logger.log("\n_______________________");
        }finally {
            try {


                assert callStmt != null;

                callStmt.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }
        throw new IllegalArgumentException("No Data Found");
    }


    /**
     * Update courier boolean.
     *
     * @param name       the name
     * @param email      the email
     * @param nif        the nif
     * @param niss       the niss
     * @param state      the state
     * @param pharmacyId the pharmacy id
     * @param vehicleId  the vehicle id
     * @return the boolean
     */


    public boolean updateCourier(String name, String email, int nif,String niss, int state, int pharmacyId, int vehicleId) {

        boolean updated = false;
        CallableStatement callStmt=null;
        try {


            callStmt = getConnection().prepareCall("{ call updateCourier(?,?,?,?,?,?,?) }");

            callStmt.setString(1, name);
            callStmt.setString(2, email);
            callStmt.setInt(3, nif);
            callStmt.setString(4, niss);
            callStmt.setInt(5, state);
            callStmt.setInt(6, pharmacyId);
            callStmt.setInt(7, vehicleId);
            callStmt.execute();
            updated = true;
            closeAll();
            Logger.log("Courier updated with sucess!!!\n_______________________");
        } catch (SQLException e) {
            Logger.log("Error getting courier list:\n");
            Logger.log(e.getMessage());
            Logger.log("\n_______________________");
        }finally {
            try {


                assert callStmt != null;

                callStmt.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }
        return updated;
    }

    /**
     * Gets state by id.
     *
     * @param id the id
     * @return the state by id
     */
    public CourierState getStateByID(int id) {
        for (CourierState pos : CourierState.values()) {
            if (pos.getId() == id) {
                return pos;
            }
        }
        return null;
    }



}
