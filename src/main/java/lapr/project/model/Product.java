package lapr.project.model;

import java.util.Objects;

/**
 * The type Product.
 */
public class Product {
    private String name;
    private int stock;
    private float price;
    private float weight;
    private float iva;
    private String barcode;

    /**
     * Instantiates a new Product.
     *
     * @param name    the name
     * @param stock   the stock
     * @param price   the price
     * @param weight  the weight
     * @param iva     the iva
     * @param barCode the bar code
     */
    public Product(String name, int stock, float price, float weight, float iva, String barCode) {
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.weight = weight;
        this.iva = iva;
        this.barcode = barCode;
    }

    /**
     * Instantiates a new Product.
     *
     * @param barcode the barcode
     * @param name    the name
     * @param price   the price
     * @param iva     the iva
     */
    public Product(String barcode, String name, float price, float iva) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.iva = iva;
    }


    /**
     * Instantiates a new Product.
     *
     * @param barCode the bar code
     */
    public Product(String barCode) {
        this.barcode = barCode;
    }

    /**
     * Instantiates a new Product.
     *
     * @param copy the copy
     */
    public Product(Product copy) {
        this.name = copy.getName();
        this.stock = copy.getStock();
        this.price = copy.getPrice();
        this.weight = copy.getWeight();
        this.iva = copy.getIva();
        this.barcode = copy.getBarcode();
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
     * Gets stock.
     *
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * Gets iva.
     *
     * @return the iva
     */
    public float getIva() {
        return iva;
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets stock.
     *
     * @param stock the stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * Sets iva.
     *
     * @param iva the iva
     */
    public void setIva(float iva) {
        this.iva = iva;
    }

    /**
     * Sets barcode.
     *
     * @param barcode the barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return String.format("Product{ name= %s | price= %f ",
                name, returnPriceWithIva());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return barcode.equals(product.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode);
    }

    /**
     * Return price with iva float.
     *
     * @return the float
     */
    public float returnPriceWithIva(){
        return (price + price*(iva/100));
    }
}
