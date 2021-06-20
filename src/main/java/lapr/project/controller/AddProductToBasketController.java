package lapr.project.controller;


import lapr.project.data.ProductDB;
import lapr.project.data.UserSession;
import lapr.project.model.Product;
import lapr.project.model.utils.Utils;

import java.util.List;

/**
 * The type Add product to basket controller.
 */
public class AddProductToBasketController {

    private final ProductDB db;
    private List<Product> products;

    /**
     * Instantiates a new Add product to basket controller.
     */
    public AddProductToBasketController() {
        db = new ProductDB();
    }

    /**
     * Instantiates a new Add product to basket controller.
     *
     * @param db the db
     */
    public AddProductToBasketController(ProductDB db) {
        this.db = db;

    }

    /**
     * Gets products list.
     *
     * @return the products list
     */
    public List<Product> getProductsList() {

        products = db.getProductsList();

        return products;
    }

    /**
     * Add product to basket boolean.
     *
     * @param option   the option
     * @param quantity the quantity
     * @return the boolean
     */
    public boolean addProductToBasket(int option, int quantity) {
        Product p = Utils.getItemByOption(products, option);
        String email = UserSession.getInstance().getUser().getEmail();

        return db.addBasketProduct(email, p.getBarcode(), quantity);
    }
}
