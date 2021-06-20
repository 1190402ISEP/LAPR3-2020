package lapr.project.controller;

import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;
import lapr.project.ui.Logger;

import java.util.List;


/**
 * The type Scooter controller.
 */
public class ScooterController {
    /**
     * Scooter DB
     */
    private final ScooterDB scooterDB;


    /**
     * Instantiates a new Scooter controller.
     */
    public ScooterController() {
        scooterDB = new ScooterDB();
    }

    /**
     * Instantiates a new Scooter controller.
     *
     * @param copy the copy
     */
    public ScooterController(ScooterDB copy) {
        scooterDB = copy;
    }

    /**
     * Insert scooter scooter.
     *
     * @param qrCode          the qr code
     * @param batteryCapacity the battery capacity
     * @param maxPayload      the max payload
     * @param idPh            the id ph
     * @return the scooter
     */
    public Scooter insertScooter(int qrCode, float batteryCapacity, double maxPayload, int idPh, int battery) {
        boolean qrCodeIn = ScooterDB.checkIntegerQrCode(qrCode);
        Scooter scooter = new Scooter(qrCodeIn, batteryCapacity, maxPayload);
        scooter.setId(scooterDB.scooterHandlerAdd(scooter, idPh,battery));
        if (scooter.getId() != 0) {
            Logger.log(String.format("New Scooter:%n%s%n%n", scooter.toString()));

        }
        return scooter;
    }

    /**
     * Gets all scooters.
     *
     * @param phID the ph id
     * @return the all scooters
     */
    public List<Scooter> getAllScooters(int phID) {
        return scooterDB.getAllScooters(phID);
    }


//--------------------------------------------------------

    /**
     * Remove scooter boolean.
     *
     * @param id   the id
     * @param phID the ph id
     * @return the boolean
     */
    public boolean removeScooter(int id, int phID) {
        return scooterDB.scooterHandlerRemove(id, phID);
    }

    /**
     * Update scooter boolean.
     *
     * @param id              the id
     * @param qrCode          the qr code
     * @param batteryCapacity the battery capacity
     * @param maxPayload      the max payload
     * @param phID            the ph id
     * @param battery         the battery
     * @param state           the state
     * @return the boolean
     */
//--------------------------------------------------------
    public boolean updateScooter(int id, int qrCode, float batteryCapacity, double maxPayload, int phID, float battery, int state) {
        return scooterDB.scooterHandlerUpdate(id, qrCode, batteryCapacity, state, battery, maxPayload, phID);

    }
}


