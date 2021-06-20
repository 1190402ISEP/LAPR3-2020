package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChargingStationTest {

	private ChargingStation validInstance;
	private ChargingStation invalidInstance;

	@BeforeEach
	void setUp() {
		validInstance = new ChargingStation( 1 );
		invalidInstance = new ChargingStation();
	}

	@Test
	void validGetIdTest () {
		int expected = 1;
		int actual = validInstance.getStationId();

		assertEquals( expected, actual );
	}

	@Test
	void invalidGetIdTest () {
		int expected = -1;
		int actual = invalidInstance.getStationId();

		assertEquals( expected, actual );
	}

	@Test
	void validSetIdTest () {
		int expected = 10;
		validInstance.setStationId( 10 );
		int actual = validInstance.getStationId();

		assertEquals( expected, actual );
	}

	@Test
	void invalidsetIdTest () {
		int expected = -2;
		invalidInstance.setStationId( -2 );
		int actual = invalidInstance.getStationId();

		assertEquals( expected, actual );
	}

	@Test
	void sameObjectEqualsTest () {
		assertEquals( validInstance, validInstance );
	}

	@Test
	void validEqualsTest () {
		int expected = validInstance.getStationId();
		int actual = invalidInstance.getStationId();

		assertNotEquals( expected,actual);
	}

	@Test
	void validEqualsTest2 () {
		int expected = validInstance.getStationId();
		int actual = validInstance.getStationId();

		assertEquals( expected,actual);
	}

	@Test
	void notInstanceOfClassEqualsTest () {
		assertFalse( validInstance.equals( new Address("", "", "", 0)));
	}

	@Test
	void notEqualsTest () {
		assertNotEquals( validInstance, invalidInstance );
	}

	@Test
	void hashCodeValidTest () {
		int expected = 32;
		int actual = validInstance.hashCode();

		assertEquals( expected, actual );
	}

	@Test
	void hashCodeInvalidTest () {
		ParkPlace localPlace = new ParkPlace();
		int expected = 929;
		int actual = localPlace.hashCode();

		assertEquals( expected, actual );
	}
}
