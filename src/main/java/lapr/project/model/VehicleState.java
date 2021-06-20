package lapr.project.model;

/**
 * The enum Vehicle state.
 */
public enum VehicleState {


    /**
     * The Available.
     */
    AVAILABLE(1) {
        @Override
        public String toString() {
            return "Available";
        }
    },
    /**
     * The Unavailable.
     */
    UNAVAILABLE(2) {
        @Override
        public String toString() {
            return "UnAvailable";
        }
    },
    /**
     * The Broken.
     */
    BROKEN(3) {
        @Override
        public String toString() {
            return "Broken";
        }
    },
    /**
     * The Charging.
     */
    CHARGING(4) {
        @Override
        public String toString() {
            return "Charging";
        }
    };

    /**
     * State ID
     */
    private final int id;

    /**
     * Instantiates an vehicle state
     * @param id the id
     */
    VehicleState(int id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }
}
