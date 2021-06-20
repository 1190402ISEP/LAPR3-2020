package lapr.project.ui;

import lapr.project.model.files.ReadFiles;
import lapr.project.model.utils.Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The type Client ui.
 */
public class ClientUI {

    /**
     * Loop.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public void loop(int clientLoginIndex) throws FileNotFoundException {
        ArrayList<String[]> matriz = ReadFiles.readFileScenario(Utils.getPath() + "ClienteUserScenario.txt");

        assert matriz != null;
        String[] op = matriz.get(0);


        for (String s : op) {
            switch (s) {
                case "1": //new credit card
                    AddCreditCardUI cardUI = new AddCreditCardUI();
                    cardUI.run(clientLoginIndex);

                    break;
                case "2": //add product to basket
                    AddProductToBasketUI ui = new AddProductToBasketUI();
                    ui.run(clientLoginIndex);

                    break;
                case "3":
                    CheckoutBasketUI check = new CheckoutBasketUI();
                    check.run(clientLoginIndex);

                    break;
                case "4":
                    CancelOrderUI co = new CancelOrderUI();
                    co.run(clientLoginIndex);
                    break;
                case "0":
                    return;
            }
        }
    }
}
