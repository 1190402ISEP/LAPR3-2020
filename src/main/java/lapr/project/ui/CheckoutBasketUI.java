package lapr.project.ui;

import lapr.project.controller.CheckoutBasketController;
import lapr.project.model.files.ReadFiles;
import lapr.project.model.CreditCard;
import lapr.project.model.utils.Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Checkout basket ui.
 */
public class CheckoutBasketUI {

    /**
     * Run.
     *
     * @param index the index
     *
     * @throws FileNotFoundException the file not found exception
     */
    public void run(int index) throws FileNotFoundException {

        ArrayList<String[]> matrix = ReadFiles.readFileScenario(Utils.getPath()+"CheckoutBasketScenario.txt");

        // 1 - new credit card   2- existing credit card

        assert matrix != null;
        String[] actions = matrix.get(index);

        int flag = Integer.parseInt(actions[0]);
        Integer option = actions[1].isEmpty() ? null : Integer.parseInt(actions[1]);
        Integer nif = actions[2].isEmpty() ? null : Integer.parseInt(actions[2]);
        int selectedCredits = Integer.parseInt(actions[3]);


        CheckoutBasketController controller = new CheckoutBasketController();

        controller.getClient();

        Logger.log("----------------------------Checking out the basket-----------------------------------");

        Logger.log("1- new credit card 2- Use existing credid card");
        Logger.log("Select Option: " + flag);

        if (flag == 2) {
            List<CreditCard> list = controller.getCreditCardsClient();

            Utils.listar(list);
            Logger.log("Selected Option: " + option);

            controller.getCardByOption(list, option);


        } else if (flag == 1) {
            controller.newCreditCard(index);
        }

        if (!controller.hasClientNif()) {
            if (nif != null) {
                controller.setNif(nif);
            } else {
                Logger.log("You must enter a NIF!");
                return;
            }

        }

        int credits = controller.getClientCreditsByEmail();

        Logger.log("Credits Available: " + credits + "\nPlease insert how many do want to use");

        Logger.log("Selected Credits: " + selectedCredits);

       if( controller.newOrder(selectedCredits)) {
           Logger.log("Order done successfully!");

       } else {
           Logger.log("It was not possible to process the order!");
       }

       Logger.log("-----------------------------------------------------------------------");

    }

}
