package lapr.project.ui;

import lapr.project.controller.AddCreditCardController;
import lapr.project.controller.UserController;
import lapr.project.data.UserSession;
import lapr.project.model.files.ReadFiles;
import lapr.project.model.utils.Utils;


import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


/**
 * The type Login ui.
 */
public class LoginUI {
    /**
     * The constant sc.
     */
    public static final Scanner sc = new Scanner(System.in);
    private static final String ADMINEMAIL = "admin";
    private static final String ADMINPASS = "admin";

    private final UserController uc = new UserController();

    /**
     * The enum Usertype.
     */
    enum USERTYPE {
        /**
         * Client usertype.
         */
        CLIENT,
        /**
         * Pharmacy owner usertype.
         */
        PHARMACY_OWNER
    }

    /**
     * Login.
     *
     * @param index the index
     * @throws FileNotFoundException the file not found exception
     */
    public void login(int index, int[] clientLoginIndex) throws FileNotFoundException {
        ArrayList<String[]> matriz = ReadFiles.readFileScenario(Utils.getPath() + "LoginUserScenario.txt");
        assert matriz != null;
        String[] op = matriz.get(index);

        login(op[0], op[1], index, clientLoginIndex);
    }

    /**
     * Login.
     *
     * @param email the email
     * @param pass  the pass
     * @param index the index
     * @throws FileNotFoundException the file not found exception
     */
    public void login(String email, String pass, int index, int[] clientLoginIndex) throws FileNotFoundException {

        if (email.equalsIgnoreCase(ADMINEMAIL) && pass.equalsIgnoreCase(ADMINPASS)) {
            //AdminMenu start
            AdminUI Aui = new AdminUI();
            Aui.loop();
        } else if (uc.authenticate(email, pass)) {
            if (UserSession.getInstance().getUser().getRole().equals(USERTYPE.CLIENT.name())) {
               // ClientMenu start;
                ClientUI Cui = new ClientUI();
                Cui.loop(clientLoginIndex[0]);
                clientLoginIndex[0] += 1;
            } else if (UserSession.getInstance().getUser().getRole().equals(USERTYPE.PHARMACY_OWNER.name())) {
                //PharmacyOwner start
                PharmacyOwnerUI Pui = new PharmacyOwnerUI();
                Pui.loop();
            }
        } else {
            System.err.println("\nThe E-mail or Password are incorrect.\n");
        }
    }

    /**
     * Register.
     *
     * @param index the index
     * @throws FileNotFoundException the file not found exception
     */
    public void register(int index) throws FileNotFoundException {

        ArrayList<String[]> matrix = ReadFiles.readFileScenario(Utils.getPath() + "RegisterClientScenario.txt");
        assert matrix != null;
        String[] actions = matrix.get(index);

        try {
            uc.addClient(actions[0], Integer.parseInt(actions[1]), actions[2], actions[3], actions[4], actions[5], actions[6], Integer.parseInt(actions[7]));
        } catch (SQLException e) {
           // nada

        }
        //Credit Card UC calling
        try {
            Date d = Utils.createDate(actions[10]);
            UserSession.getInstance().getUser().setEmail(actions[0]);
            AddCreditCardController CreditCardController = new AddCreditCardController();

            CreditCardController.newCreditCard(actions[8], Integer.parseInt(actions[9]), d);
            UserSession.getInstance().setUser(null);
        } catch (ArrayIndexOutOfBoundsException e) {
            //skip
        }

    }

}
