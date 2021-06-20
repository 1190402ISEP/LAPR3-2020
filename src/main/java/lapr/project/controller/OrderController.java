package lapr.project.controller;

import lapr.project.data.OrderDB;
import lapr.project.model.Order;

import java.util.List;

/**
 * The type Order controller.
 */
public class OrderController {

	private final OrderDB orderDB;

    /**
     * Instantiates a new Order controller.
     */
    public OrderController() {
    	orderDB = new OrderDB();
	}

    /**
     * Instantiates a new Order controller.
     *
     * @param orderDB the order db
     */
    public OrderController( OrderDB orderDB ) {
		this.orderDB = orderDB;
	}

    /**
     * Show list.
     *
     * @return the list
     */
    public List<Order> show () {
		return this.orderDB.show();
	}

    /**
     * Check status string.
     *
     * @param orderId the order id
     * @return the string
     */
    public String checkStatus ( int orderId ) {
		return orderDB.checkOrderStatus( orderId );
	}
}
