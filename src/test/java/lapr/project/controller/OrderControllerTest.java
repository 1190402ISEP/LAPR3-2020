package lapr.project.controller;

import lapr.project.data.OrderDB;
import lapr.project.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderControllerTest {

	private OrderController instance;
	private OrderDB db;

	@BeforeEach
	void setUp() {
		db = mock( OrderDB.class );
		instance = new OrderController( );
		when( db.checkOrderStatus(1) ).thenReturn( "In process" );
		when( db.checkOrderStatus(2) ).thenReturn( "In destribution" );
		when( db.checkOrderStatus(3) ).thenReturn( "Canceled" );
		when( db.checkOrderStatus(4) ).thenReturn( "Delivered" );
		when( db.checkOrderStatus(5)).thenReturn( "The order does not exist." );

		instance = new OrderController( db );
	}

	@Test
	void checkOrderStatusTest () {
		String expected = "In process";
		String actual = instance.checkStatus( 1 );

		assertEquals( expected, actual);
	}

	@Test
	void checkOrderStatusTest2 () {
		String expected = "In destribution";
		String actual = instance.checkStatus( 2 );

		assertEquals( expected, actual);
	}

	@Test
	void checkOrderStatusTest3 () {
		String expected = "Canceled";
		String actual = instance.checkStatus( 3 );

		assertEquals( expected, actual);
	}

	@Test
	void checkOrderStatusTest4 () {
		String expected = "Delivered";
		String actual = instance.checkStatus( 4 );

		assertEquals( expected, actual );
	}

	@Test
	void checkOrderStatusTest5 () {
		String expected = "The order does not exist.";
		String actual = instance.checkStatus( 5 );

		assertEquals( expected, actual );
	}

	@Test
	void showTestEmpty () {
		when( db.show() ).thenReturn( Collections.emptyList() );
		List<Order> expected = new ArrayList<>();
		List<Order> actual = instance.show();

		assertEquals( expected, actual );
	}

	@Test
	void showTest () {
		List<Order> orders = new ArrayList<>();
		orders.add( new Order( 1, LocalDateTime.now(), 1 ) );
		orders.add( new Order( 1, LocalDateTime.now(), 2 ) );
		orders.add( new Order( 1, LocalDateTime.now(), 3 ) );
		orders.add( new Order( 1, LocalDateTime.now(), 4 ) );

		when( db.show() ).thenReturn( orders );
		List<Order> expected = orders;
		List<Order> actual = instance.show();

		assertEquals( expected, actual );
	}
}
