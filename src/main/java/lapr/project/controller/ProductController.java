package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.Pharmacy;
import lapr.project.model.Product;
import lapr.project.model.User;


/**
 * The type Product controller.
 */
public class ProductController {

    private final ProductDB productDB;
    private final PharmacyDB pharmacyDB;
    private final AddressDB addDB;
    private User user;


    /**
     * Instantiates a new Product controller.
     */
    public ProductController() {
        productDB = new ProductDB();
        pharmacyDB = new PharmacyDB();
        addDB = new AddressDB();
    }

    /**
     * Instantiates a new Product controller.
     *
     * @param pharmacyDB the pharmacy db
     * @param addDB      the add db
     * @param productDB  the product db
     */
    public ProductController(PharmacyDB pharmacyDB, AddressDB addDB, ProductDB productDB) {
        this.productDB = productDB;
        this.pharmacyDB = pharmacyDB;
        this.addDB = addDB;
    }


    /**
     * Create product boolean.
     *
     * @param name    the name
     * @param stock   the stock
     * @param price   the price
     * @param weight  the weight
     * @param iva     the iva
     * @param barCode the bar code
     * @return the boolean
     */
    public boolean createProduct(String name, int stock, float price, float weight, float iva, String barCode) {
        user = UserSession.getInstance().getUser();
        int pharmacyID = pharmacyDB.getPharmacyByUserEmail(user.getEmail()).getId();
        Product product = new Product(name, stock, price, weight, iva, barCode);
        return productDB.productHandlerCreate(product, pharmacyID);
    }


    /**
     * Remove product boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean removeProduct(int id) {
        user = UserSession.getInstance().getUser();
        int pharmacyID = pharmacyDB.getPharmacyByUserEmail(user.getEmail()).getId();
        return productDB.productHandlerRemove(id, pharmacyID);
    }

    /**
     * Update product boolean.
     *
     * @param id      the id
     * @param name    the name
     * @param stock   the stock
     * @param price   the price
     * @param weight  the weight
     * @param iva     the iva
     * @param barCode the bar code
     * @return the boolean
     */
    public boolean updateProduct(int id, String name, int stock, float price, float weight, float iva, String barCode) {
        user = UserSession.getInstance().getUser();
        int pharmacyID = pharmacyDB.getPharmacyByUserEmail(user.getEmail()).getId();
        return productDB.productHandlerUpdate(id, name, stock, price, weight, iva, barCode, pharmacyID);
    }

    /**
     * Update stock boolean.
     *
     * @param id    the id
     * @param stock the stock
     * @return the boolean
     */
    public boolean updateStock(int id, int stock) {

        PharmacyController pharmacyController = new PharmacyController(pharmacyDB, addDB);
        Pharmacy pharmacy = pharmacyController.getPharmacyByUserEmail();
        int farmId = pharmacy.getId();
        return productDB.updateStockHandler(id, stock, farmId);

    }
}
