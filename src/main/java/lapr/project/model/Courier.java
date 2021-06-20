package lapr.project.model;

import java.util.Objects;

/**
 * The type Courier.
 */
public class Courier {

    private String name;
    private String email;
    private int nif;
    private String niss;
    private CourierState state;


    /**
     * Instantiates a new Courier.
     *
     * @param name  the name
     * @param email the email
     * @param nif   the nif
     * @param niss  the niss
     * @param state the state
     */
    public Courier(String name, String email,int nif, String niss, CourierState state){

        this.name=name;
        this.email=email;
        this.nif=nif;
        this.niss=niss;
        this.state=state;
    }

    /**
     * Instantiates a new Courier.
     *
     * @param name  the name
     * @param email the email
     * @param nif   the nif
     * @param niss  the niss
     */
    public Courier(String name, String email,int nif, String niss){

        this.name=name;
        this.email=email;
        this.nif=nif;
        this.niss=niss;
        this.state = CourierState.AVAILABLE;

    }

    /**
     * Instantiates a new Courier.
     *
     * @param copy the copy
     */
    public Courier(Courier copy) {
        this.name=copy.name;
        this.email=copy.email;
        this.nif=copy.nif;
        this.niss=copy.niss;
        this.state=copy.state;

    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets nif.
     *
     * @return the nif
     */
    public int getNif() {
        return nif;
    }

    /**
     * Sets nif.
     *
     * @param nif the nif
     */
    public void setNif(int nif) {
        this.nif = nif;
    }

    /**
     * Gets niss.
     *
     * @return the niss
     */
    public String getNiss() {
        return niss;
    }

    /**
     * Sets niss.
     *
     * @param niss the niss
     */
    public void setNiss(String niss) {
        this.niss = niss;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public CourierState getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(CourierState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("Name: %s | Email: %s | Nif: %d | Niss: %s | State: %s",
                name, email, nif, niss, state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courier courier = (Courier) o;
        return nif == courier.nif &&
                Objects.equals(name, courier.name) &&
                Objects.equals(email, courier.email) &&
                Objects.equals(niss, courier.niss);
    }

    @Override
    public int hashCode() {
        return 12121212;
    }
}
