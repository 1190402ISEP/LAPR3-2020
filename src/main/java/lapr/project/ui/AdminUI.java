package lapr.project.ui;

import lapr.project.model.files.ReadFiles;
import lapr.project.model.utils.Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The type Admin ui.
 */
public class AdminUI {
    /**
     * Loop.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public void loop() throws FileNotFoundException {
        ArrayList<String[]> matriz = ReadFiles.readFileScenario(Utils.getPath()+"AdminUserScenario.txt");
        assert matriz != null;
        String[] option = matriz.get(0);

        for (String s : option) {
            switch (s) {
                case "1": //Scooter scenario
                    ScooterUI.readAllScooterScenarios();
                    break;
                case "2": //Courier scenario
                    ManageCourierUI.readAllCourierScenarios();
                    break;
                case "3":
                    DroneUI.readAllDroneScenarios();
                    break;
                case "4":
                    PharmacyOwnerUI.readAllPharmacyOwnersScenarios(); //criar pharmacyOwner etc
                    break;
                case "5":
                    PharmacyUI.readAllPharmacyScenarios(); // criar farmacia etc
                    break;
                case "6":
                    ParkUI.readAllScenarios();
                    break;
                case "0":
                    return;
            }
        }
    }
}
