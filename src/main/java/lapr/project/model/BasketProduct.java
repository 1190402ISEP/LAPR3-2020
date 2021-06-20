package lapr.project.model;

import java.util.Objects;

/**
 * The type Basket product.
 */
public class BasketProduct {

    private String barcode;
    private int quantity;

    /**
     * Instantiates a new Basket product.
     *
     * @param barcode  the barcode
     * @param quantity the quantity
     */
    public BasketProduct(String barcode, int quantity) {
        this.barcode = barcode;
        this.quantity = quantity;
    }

    /**
     * Gets barcode.
     *
     * @return the barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Sets barcode.
     *
     * @param barcode the barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasketProduct that = (BasketProduct) o;

        if (quantity != that.quantity) return false;
        return Objects.equals(barcode, that.barcode);
    }

    @Override
    public int hashCode() {
        int result = barcode != null ? barcode.hashCode() : 0;
        result = 31 * result + quantity;
        return result;
    }
}
