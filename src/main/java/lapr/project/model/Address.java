package lapr.project.model;

import java.util.Objects;

/**
 * The type Address.
 */
public class Address {

    private String coordinates;
    private String street;
    private String postalCode;
    private int doorNumber;

    /**
     * Instantiates a new Address.
     *
     * @param coordinates the coordinates
     * @param street      the street
     * @param postalCode  the postal code
     * @param doorNumber  the door number
     */
    public Address ( String coordinates, String street,
                     String postalCode, int doorNumber ) {
        this.coordinates = coordinates;
        this.street = street;
        this.postalCode = postalCode;
        this.doorNumber = doorNumber;
    }

    /**
     * Gets coordinates.
     *
     * @return the coordinates
     */
    public String getCoordinates() {
        return coordinates;
    }

    /**
     * Sets coordinates.
     *
     * @param coordinates the coordinates
     */
    public void setCoordinates( String coordinates ) {
        this.coordinates = coordinates;
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet ( String street ) {
        this.street = street;
    }

    /**
     * Gets postal code.
     *
     * @return the postal code
     */
    public String getPostalCode () {
        return postalCode;
    }

    /**
     * Sets postal code.
     *
     * @param postalCode the postal code
     */
    public void setPostalCode ( String postalCode ) {
        this.postalCode = postalCode;
    }

    /**
     * Gets door number.
     *
     * @return the door number
     */
    public int getDoorNumber () {
        return doorNumber;
    }

    /**
     * Sets door number.
     *
     * @param doorNumber the door number
     */
    public void setDoorNumber ( int doorNumber ) {
        this.doorNumber = doorNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return coordinates.equals( address.coordinates );
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, street, postalCode, doorNumber);
    }

    @Override
    public String toString() {
        return "Address{" +
                "coordinates='" + coordinates + '\'' +
                ", street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", doorNumber=" + doorNumber +
                '}';
    }
}
