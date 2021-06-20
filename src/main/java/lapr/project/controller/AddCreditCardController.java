package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.data.UserSession;
import lapr.project.model.CreditCard;
import lapr.project.model.User;

import java.util.Date;

/**
 * The type Add credit card controller.
 */
public class AddCreditCardController {

    private final ClientDB db;

    /**
     * Instantiates a new Add credit card controller.
     */
    public AddCreditCardController() {
        db = new ClientDB();
    }

    /**
     * Instantiates a new Add credit card controller.
     *
     * @param db the db
     */
    public AddCreditCardController(ClientDB db) {
        this.db = db;
    }

    /**
     * New credit card boolean.
     *
     * @param number       the number
     * @param cvv          the cvv
     * @param expirateDate the expirate date
     * @return the boolean
     */
    public boolean newCreditCard(String number, int cvv, Date expirateDate) {

        CreditCard card = new CreditCard(number, cvv, expirateDate);

        UserSession session = UserSession.getInstance();
        User user = session.getUser();

        String email = user.getEmail();

        return db.newCreditCard(card, email);


    }
}
