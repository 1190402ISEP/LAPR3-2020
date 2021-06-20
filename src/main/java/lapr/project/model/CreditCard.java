package lapr.project.model;

import java.util.Date;

/**
 * The type Credit card.
 */
public class CreditCard {

    private String number;
    private int cvv;
    private Date expirateDate;

    /**
     * Instantiates a new Credit card.
     *
     * @param number       the number
     * @param cvv          the cvv
     * @param expirateDate the expirate date
     */
    public CreditCard(String number, int cvv, Date expirateDate) {
        this.number = number;
        this.cvv = cvv;
        this.expirateDate = expirateDate;
    }

    /**
     * Gets number.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets number.
     *
     * @param number the number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Gets cvv.
     *
     * @return the cvv
     */
    public int getCvv() {
        return cvv;
    }

    /**
     * Sets cvv.
     *
     * @param cvv the cvv
     */
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    /**
     * Gets expirate date.
     *
     * @return the expirate date
     */
    public Date getExpirateDate() {
        return this.expirateDate;
    }

    /**
     * Sets expirate date.
     *
     * @param expirateDate the expirate date
     */
    public void setExpirateDate(Date expirateDate) {
        this.expirateDate = expirateDate;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCard that = (CreditCard) o;

        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }
}
