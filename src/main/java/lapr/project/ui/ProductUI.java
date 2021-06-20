package lapr.project.ui;

import lapr.project.controller.ProductController;
import lapr.project.model.files.ReadFiles;
import lapr.project.model.utils.Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The type Product ui.
 */
public class ProductUI {

    private static final ProductController pc = new ProductController();

    private static int index = 0;

    /**
     * Read file array list.
     *
     * @return the array list
     * @throws FileNotFoundException the file not found exception
     */
    public static ArrayList<String[]> readFile() throws FileNotFoundException {
        return ReadFiles.readFileScenario(Utils.getPath() + "ReadProductScenario.txt");
    }

    /**
     * Read all product scenarios.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public static void readAllProductScenarios() throws FileNotFoundException {
        ArrayList<String[]> ScootersList = readFile();

        boolean flag = false;
        int count = 0;

        for (String[] product : ScootersList) {

            if (count == index) {
                switch (product[0]) {
                    case "c": //create product
                        createProduct(product);
                        break;
                    case "u": //update product
                        updateProduct(product);
                        break;
                    case "r": //remove product
                        removeProduct(product);
                        break;
                    case "us": //remove product
                        updateStock(product);
                        break;
                    case "#":
                        flag = true;
                        break;
                }
            } else {
                if (product[0].equals("#")) {
                    count++;
                }
            }


            if (flag) {
                break;
            }

        }

        index++;
    }


    /**
     * Create product.
     *
     * @param product the product
     */
    public static void createProduct(String[] product) {
        Logger.log("--------------------------------------------Creating a product---------------------------------------");
        pc.createProduct(product[1], Integer.parseInt(product[2]), Float.parseFloat(product[3]), Float.parseFloat(product[4]), Float.parseFloat(product[5]), product[6]);
        Logger.log("Product with barcode " + product[6] + " created with success!");
        Logger.log("-----------------------------------------------------------------------------------------------------\n");
    }

    /**
     * Remove product.
     *
     * @param product the product
     */
    public static void removeProduct(String[] product) {
        pc.removeProduct(Integer.parseInt(product[1]));
        System.err.println("Product removed with success!");
    }

    /**
     * Update product.
     *
     * @param prod the prod
     */
    public static void updateProduct(String[] prod) {
        pc.updateProduct(Integer.parseInt(prod[1]), prod[2], Integer.parseInt(prod[3]), Float.parseFloat(prod[4]), Float.parseFloat(prod[5]), Float.parseFloat(prod[6]), prod[7]);
        System.err.println("Product updated with success!");
    }

    /**
     * Update stock.
     *
     * @param stockToUpdate the stock to update
     */
    public static void updateStock(String[] stockToUpdate) {
        pc.updateStock(Integer.parseInt(stockToUpdate[1]), Integer.parseInt(stockToUpdate[2])); /*product name and quantity of stock passed*/
        System.err.println("Stock updated with success!");
    }
}
