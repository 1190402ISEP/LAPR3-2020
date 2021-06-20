package lapr.project.model;

import java.util.Objects;

/**
 * The type Park.
 */
public class Park {

    private int id;
    private int pharmacyID;
    private int maxCapacity;
    private int parkTypeID;
    private int currentOcupation;
    private int totalVoltage;

    /**
     * Instantiates a new Park.
     *
     * @param pharmacyID       the pharmacy id
     * @param totalVoltage     the total voltage
     * @param maxCapacity      the max capacity
     * @param parkTypeID       the park type id
     * @param currentOcupation the current ocupation
     */
    public Park( int pharmacyID, int totalVoltage, int maxCapacity, int parkTypeID, int currentOcupation ) {
        this.id = -1;
        setPharmacyID( pharmacyID );
        setMaxCapacity( maxCapacity );
        setParkType( parkTypeID );
        setCurrentOcupation( currentOcupation );
        setTotalVoltage( totalVoltage );
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getID() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param ID the id
     */
    public void setID( int ID ) {
        if ( ID > 0 ) {
            this.id = ID;
        }
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
     * Sets pharmacy id.
     *
     * @param pharmacyID the pharmacy id
     */
    public void setPharmacyID( int pharmacyID ) {
        if ( pharmacyID > 0 ) {
            this.pharmacyID = pharmacyID;
        }
    }

    /**
     * Gets max capacity.
     *
     * @return the max capacity
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Sets max capacity.
     *
     * @param maxCapacity the max capacity
     */
    public void setMaxCapacity( int maxCapacity ) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * Gets park type.
     *
     * @return the park type
     */
    public int getParkType() {
        return parkTypeID;
    }

    /**
     * Sets park type.
     *
     * @param parkType the park type
     */
    public void setParkType( int parkType ) {
        this.parkTypeID = parkType;
    }

    /**
     * Gets current ocupation.
     *
     * @return the current ocupation
     */
    public int getCurrentOcupation() {
        return currentOcupation;
    }

    /**
     * Sets current ocupation.
     *
     * @param currentOcupation the current ocupation
     */
    public void setCurrentOcupation(int currentOcupation) {
        if ( currentOcupation <= this.maxCapacity ) {
            this.currentOcupation = currentOcupation;
        }
    }

    /**
     * Gets total voltage.
     *
     * @return the total voltage
     */
    public int getTotalVoltage() {
        return totalVoltage;
    }

    /**
     * Sets total voltage.
     *
     * @param totalVoltage the total voltage
     */
    public void setTotalVoltage(int totalVoltage) {
        if ( totalVoltage > 0 ) {
            this.totalVoltage = totalVoltage;
        }
    }

    @Override
    public String toString() {
        return "Park {\n identification -> " + id +
                ", belongs to pharmacy with ID -> " + pharmacyID +
                ", maximum capacity -> " + maxCapacity +
                ", park type ID -> " + parkTypeID +
                ", current ocupation -> " + currentOcupation +
                ", total voltage shared between charging spots->" + totalVoltage +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Park park = (Park) o;
        return id == park.id && pharmacyID == park.pharmacyID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pharmacyID);
    }
}

