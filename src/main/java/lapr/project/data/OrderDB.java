package lapr.project.data;

import lapr.project.model.Order;
import lapr.project.ui.Logger;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Order db.
 */
public class OrderDB extends DataHandler {


    /**
     * Show list.
     *
     * @return the list
     */
    public List<Order> show ( ) {
		List<Order> orders = new ArrayList<>();

		try ( CallableStatement callStmt = getConnection().prepareCall("{ ? = call showOrders() }")){

			callStmt.registerOutParameter( 1, OracleTypes.CURSOR );
			callStmt.execute();

			ResultSet results = ( ResultSet ) callStmt.getObject(1 );

			while ( results.next() ) {

				int id = results.getInt( 1 );
				LocalDate date = results.getDate( 2 ).toLocalDate();
				int stateId = results.getInt( 3 );

				Order currentOrder = new Order( id, LocalDateTime.of( date ,LocalTime.now() ), stateId );

				orders.add( currentOrder );
			}

		} catch (SQLException e) {
			Logger.log( " SQL Exception -> " + e.getErrorCode() );
		} finally {
			closeAll();
		}

		return orders;
	}


    /**
     * Check order status string.
     *
     * @param orderID the order id
     * @return the string
     */
    public String checkOrderStatus ( int orderID ) {
		String status = "The order does not exist.";

		try ( CallableStatement callStmt = getConnection().prepareCall("{ ? = call checkOrderStatus(?) }")){

			callStmt.registerOutParameter( 1, OracleTypes.VARCHAR );
			callStmt.setInt(1, orderID);
			callStmt.execute();

			status = callStmt.getString(1 );

		} catch (SQLException e) {
			Logger.log( " SQL Exception -> " + e.getErrorCode() );
		} finally {
			closeAll();
		}

		return status;
	}
}
