package lapr.project.ui;

import lapr.project.controller.CheckoutBasketController;
import lapr.project.model.Order;
import lapr.project.model.files.ReadFiles;
import lapr.project.model.utils.Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Cancel order ui.
 */
public class CancelOrderUI {

    /**
     * Run.
     *
     * @param index the index
     * @throws FileNotFoundException the file not found exception
     */
    public void run(int index) throws FileNotFoundException {

        ArrayList<String[]> matrix = ReadFiles.readFileScenario(Utils.getPath()+"CancelOrderScenario.txt");

        assert matrix != null;
        String[] actions = matrix.get(index);

        int option = Integer.parseInt(actions[0]);

        CheckoutBasketController controller = new CheckoutBasketController();

        controller.getClient();

        List<Order> list = controller.getClientOrdersByEmail();

        Utils.listar(list);
        Logger.log("--------------Canceling an order------------------");
        Logger.log("Select the order that you want to cancel:");
        Logger.log("Selected Option: " + option);


        //orders.forEach((key, value) -> Logger.log(key + ":" + value));

        if (controller.changeOrderToCanceled(option)){
            Logger.log("Order cancelled successfully!");
        } else{
            Logger.log("Order not cancelled!");
        }

        Logger.log("--------------------------------------------------");

    }

}
