package lapr.project.ui;

import lapr.project.controller.AddProductToBasketController;
import lapr.project.model.files.ReadFiles;
import lapr.project.model.Product;
import lapr.project.model.utils.Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Add product to basket ui.
 */
public class AddProductToBasketUI {

    /**
     * Run.
     *
     * @param index the index
     * @throws FileNotFoundException the file not found exception
     */
    public void run(int index) throws FileNotFoundException {


        //_--------------------------leitura dos dados------------------------------


        ArrayList<String[]> matrix = ReadFiles.readFileScenario(Utils.getPath() + "SelectProdutScenario.txt");
        //-------------------------------------------------------------------------------

        assert matrix != null;
        String[] actions = matrix.get(index);

        AddProductToBasketController controller = new AddProductToBasketController();

        List<Product> products = controller.getProductsList();

        Utils.listar(products);

        int option = Integer.parseInt(actions[0]);
        int quantity = Integer.parseInt(actions[1]);



        controller.addProductToBasket(option, quantity);


    }


}
