package lapr.project.ui;


import lapr.project.controller.task.ScooterDeliveryPlanningTask;
import lapr.project.data.DeleteDataDB;
import lapr.project.model.DeliveryPlanning;
import lapr.project.model.VehicleType;
import lapr.project.model.files.ReadFiles;
import lapr.project.model.utils.Utils;
import lapr.project.model.utils.XmlParser;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

/**
 * The type Main.
 *
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {

    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     * @throws IOException            the io exception
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, ParserConfigurationException, SAXException {

        Utils.start("Scenario.txt");

        loadSQL();

        DeliveryPlanning plan = new DeliveryPlanning();

        System.err.println("Please wait, the scenerio ("+Utils.getPath()+") is being read...");

        ArrayList<String[]> matriz = ReadFiles.readFileScenario(Utils.getPath() + "ScenarioController.txt");

        assert matriz != null;
        String[] op = matriz.get(0);

        float passos = (float) op.length;
        float atual = 0f;

        boolean flagCatch = false;
        LoginUI ui = new LoginUI();
        int loginIndex = 0;
        int registerIndex = 0;
        int[] clientLoginIndex = new int[1];
        clientLoginIndex[0] = 0;
        try {
            for (String s : op) {
                float percent = (atual / passos) * 100;
                System.err.println(String.format("%.2f", percent) + "%...");
                atual++;
                switch (s) {

                    case "1": //login
                        ui.login(loginIndex, clientLoginIndex);
                        loginIndex++;
                        break;
                    case "2": //register
                        ui.register(registerIndex);
                        registerIndex++;
                        break;
                    case "3":
                        StatsUI.run();
                        break;
                    case "0":
                        planDelivery(VehicleType.SCOOTER);
                        break;
                    case "4":
                        planDelivery(VehicleType.DRONE);
                        break;
                    case "5": // sem restriçoes e xml

                        plan.start();
                        break;
                    case "6": // para iniciar com o xml

                        plan.startWithXML();
                        break;


                }
                if (s.equals("-1")) {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            flagCatch = true;
        } finally {
            if (!flagCatch) {
                System.err.println("100% Completed!");
                System.err.println("Scenario read and done successfully! Output files created.");
            }


            DeleteDataDB delete = new DeleteDataDB();
            delete.deleteData(Objects.requireNonNull(ReadFiles.readFileScenario(Utils.getPath() + "Delete.txt")));

            System.exit(0);

        }
    }

    public static void planDelivery(VehicleType type) throws Exception {
        ScooterDeliveryPlanningTask task = new ScooterDeliveryPlanningTask();
        task.run(type); // para começar o planning
    }

    /**
     * Load sql.
     */
    public static void loadSQL() {

        try {
            Properties properties =
                    new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

