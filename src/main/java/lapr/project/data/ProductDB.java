package lapr.project.data;

import lapr.project.model.Product;
import lapr.project.ui.Logger;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Product db.
 */
public class ProductDB extends DataHandler {


    /**
     * Product handler create boolean.
     *
     * @param product    the product
     * @param pharmacyID the pharmacy id
     * @return the boolean
     */
    public boolean productHandlerCreate(Product product, int pharmacyID) {
        return createProduct(product.getName(), product.getStock(), product.getPrice(), product.getWeight(), product.getIva(), product.getBarcode(), pharmacyID);
    }

    private boolean createProduct(String name, int stock, float price, float weight, float iva, String barCode, int pharmacyID) {
        boolean created = false;
        try {


            CallableStatement callStt = getConnection().prepareCall("{call addProduct(?,?,?,?,?,?,?)}");
            callStt.setString(1, name);
            callStt.setInt(2, stock);
            callStt.setFloat(3, price);
            callStt.setFloat(4, weight);
            callStt.setFloat(5, iva);
            callStt.setString(6, barCode);
            callStt.setInt(7, pharmacyID);

            callStt.execute();
            created = true;
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return created;
    }

    /**
     * Product handler remove boolean.
     *
     * @param id         the id
     * @param pharmacyID the pharmacy id
     * @return the boolean
     */
    public boolean productHandlerRemove(int id, int pharmacyID) {
        boolean removed = false;
        try {


            CallableStatement callStt = getConnection().prepareCall("{ call removeProduct(?) }");

            callStt.setInt(1, id);
            callStt.setInt(2, pharmacyID);

            callStt.execute();
            removed = true;
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return removed;
    }

    /**
     * Product handler update boolean.
     *
     * @param id         the id
     * @param name       the name
     * @param stock      the stock
     * @param price      the price
     * @param weight     the weight
     * @param iva        the iva
     * @param barCode    the bar code
     * @param pharmacyID the pharmacy id
     * @return the boolean
     */
    public boolean productHandlerUpdate(int id, String name, int stock, float price, float weight, float iva, String barCode, int pharmacyID) {
        boolean updated = false;
        try {
            openConnection();

            CallableStatement callStt = getConnection().prepareCall("{ call updateProduct(?,?,?,?,?,?,?) }");

            callStt.setInt(1, id);
            callStt.setString(2, name);
            callStt.setInt(3, stock);
            callStt.setFloat(4, price);
            callStt.setFloat(5, weight);
            callStt.setFloat(6, iva);
            callStt.setString(7, barCode);
            callStt.setInt(8, pharmacyID);

            callStt.execute();
            updated = true;
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    /**
     * Gets products list.
     *
     * @return the products list
     */
    public List<Product> getProductsList() {
        List<Product> products = new ArrayList<>();

        CallableStatement callStmt;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getProductsList() }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {

                String barcode = rSet.getString(1);
                String name = rSet.getString(2);
                float price = rSet.getFloat(3);
                float iva = rSet.getFloat(4);

                products.add(new Product(barcode, name, price, iva));
            }

            closeAll();

            return products;
        } catch (SQLException e) {
           Logger.log(e.getMessage());
            return null;
        }


    }


    /**
     * Add basket product boolean.
     *
     * @param email    the email
     * @param barcode  the barcode
     * @param quantity the quantity
     * @return the boolean
     */
    public boolean addBasketProduct(String email, String barcode, int quantity) {

        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addBasketProduct(?,?,?) }");

            callStmt.setString(1, email);
            callStmt.setString(2, barcode);
            callStmt.setInt(3, quantity);


            callStmt.execute();

            closeAll();

            Logger.log("Client with email " + email + " added a product to basket added! barcode:" + barcode + " ; quantity: " + quantity);
            return true;

        } catch (SQLException e) {
            Logger.log(e.getMessage());

        }
        return false;

    }

    /**
     * Update stock handler boolean.
     *
     * @param productID  the product id
     * @param stock      the stock
     * @param pharmacyID the pharmacy id
     * @return the boolean
     */
    public boolean updateStockHandler(int productID, int stock, int pharmacyID) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call updateStock(?,?,?) }");


            callStmt.setInt(1, productID);
            callStmt.setInt(2, stock);
            callStmt.setInt(3, pharmacyID);

            callStmt.execute();

            closeAll();

           Logger.log("Stock updated!");
            return true;

        } catch (SQLException e) {
           Logger.log(e.getMessage());

        }
        return false;

    }

    /**
     * Gets pharmacy by owner email.
     *
     * @param email the email
     * @return the pharmacy by owner email
     */
    public int getPharmacyByOwnerEmail(String email) {
        try {
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call getPharmacyByOwnerEmail(?) }");
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setString(2, email);
            callStmt.execute();
            Integer id = (Integer) callStmt.getObject(1);

            closeAll();
            return id;
        } catch (SQLException e) {
            Logger.log(e.getMessage());
            return -1;
        }
    }
}
