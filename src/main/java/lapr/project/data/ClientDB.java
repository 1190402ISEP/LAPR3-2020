package lapr.project.data;

import lapr.project.model.*;
import lapr.project.controller.task.OrderBackPharmacyTask;
import lapr.project.ui.Logger;

import lapr.project.ui.Print;
import oracle.jdbc.OracleTypes;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Client db.
 */
public class ClientDB extends DataHandler {

    /**
     * New credit card boolean.
     *
     * @param card  the card
     * @param email the email
     * @return the boolean
     */
    public boolean newCreditCard(CreditCard card, String email) {
        String number = card.getNumber();
        int cvv = card.getCvv();
        Date expirateDate = new Date(card.getExpirateDate().getTime());
        return newCreditCard(number, cvv, expirateDate, email);
    }


    private boolean newCreditCard(String number, int cvv, Date expirateDate, String email) {
        CallableStatement callStmt = null;


        try {


            callStmt = getConnection().prepareCall("{ call addCreditCard(?,?,?,?) }");

            callStmt.setString(1, number);
            callStmt.setInt(2, cvv);
            callStmt.setDate(3, expirateDate);
            callStmt.setString(4, email);

            callStmt.execute();

            Logger.log("-----------------------");
            Logger.log("Credit card added!");
            Logger.log("Number: " + number);
            Logger.log("CVV: " + cvv);
            Logger.log("Expirate Date: " + expirateDate);
            Logger.log("Client Email: " + email);
            Logger.log("-----------------------");
            return true;

        } catch (SQLException e) {
           Logger.log( e.getMessage() );
            return false;

        } finally {
            try {
                assert callStmt != null;
                callStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * Gets client credit cards by email.
     *
     * @param email the email
     * @return the client credit cards by email
     */
    public List<CreditCard> getClientCreditCardsByEmail(String email) {

        List<CreditCard> list = new ArrayList<>();

        CallableStatement callStmt;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getClientCreditCardsByEmail(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setString(1, email);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {

                String number = rSet.getString(1);
                int cvv = rSet.getInt(3);
                java.util.Date expirateDate = rSet.getDate(4);


                list.add(new CreditCard(number, cvv, expirateDate));
            }


            return list;
        } catch (SQLException e) {
            Logger.log( e.getMessage() );
            return null;
        } finally {
            closeAll();
        }
    }

    /**
     * Has client nif boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean hasClientNif(String email) {


        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{call hasClientNif(?,?) }");


            callStmt.setString(1, email);
            callStmt.registerOutParameter(2, OracleTypes.INTEGER);


            callStmt.execute();

            Integer rSet = (Integer) callStmt.getObject(2);

            return rSet == 1;


        } catch (SQLException e) {
            Logger.log(e.getMessage());

        } finally {
            try {
                assert callStmt != null;
                callStmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


    /**
     * Gets client last added card.
     *
     * @param email the email
     * @return the client last added card
     */
    public CreditCard getClientLastAddedCard(String email) {


        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{? = call getClientLastAddedCard(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, email);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String number = rSet.getString(1);
                String s = rSet.getString(2);
                int cvv = rSet.getInt(3);
                java.util.Date expirateDate = rSet.getDate(4);
                Date d = rSet.getDate(5);

                return new CreditCard(number, cvv, expirateDate);
            }


        } catch (SQLException e) {
            Logger.log(e.getMessage() + "ola");
        } finally {
            try {
                assert callStmt != null;
                callStmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;

    }

    private int getIntegerBDClientBy(String email, String function) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall(function);


            callStmt.setString(1, email);
            callStmt.registerOutParameter(2, OracleTypes.INTEGER);

            callStmt.execute();


            return (Integer) callStmt.getObject(2);


        } catch (SQLException e) {
            Logger.log(e.getMessage());
        } finally {
            try {
                assert callStmt != null;
                callStmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }

    /**
     * Gets client nif by email.
     *
     * @param email the email
     * @return the client nif by email
     */
    public int getClientNifByEmail(String email) {
        String function = "{call getClientNifByEmail(?,?) }";
        return getIntegerBDClientBy(email, function);
    }

    /**
     * Gets client credits by email.
     *
     * @param email the email
     * @return the client credits by email
     */
    public int getClientCreditsByEmail(String email) {
        String function = "{call getClientCreditsByEmail(?,?) }";
        return getIntegerBDClientBy(email, function);

    }

    /**
     * Gets products in basket by client email.
     *
     * @param email the email
     * @return the products in basket by client email
     */
    public List<BasketProduct> getProductsInBasketByClientEmail(String email) {
        List<BasketProduct> list = new ArrayList<>();

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getProductsInBasketByClientEmail(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setString(2, email);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {

                String barcode = rSet.getString(2);
                int quantity = rSet.getInt(3);

                list.add(new BasketProduct(barcode, quantity));
            }


            return list;
        } catch (SQLException e) {
            Logger.log(e.getMessage());
            return null;
        } finally {
            try {
                assert callStmt != null;
                callStmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * New order boolean.
     *
     * @param email        the email
     * @param products     the products
     * @param card         the card
     * @param nif          the nif
     * @param creditsToUse the credits to use
     * @param pharmacyID   the pharmacy id
     * @return the boolean
     */
    public boolean newOrder(String email, List<BasketProduct> products, CreditCard card, int nif, int creditsToUse, int pharmacyID) {
        Map<String, Integer> productsNoStock = new HashMap<>();
        CallableStatement callStmt1 = null;
        int orderID;
        try {
            callStmt1 = getConnection().prepareCall("{ call createOrder(?,?,?,?,?) }");


            callStmt1.setString(1, email);
            String cardNumber = card.getNumber();
            callStmt1.setString(2, cardNumber);
            callStmt1.setInt(3, nif);
            callStmt1.registerOutParameter(4, OracleTypes.INTEGER);
            callStmt1.setInt(5, creditsToUse);

            callStmt1.execute();


            orderID = (Integer) callStmt1.getObject(4);


        } catch (SQLException e) {
            Logger.log(e.getMessage());
            return false;
        } finally {
            try {
                assert callStmt1 != null;
                callStmt1.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //----------------------------------------------------------------------------


        CallableStatement callStmt3 = null;


        try {
            callStmt3 = getConnection().prepareCall("{call createInvoice(?,?) }");

            float deliveryFee = 5.0F;

            callStmt3.setInt(1, orderID);
            callStmt3.setFloat(2, deliveryFee);


            callStmt3.execute();


        } catch (SQLException e) {
           Logger.log(e.getMessage());
            return false;
        } finally {
            try {
                assert callStmt3 != null;
                callStmt3.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        //-----------------------------------------------------------------------------

        for (BasketProduct p : products) {

            CallableStatement callStmt2 = null;

            try {
                callStmt2 = getConnection().prepareCall("{call registerOrderProduct(?,?,?,?,?) }");


                callStmt2.setInt(1, orderID);
                callStmt2.setString(2, p.getBarcode());
                callStmt2.setInt(3, p.getQuantity());
                callStmt2.setInt(4, pharmacyID);
                callStmt2.registerOutParameter(5, OracleTypes.INTEGER);


                callStmt2.execute();


                Integer stockNeeded = (Integer) callStmt2.getObject(5);

                if (stockNeeded > 0) {
                    productsNoStock.put(p.getBarcode(), stockNeeded);
                }


            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    assert callStmt2 != null;
                    callStmt2.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        discountCredits(orderID, creditsToUse);


//----------------------------------------------------------

        if (!productsNoStock.isEmpty()) {
            OrderBackPharmacyTask task = new OrderBackPharmacyTask(new PlanningDB(), new DeliveryPlanning());
            try {
                task.run(productsNoStock, pharmacyID, orderID);
            } catch (IOException e) {
                //nada
            }
        }
        OrdersQueue.addOrderToQueue(pharmacyID, orderID);

        sendEmailToClientWithInvoice(orderID);

        earnPoints(orderID);

        return true;


    }

    private boolean earnPoints(int orderID) {

        CallableStatement callStmt = null;

        try {
            callStmt = getConnection().prepareCall("{call earnPoints(?) }");


            callStmt.setInt(1, orderID);

            callStmt.execute();


            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                assert callStmt != null;
                callStmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean sendEmailToClientWithInvoice(int orderID) {

        CallableStatement callStmt = null;

        try {
            callStmt = getConnection().prepareCall("{?= call sendEmailToClientWithInvoice(?) }");


            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setInt(2, orderID);

            callStmt.execute();

            ResultSet rset = (ResultSet) callStmt.getObject(1);

            while (rset.next()) {
                int id = rset.getInt(1);
                int nif = rset.getInt(2);
                int usedCredits = rset.getInt(3);
                java.util.Date orderDate = rset.getDate(4);
                String nomeFarmacia = rset.getString(5);
                float totalWeight = (float) rset.getDouble(6);
                float totalPrice = (float) rset.getDouble(7);
                float deliveryFee = (float) rset.getDouble(8);
                float productsTotalPrice = (float) rset.getDouble(9);
                float totalWithDiscount = (float) rset.getDouble(10);
                String email = rset.getString(11);
                Print.sendEmailInvoiceClient(id, nif, usedCredits, orderDate, nomeFarmacia, totalWeight, totalPrice, deliveryFee, productsTotalPrice, totalWithDiscount, email);


            }


            return true;

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                assert callStmt != null;
                callStmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    private boolean discountCredits(int orderID, int creditsToUse) {
        CallableStatement callStmt = null;

        try {
            callStmt = getConnection().prepareCall("{call discountCredits(?,?) }");

            callStmt.setInt(1, orderID);
            callStmt.setInt(2, creditsToUse);

            callStmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                assert callStmt != null;
                callStmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Change order to canceled boolean.
     *
     * @param orderID the order id
     * @return the boolean
     */
    public boolean changeOrderToCanceled(int orderID) {
        CallableStatement callStmt = null;
        boolean flag = true;
        try {
            callStmt = getConnection().prepareCall("{ call changeOrderToCanceled(?) }");

            callStmt.setInt(1, orderID);

            callStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        } finally {
            try {
                assert callStmt != null;
                callStmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        OrdersQueue.removeOrderFromQueue(orderID);

        return flag;
    }

    /**
     * Gets client orders by email.
     *
     * @param email the email
     * @return the client orders by email
     */
    public List<Order> getClientOrdersByEmail(String email) {

        List<Order> list = new ArrayList<>();

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getClientOrdersByEmail(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setString(2, email);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {

                int id = rSet.getInt(1);
                LocalDate date = rSet.getDate(2).toLocalDate();
                int orderState = rSet.getInt(3);

                list.add(new Order(id, LocalDateTime.of(date, LocalTime.now()), orderState));
            }


            return list;
        } catch (SQLException e) {
            Logger.log(e.getMessage());
            return null;
        } finally {
            try {

                assert callStmt != null;

                callStmt.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }
    }



}

