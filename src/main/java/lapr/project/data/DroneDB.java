package lapr.project.data;

import lapr.project.model.Drone;
import lapr.project.model.VehicleState;
import lapr.project.model.VehicleUtils;
import lapr.project.ui.Logger;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Drone db.
 */
public class DroneDB extends DataHandler {

    private final String LITERAL = "\n_______________________";

    /**
     * Drone handler add integer.
     *
     * @param drone the drone
     * @param idPh  the id ph
     * @return the integer
     */
    public Integer droneHandlerAdd(Drone drone, int idPh, int battery) {
        return addDrone(drone.getBatteryCapacity(), drone.getMaxPayload(), idPh, battery);
    }

    private Integer addDrone(float batteryCapacity, double maxPayload, int idPh, int battery) {
        int id = 0;
        try {


            CallableStatement callStmt = getConnection().prepareCall("{ ?=call addDrone(?,?,?,?,?) }");
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setFloat(2, batteryCapacity);
            callStmt.setInt(3, 1);
            float f = (float) maxPayload;
            callStmt.setFloat(4, f);
            callStmt.setFloat(5, (float) battery);
            callStmt.setInt(6, idPh);

            callStmt.execute();
            id = callStmt.getInt(1);
            closeAll();
            Logger.log("Drone Created with sucess!!!" + LITERAL);
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log("Error Creating Drone:\n");
            Logger.log(e.getMessage());
            Logger.log( LITERAL );
        }
        return id;
    }

    /**
     * Drone handler remove boolean.
     *
     * @param id   the id
     * @param phID the ph id
     * @return the boolean
     */
    public boolean droneHandlerRemove(int id, int phID) {
        boolean removed = false;
        try {


            CallableStatement callStmt = getConnection().prepareCall("{ call removeDrone(?,?) }");

            callStmt.setInt(1, id);
            callStmt.setInt(2, phID);

            callStmt.execute();
            removed = true;
            closeAll();
            Logger.log("\nDrone Removed with sucess!!!" + LITERAL );
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log("Error Removing Drone:\n");
            Logger.log(e.getMessage());
            Logger.log( LITERAL );
        }

        return removed;
    }

    /**
     * Drone handler update boolean.
     *
     * @param id              the id
     * @param batteryCapacity the battery capacity
     * @param state           the state
     * @param actualBattery   the actual battery
     * @param maxWeight       the max weight
     * @param phID            the ph id
     * @return the boolean
     */
    public boolean droneHandlerUpdate(int id, float batteryCapacity, int state, float actualBattery, double maxWeight, int phID) {
        boolean updated = false;
        try {


            CallableStatement callStmt = getConnection().prepareCall("{ call updateDrone(?,?,?,?,?,?) }");

            callStmt.setInt(1, id);
            callStmt.setFloat(2, batteryCapacity);
            callStmt.setInt(3, state);
            callStmt.setDouble(4, maxWeight);
            callStmt.setFloat(5, actualBattery);
            callStmt.setInt(6, phID);
            callStmt.execute();
            updated = true;
            closeAll();
            Logger.log("\nDrone Updated with sucess!!!" + LITERAL);

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log("Error Updating Drone:\n");
            Logger.log(e.getMessage());
            Logger.log( LITERAL );

        }
        return updated;
    }

    /**
     * Gets all drones.
     *
     * @param phID the ph id
     * @return the all drones
     */
    public List<Drone> getAllDrones(int phID) {
        List<Drone> drones = new ArrayList<>();

        CallableStatement callStmt;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllDrones(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, phID);
            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {

                int uniqueNumber = rSet.getInt(1);
                long batteryCapacity = rSet.getLong(2);
                int state = rSet.getInt(3);
                VehicleState stateFinal = VehicleUtils.getStateByID(state);
                double maxWeight = rSet.getFloat(4);
                float actualBattery = rSet.getFloat(5);
                drones.add(new Drone(uniqueNumber, batteryCapacity, stateFinal, actualBattery, maxWeight));
            }
            closeAll();
            return drones;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        throw new IllegalArgumentException("No Data Found");

    }


}
