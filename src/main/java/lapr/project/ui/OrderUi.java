package lapr.project.ui;

import lapr.project.controller.OrderController;
import lapr.project.model.Order;
import lapr.project.model.utils.Utils;

import java.util.List;

/**
 * The type Order ui.
 */
public class OrderUi {

	private final OrderController controller;

    /**
     * Instantiates a new Order ui.
     *
     * @param controller the controller
     */
    public OrderUi(OrderController controller) {
		this.controller = controller;
	}

    /**
     * List all orders.
     */
    public void listAllOrders () {
		StringBuilder builder = new StringBuilder();

		List<Order> orderStatus = controller.show( );
		builder.append( "/--------------------------------------------------------\\n");

		if ( orderStatus.isEmpty() ){
			builder.append(" Unable to retrieve orders.\n");
		} else {
			Utils.listar( controller.show() );
		}
		builder.append( "\\--------------------------------------------------------/\n");
	}

    /**
     * Check order status.
     *
     * @param orderId the order id
     */
    public void checkOrderStatus ( int orderId ) {
		StringBuilder builder = new StringBuilder();
		OrderController controller = new OrderController();

		String orderStatus = controller.checkStatus( orderId );
		builder.append( "/--------------------------------------------------------\\n");

		if ( orderStatus.isEmpty() ){
			builder.append(" Unable to retrieve order.\n");
		} else {
			builder.append(" Status: ").append( orderStatus ).append("\n");
		}
		builder.append( "\\--------------------------------------------------------/\n");

		Logger.log( builder.toString() );
	}
}
