package lapr.project.data;

import lapr.project.model.Delivery;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Statistics db.
 */
public class StatisticsDB extends DataHandler {


    /**
     * Gets all deliverys.
     *
     * @return the all deliverys
     */
    public List<Delivery> getAllDeliverys() {

        List<Delivery> list = new ArrayList<>();

        CallableStatement callStmt1;
        try {
            callStmt1 = getConnection().prepareCall("{? =call getAllDeliverys() }");

            callStmt1.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt1.execute();


            ResultSet res = (ResultSet) callStmt1.getObject(1);

            while (res.next()) {
                int id = res.getInt(1);
                Integer nif = res.getInt(2);
                int pharmacyID;
                int vehicleID;

                pharmacyID = res.getInt(5);
                vehicleID = res.getInt(6);

                float distanceMeters = res.getFloat(7);
                float timeMinutes = res.getFloat(8);
                float energyCost = res.getFloat(9);

                list.add(new Delivery(id, nif, vehicleID, pharmacyID, distanceMeters, timeMinutes, energyCost));

            }


        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        } finally {
            closeAll();
        }

        return list;

    }
}
