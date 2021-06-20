package lapr.project.controller;

import lapr.project.data.UserDB;
import lapr.project.data.UserSession;
import lapr.project.model.User;

import java.sql.SQLException;

/**
 * The type User controller.
 */
public class UserController {
    private final UserDB userDB;

    /**
     * Instantiates a new User controller.
     */
    public UserController() {
        this.userDB = new UserDB();
    }

    /**
     * Instantiates a new User controller.
     *
     * @param db the db
     */
    public UserController(UserDB db) {
        this.userDB = db;
    }

    /**
     * Authenticate boolean.
     *
     * @param email    the email
     * @param password the password
     * @return the boolean
     */
    public boolean authenticate(String email, String password) {
            User user = new User(email, password);
            if(userDB.getUserPaper(email, password, 1) == 1) {
                user.setRole("CLIENT");
                UserSession.getInstance().setUser(user);
            } else if (userDB.getUserPaper(email, password, 2) == 1) {
                user.setRole("PHARMACY_OWNER");
                UserSession.getInstance().setUser(user);
            } else {
                return false;
            }
        return true;
    }

    /**
     * Add client boolean.
     *
     * @param email       the email
     * @param nif         the nif
     * @param name        the name
     * @param password    the password
     * @param coordinates the coordinates
     * @param street      the street
     * @param postalCode  the postal code
     * @param doorNumber  the door number
     * @return the boolean
     * @throws SQLException the sql exception
     */
    public boolean addClient(String email, int nif, String name, String password, String coordinates, String street, String postalCode, int doorNumber) throws SQLException {
        return userDB.addClient(email, nif, name, password, coordinates, street, postalCode, doorNumber);
    }
}
