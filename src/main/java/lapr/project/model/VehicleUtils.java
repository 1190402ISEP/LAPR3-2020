package lapr.project.model;


import lapr.project.ui.Logger;

/**
 * The type Vehicle utils.
 */
public class VehicleUtils {
    /**
     * Listar states.
     */
    public static void listarStates() {
        StringBuilder s = new StringBuilder();
        s.append("Choose the state:\n");
        for (VehicleState pos : VehicleState.values()) {
            s.append(pos.getId() + " " + pos);
            s.append("\n");
        }
        Logger.log(s.toString());
    }

    /**
     * Gets state by id.
     *
     * @param id the id
     * @return the state by id
     */
    public static VehicleState getStateByID(int id) {
        for (VehicleState pos : VehicleState.values()) {
            if (pos.getId() == id) {
                return pos;
            }
        }
        return null;
    }
}
