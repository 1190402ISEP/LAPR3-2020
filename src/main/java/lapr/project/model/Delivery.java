package lapr.project.model;

/**
 * The type Delivery.
 */
public class Delivery {

    private final int idDelivery;
    private final Integer nif;
    private final int vehicleID;
    private final int pharmacyID;
    private final float distance;
    private final float timeMinutes;
    private final float energyCost;

    /**
     * Instantiates a new Delivery.
     *
     * @param idDelivery  the id delivery
     * @param nif         the nif
     * @param vehicleID   the vehicle id
     * @param pharmacyID  the pharmacy id
     * @param distance    the distance
     * @param timeMinutes the time minutes
     * @param energyCost  the energy cost
     */
    public Delivery(int idDelivery, Integer nif, int vehicleID, int pharmacyID, float distance, float timeMinutes, float energyCost) {
        this.idDelivery = idDelivery;
        this.nif = nif;
        this.vehicleID = vehicleID;
        this.pharmacyID = pharmacyID;
        this.distance = distance;
        this.timeMinutes = timeMinutes;
        this.energyCost = energyCost;
    }

    /**
     * Gets id delivery.
     *
     * @return the id delivery
     */
    public int getIdDelivery() {
        return idDelivery;
    }

    /**
     * Gets nif.
     *
     * @return the nif
     */
    public Integer getNif() {
        return nif;
    }

    /**
     * Gets vehicle id.
     *
     * @return the vehicle id
     */
    public int getVehicleID() {
        return vehicleID;
    }

    /**
     * Gets pharmacy id.
     *
     * @return the pharmacy id
     */
    public int getPharmacyID() {
        return pharmacyID;
    }

    /**
     * Gets distance.
     *
     * @return the distance
     */
    public float getDistance() {
        return distance;
    }

    /**
     * Gets time minutes.
     *
     * @return the time minutes
     */
    public float getTimeMinutes() {
        return timeMinutes;
    }

    /**
     * Gets energy cost.
     *
     * @return the energy cost
     */
    public float getEnergyCost() {
        return energyCost;
    }
}
