package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.data.PlanningDB;
import lapr.project.data.UserSession;
import lapr.project.model.*;
import lapr.project.ui.AddCreditCardUI;
import lapr.project.model.utils.Utils;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * The type Checkout basket controller.
 */
public class CheckoutBasketController {

    private final ClientDB db;
    private String email;
    private CreditCard card;
    private int nif;


    /**
     * Instantiates a new Checkout basket controller.
     */
    public CheckoutBasketController() {
        db = new ClientDB();
    }

    /**
     * Instantiates a new Checkout basket controller.
     *
     * @param db the db
     */
    public CheckoutBasketController(ClientDB db) {
        this.db = db;
    }

    /**
     * Gets client.
     *
     * @return the client
     */
    public String getClient() {
        email = UserSession.getInstance().getUser().getEmail();
        return email;
    }

    /**
     * Gets credit cards client.
     *
     * @return the credit cards client
     */
    public List<CreditCard> getCreditCardsClient() {

        return db.getClientCreditCardsByEmail(email);

    }

    /**
     * Gets card by option.
     *
     * @param list   the list
     * @param option the option
     * @return the card by option
     */
    public CreditCard getCardByOption(List<CreditCard> list, int option) {

        card = Utils.getItemByOption(list, option);
        hasClientNif();
        return card;
    }

    /**
     * Gets client last added card.
     *
     * @return the client last added card
     */
    public CreditCard getClientLastAddedCard() {
        card = db.getClientLastAddedCard(email);
        return card;
    }

    /**
     * New credit card.
     *
     * @param index the index
     * @throws FileNotFoundException the file not found exception
     */
    public void newCreditCard(int index) throws FileNotFoundException {

        AddCreditCardUI addCard = new AddCreditCardUI();
        addCard.run(index);
        getClientLastAddedCard();

    }

    /**
     * Has client nif boolean.
     *
     * @return the boolean
     */
    public boolean hasClientNif() {
        boolean flag = db.hasClientNif(email);
        if (flag) {

            nif = db.getClientNifByEmail(email);
        }
        return flag;

    }

    /**
     * Sets nif.
     *
     * @param nif the nif
     * @return the nif
     */
    public boolean setNif(int nif) {
        this.nif = nif;
        return true;
    }

    /**
     * Gets nif.
     *
     * @return the nif
     */
    public int getNif() {

        return nif;
    }


    /**
     * Gets client credits by email.
     *
     * @return the client credits by email
     */
    public int getClientCreditsByEmail() {


        return db.getClientCreditsByEmail(email);
    }

    /**
     * Gets id closest pharmacy.
     *
     * @return the id closest pharmacy
     */
    public int getIdClosestPharmacy() {

        return DeliveryPlanning.getIdClosestPharmacyByUserEmail(email, new PlanningDB());


    }

    /**
     * New order boolean.
     *
     * @param creditsToUse the credits to use
     * @return the boolean
     */
    public boolean newOrder(int creditsToUse) {
        List<BasketProduct> products = db.getProductsInBasketByClientEmail(email);
        return db.newOrder(email, products, card, nif, creditsToUse, getIdClosestPharmacy());
    }

    /**
     * Gets client orders by email.
     *
     * @return the client orders by email
     */
    public List<Order> getClientOrdersByEmail() {
        return db.getClientOrdersByEmail(email);
    }

    /**
     * Change order to canceled boolean.
     *
     * @param option the option
     * @return the boolean
     */
    public boolean changeOrderToCanceled(int option) {
        List<Order> list = db.getClientOrdersByEmail(email);
        Order o = Utils.getItemByOption(list, option);
        return db.changeOrderToCanceled(o.getId());
    }





}
