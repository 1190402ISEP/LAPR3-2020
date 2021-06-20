package lapr.project.model;

/**
 * The type Charging.
 */
public class Charging {
    private final Integer pharmacyID;
    private final Integer scooterID;
    private final String CourierEmail;

    /**
     * Instantiates a new Charging.
     *
     * @param pharmacyID   the pharmacy id
     * @param scooterID    the scooter id
     * @param courierEmail the courier email
     */
    public Charging(Integer pharmacyID, Integer scooterID, String courierEmail) {
        this.pharmacyID = pharmacyID;
        this.scooterID = scooterID;
        this.CourierEmail = courierEmail;
    }

    /**
     * Gets pharmacy id.
     *
     * @return the pharmacy id
     */
    public Integer getPharmacyID() {
        return pharmacyID;
    }

    /**
     * Gets scooter id.
     *
     * @return the scooter id
     */
    public Integer getScooterID() {
        return scooterID;
    }

    /**
     * Gets courier email.
     *
     * @return the courier email
     */
    public String getCourierEmail() {
        return CourierEmail;
    }
}
