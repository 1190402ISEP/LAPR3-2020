package lapr.project.model;

/**
 * The enum Courier state.
 */
public enum CourierState {


    /**
     * The Available.
     */
    AVAILABLE(1){
        @Override
        public String toString() {
            return "Available";
        }
    },
    /**
     * The Unavailable.
     */
    UNAVAILABLE(2){
        @Override
        public String toString() {
            return "Unavailable";
        }
    };

    private final int id;

    CourierState(int id){
        this.id=id;
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
