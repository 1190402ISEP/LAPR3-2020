package lapr.project.model;

import java.util.Objects;

/**
 * The type Pharmacy.
 */
public class Pharmacy {

    private int id;
    private String designation;
    private String address;
    private String pharmacyOwner;

    /**
     * Instantiates a new Pharmacy.
     *
     * @param id            the id
     * @param designation   the designation
     * @param address       the address
     * @param pharmacyOwner the pharmacy owner
     */
    public Pharmacy( int id, String designation, String address, String pharmacyOwner ) {
        this.id = id;
        this.designation = designation;
        this.address = address;
        this.pharmacyOwner = pharmacyOwner;
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
     * Sets id.
     *
     * @param id the id
     */
    public void setId( int id ) {
        this.id = id;
    }

    /**
     * Gets designation.
     *
     * @return the designation
     */
    public String getDesignation () {
        return designation;
    }

    /**
     * Sets designation.
     *
     * @param designation the designation
     */
    public void setDesignation ( String designation ) {
        this.designation = designation;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress( String address ) {
        this.address = address;
    }

    /**
     * Gets pharmacy owner.
     *
     * @return the pharmacy owner
     */
    public String getPharmacyOwner () {
        return pharmacyOwner;
    }

    /**
     * Sets pharmacy owner.
     *
     * @param pharmacyOwner the pharmacy owner
     */
    public void setPharmacyOwner ( String pharmacyOwner ) {
        this.pharmacyOwner = pharmacyOwner;
    }

    @Override
    public String toString () {
        return "Pharmacy{" + "id=" + id + ", designation=" + designation + ", address=" + address +
                ", pharmacyOwner=" + pharmacyOwner + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pharmacy pharmacy = (Pharmacy) o;
        return this.id == pharmacy.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, designation, address, pharmacyOwner);
    }
}
