package lapr.project.model;

import lapr.project.ui.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Orders queue.
 */
public class OrdersQueue {

    /**
     * The Orders.
     */
    static Map<Integer, List<Integer>> orders = new HashMap<>();

    private OrdersQueue() {
    }

    /**
     * Add order to queue boolean.
     *
     * @param pharmacyID the pharmacy id
     * @param orderID    the order id
     * @return the boolean
     */
    public static boolean addOrderToQueue(int pharmacyID, int orderID) {
        List<Integer> list = orders.get(pharmacyID);
        if (list == null) {
            orders.put(pharmacyID, new ArrayList<>());
            list = orders.get(pharmacyID);

        }

        return list.add(orderID);
    }

    /**
     * Remove order from queue boolean.
     *
     * @param orderID the order id
     * @return the boolean
     */
    public static boolean removeOrderFromQueue(int orderID) {
        Map<Integer, List<Integer>> map = getOrders();

        for (List<Integer> order: map.values()) {
            int size=order.size();
            order.remove(Integer.valueOf(orderID));
            if(size>order.size()){
                return true;
            }
        }
        Logger.log("Impossible to remove");
        return false;
    }

    /**
     * Gets orders.
     *
     * @return the orders
     */
    public static Map<Integer, List<Integer>> getOrders() {
        return orders;
    }


    /**
     * Sets orders.
     *
     * @param orders the orders
     */
    public static void setOrders(Map<Integer, List<Integer>> orders) {
        OrdersQueue.orders = orders;
    }

    /**
     * Clear queue boolean.
     *
     * @return the boolean
     */
    public static boolean clearQueue() {
        orders.clear();

        return true;
    }

}