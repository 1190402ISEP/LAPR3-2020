package lapr.project.data;

import com.google.zxing.WriterException;
import lapr.project.model.Scooter;

import lapr.project.model.VehicleState;
import lapr.project.model.VehicleUtils;
import lapr.project.ui.Logger;
import oracle.jdbc.OracleTypes;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Scooter db.
 */
public class ScooterDB extends DataHandler {

    /**
     * Gets scooter by id.
     *
     * @param id the id
     * @return the scooter by id
     */
    public Scooter getScooterByID(int id) {

        CallableStatement callStmt;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getScooter(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(1, id);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int uniqueNumber = rSet.getInt(1);
                int qrCode = rSet.getInt(2);
                long batteryCapacity = rSet.getLong(3);
                int state = rSet.getInt(5);
                boolean intr = checkIntegerQrCode(qrCode);
                double maxWeight = rSet.getDouble(7);
                float battery = rSet.getFloat(6);
                closeAll();
                return new Scooter(uniqueNumber, intr, batteryCapacity, getStateByID(state), battery, maxWeight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeAll();
        throw new IllegalArgumentException("No Scooter with ID:" + id);
    }

    /**
     * Check integer qr code boolean.
     *
     * @param qrCode the qr code
     * @return the boolean
     */
    public static boolean checkIntegerQrCode(int qrCode) {
        return qrCode == 1;
    }

    /**
     * Scooter handler add int.
     *
     * @param scooter the scooter
     * @param idPh    the id ph
     * @return the int
     */
    public int scooterHandlerAdd(Scooter scooter, int idPh, int battery) {
        int n = checkQrCode(scooter.getQrCode());
        return addScooter(n, scooter.getBatteryCapacity(), scooter.getMaxPayload(), idPh, battery);
    }

    /**
     * Check qr code int.
     *
     * @param qrCode the qr code
     * @return the int
     */
    public static int checkQrCode(boolean qrCode) {
        if (qrCode) {
            return 1;
        }
        return 0;
    }

    private int addScooter(int qrCode, float batteryCapacity, double maxPayload, int idPh, int battery) {
        int id = 0;
        try {


            CallableStatement callStmt = getConnection().prepareCall("{?= call addScooter(?,?,?,?,?,?) }");
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, qrCode);
            callStmt.setFloat(3, batteryCapacity);
            callStmt.setInt(4, 1);
            float f = (float) maxPayload;
            callStmt.setFloat(5, f);
            callStmt.setFloat(6, (float) battery);
            callStmt.setInt(7, idPh);

            callStmt.execute();
            id = callStmt.getInt(1);
            closeAll();
            if (qrCode == 1) {
                String fileName = new SimpleDateFormat("yyyy-MM-dd-HH_mm").format(new Date());
                try {
                    QrCodeGen.createQRImage(fileName + "-ScooterID-" + id, id + "");
                    Logger.log("\nQr code generated with sucess!!!\n");
                } catch (IOException | WriterException ex) {
                    ex.printStackTrace();
                    Logger.log("\nError generating qr code\n");
                }
            }
            EmailHandler email = new EmailHandler();
            email.sendEmail("moussamanafa@outlook.pt", "Adding a scooter", String.format("Added a new Scooter with the id: %d to the pharmacy: %d\n" +
                    "qrcode: %s \nbatteryCapacity: %f, state: %s \n maxPayload: %f, battery: %f", id, idPh, checkIntegerQrCode(qrCode), batteryCapacity, VehicleUtils.getStateByID(1), f, (float) battery));
            Logger.log("Scooter Created with sucess!!!\n_______________________");

            return id;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            Logger.log("Error Creating Scooter:\n");
            Logger.log(e.getMessage());
            Logger.log("\n_______________________");
        }
        return id;
    }

    /**
     * Scooter handler remove boolean.
     *
     * @param uniqueNumber the unique number
     * @param phID         the ph id
     * @return the boolean
     */
    public boolean scooterHandlerRemove(int uniqueNumber, int phID) {
        boolean removed = false;
        try {


            CallableStatement callStmt = getConnection().prepareCall("{ call removeScooter(?,?) }");

            callStmt.setInt(1, uniqueNumber);
            callStmt.setInt(2, phID);

            callStmt.execute();
            removed = true;
            closeAll();
            Logger.log("Scooter Removed with sucess!!!\n_______________________");
            EmailHandler email = new EmailHandler();
            email.sendEmail("moussamanafa@outlook.pt", "Removing a scooter", String.format("The scooter %d from the pharmacy %d was removed.", uniqueNumber, phID));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            Logger.log("Error Removing Scooter:\n");
            Logger.log(e.getMessage());
            Logger.log("\n_______________________");
        }
        return removed;
    }

    /**
     * Scooter handler update boolean.
     *
     * @param id              the id
     * @param qrCode          the qr code
     * @param batteryCapacity the battery capacity
     * @param state           the state
     * @param actualBattery   the actual battery
     * @param maxWeight       the max weight
     * @param phID            the ph id
     * @return the boolean
     */
    public boolean scooterHandlerUpdate(int id, int qrCode, float batteryCapacity, int state, float actualBattery, double maxWeight, int phID) {
        boolean updated = false;
        try {


            CallableStatement callStmt = getConnection().prepareCall("{ call updateScooter(?,?,?,?,?,?,?) }");

            callStmt.setInt(1, id);
            callStmt.setInt(2, qrCode);
            callStmt.setFloat(3, batteryCapacity);
            callStmt.setInt(4, state);
            callStmt.setDouble(5, maxWeight);
            callStmt.setFloat(6, actualBattery);
            callStmt.setInt(7, phID);
            callStmt.execute();
            updated = true;
            closeAll();
            Logger.log("Scooter Updated with sucess!!!\n_______________________");

            EmailHandler email = new EmailHandler();
            email.sendEmail("moussamanafa@outlook.pt", "Updating a scooter", String.format("The scooter %d from the pharmacy %d was altered. \n" +
                    "Scooter information: \n qrCode: %s,\n batteryCapacity: %f, state: %s \n maxWeight: %f, actual battery: %f", id, phID, checkIntegerQrCode(qrCode), batteryCapacity, VehicleUtils.getStateByID(id), maxWeight, actualBattery));


        } catch (SQLException | IOException e) {
            e.printStackTrace();
            Logger.log("Error Updating Scooter:\n");
            Logger.log(e.getMessage());
            Logger.log("\n_______________________");
        }
        return updated;
    }

    /**
     * Gets all scooters.
     *
     * @param phID the ph id
     * @return the all scooters
     */
    public List<Scooter> getAllScooters(int phID) {
        List<Scooter> scooters = new ArrayList<>();

        CallableStatement callStmt;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllScooters(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, phID);
            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                int uniqueNumber = rSet.getInt(1);
                int qrCode = rSet.getInt(2);
                long batteryCapacity = rSet.getLong(3);
                int state = rSet.getInt(4);
                boolean intr = checkIntegerQrCode(qrCode);
                VehicleState stateFinal = getStateByID(state);
                double maxWeight = rSet.getFloat(5);
                float actualBattery = rSet.getFloat(6);
                scooters.add(new Scooter(uniqueNumber, intr, batteryCapacity, stateFinal, actualBattery, maxWeight));
            }
            closeAll();
            return scooters;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        throw new IllegalArgumentException("No Data Found");

    }

    /**
     * Gets state by id.
     *
     * @param id the id
     * @return the state by id
     */
    public VehicleState getStateByID(int id) {
        for (VehicleState pos : VehicleState.values()) {
            if (pos.getId() == id) {
                return pos;
            }
        }
        return null;
    }

}
