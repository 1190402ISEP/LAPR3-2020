package lapr.project.model;

/**
 * The type Drone.
 */
public class Drone extends Vehicle{
    /**
     * Instantiates a new Drone.
     *
     * @param batteryCapacity the battery capacity
     * @param maxPayload      the max payload
     */
    public Drone(float batteryCapacity, double maxPayload) {
        super(batteryCapacity, maxPayload);
    }

    /**
     * Instantiates a new Drone.
     *
     * @param id              the id
     * @param batteryCapacity the battery capacity
     * @param state           the state
     * @param battery         the battery
     * @param maxPayload      the max payload
     */
    public Drone(int id, float batteryCapacity, VehicleState state, float battery, double maxPayload) {
        super(id, batteryCapacity, state, battery, maxPayload);
    }
}
