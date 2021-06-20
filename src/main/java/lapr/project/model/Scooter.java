package lapr.project.model;

/**
 * The type Scooter.
 */
public class Scooter extends Vehicle {
    /**
     * Qr Code of the Scooter- true if scooter has qr code 0 if not
     */
    private boolean qrCode;

    /**
     * Instantiates a new Scooter.
     *
     * @param qrCode          the qr code
     * @param batteryCapacity the battery capacity
     * @param maxPayload      the max payload
     */
    public Scooter(boolean qrCode, float batteryCapacity, double maxPayload) {
        super(batteryCapacity, maxPayload);
        this.qrCode = qrCode;

    }

    /**
     * Instantiates a new Scooter.
     *
     * @param copy the copy
     */
    public Scooter(Scooter copy) {
        super(copy.getId(), copy.getBatteryCapacity(), copy.getState(), copy.getBattery(), copy.getMaxPayload());
        this.qrCode = copy.getQrCode();
    }

    /**
     * Instantiates a new Scooter.
     *
     * @param id              the id
     * @param qrCode          the qr code
     * @param batteryCapacity the battery capacity
     * @param state           the state
     * @param battery         the battery
     * @param maxPayload      the max payload
     */
    public Scooter(int id, boolean qrCode, long batteryCapacity, VehicleState state, float battery, double maxPayload) {
        super(id, batteryCapacity, state, battery, maxPayload);
        this.qrCode = qrCode;

    }

    /**
     * Gets qr code.
     *
     * @return the qr code
     */
    public boolean getQrCode() {
        return isQrCode();
    }

    /**
     * Is qr code boolean.
     *
     * @return the qrCode
     */
    public boolean isQrCode() {
        return qrCode;
    }

    /**
     * Sets qr code.
     *
     * @param qrCode the qr code
     */
    public void setQrCode(boolean qrCode) {
        this.qrCode = qrCode;
    }

    /**
     * Creates an Textual Representation of The Scooter
     *
     * @return Textual Representation of The Scooter
     */
    @Override
    public String toString() {
        return String.format("%s Qr code: %s", super.toString(), qrCode);
    }
}
