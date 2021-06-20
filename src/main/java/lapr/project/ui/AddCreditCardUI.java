package lapr.project.ui;

import lapr.project.controller.AddCreditCardController;
import lapr.project.model.files.ReadFiles;
import lapr.project.model.utils.Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

/**
 * The type Add credit card ui.
 */
public class AddCreditCardUI {

    /**
     * Run.
     *
     * @param index the index
     * @throws FileNotFoundException the file not found exception
     */
    public void run(int index) throws FileNotFoundException {


        ArrayList<String[]> matrix = ReadFiles.readFileScenario(Utils.getPath() + "AddCardScenario.txt");

        assert matrix != null;
        String[] actions = matrix.get(index);

        String number = actions[0];
        int cvv = Integer.parseInt(actions[1]);

        Date d = Utils.createDate(actions[2]);

        AddCreditCardController controller = new AddCreditCardController();

        controller.newCreditCard(number, cvv, d);
    }
}
