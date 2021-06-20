package lapr.project.model;

import java.util.Objects;

/**
 * The type Vehicle.
 */
public class Vehicle {
    /**
     * Id of the Vehicle
     */
    private int id;
    /**
     * Battery Capaccity of the Vehicle
     */
    private float batteryCapacity;
    /**
     * State of the Vehicle
     */
    private VehicleState state;
    /**
     * Actual Battery of the Vehicle percentual
     */
    private float battery;
    /**
     * Max Payload of the Vehicle in kg
     */
    private double maxPayload;
    /**
     * Max Actual Battery
     */
    private static final float MAX_BATTERY = 100;

    /**
     * Instantiates a new Vehicle.
     *
     * @param batteryCapacity the battery capacity
     * @param maxPayload      the max payload
     */
    public Vehicle(float batteryCapacity, double maxPayload) {
        this.id = -1;
        this.batteryCapacity = batteryCapacity;
        this.state = VehicleState.AVAILABLE;
        this.battery = MAX_BATTERY;
        this.maxPayload = maxPayload;
    }

    /**
     * Instantiates a new Vehicle.
     *
     * @param id              the id
     * @param batteryCapacity the battery capacity
     * @param state           the state
     * @param battery         the battery
     * @param maxPayload      the max payload
     */
    public Vehicle(int id, float batteryCapacity, VehicleState state, float battery, double maxPayload) {
        this.id = id;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
        this.battery = battery;
        this.maxPayload = maxPayload;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets battery capacity.
     *
     * @return the battery capacity
     */
    public float getBatteryCapacity() {
        return batteryCapacity;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public VehicleState getState() {
        return state;
    }

    /**
     * Gets battery.
     *
     * @return the battery
     */
    public float getBattery() {
        return battery;
    }

    /**
     * Gets max payload.
     *
     * @return the max payload
     */
    public double getMaxPayload() {
        return maxPayload;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets battery capacity.
     *
     * @param batteryCapacity the battery capacity
     */
    public void setBatteryCapacity(float batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(VehicleState state) {
        this.state = state;
    }

    /**
     * Sets battery.
     *
     * @param battery the battery
     */
    public void setBattery(float battery) {
        this.battery = battery;
    }

    /**
     * Sets max payload.
     *
     * @param maxPayload the max payload
     */
    public void setMaxPayload(double maxPayload) {
        this.maxPayload = maxPayload;
    }

    /**
     * Compares two objects if they are equals return true else return false
     *
     * @param o The other object to compare
     * @return boolean value true if they are equals false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id;
    }

    /**
     * Get the hash code
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Creates an Textual Representation of The Vehicle
     *
     * @return Textual Representation of The Vehicle
     */
    @Override
    public String toString() {
        return String.format("|ID: %d |Battery Cappacity: %.2f Wh|State: %s |Battery: %.2f %%|Max Payload: %.2f Kg| "
                , id, batteryCapacity, state, battery, maxPayload);
    }
}
