package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkPlaceTest {

	private ParkPlace validInstance;
	private ParkPlace invalidInstance;

	@BeforeEach
	void setUp() {
		validInstance = new ParkPlace(  1, true );
		invalidInstance = new ParkPlace();
	}

	@Test
	void validGetIdTest () {
		int expected = -1;
		int actual = validInstance.getId();

		assertEquals( expected, actual );
	}

	@Test
	void invalidGetIdTest () {
		int expected = -1;
		int actual = invalidInstance.getId();

		assertEquals( expected, actual );
	}

	@Test
	void validSetIdTest () {
		int expected = 10;
		validInstance.setId( 10 );
		int actual = validInstance.getId();

		assertEquals( expected, actual );
	}

	@Test
	void invalidsetIdTest () {
		int expected = -2;
		invalidInstance.setId( -2 );
		int actual = invalidInstance.getId();

		assertEquals( expected, actual );
	}

	@Test
	void validGetParkIdTest () {
		int expected = 1;
		int actual = validInstance.getParkId();

		assertEquals( expected, actual );
	}

	@Test
	void invalidGetParkIdTest () {
		int expected = -1;
		int actual = invalidInstance.getParkId();

		assertEquals( expected, actual );
	}

	@Test
	void validSetParkIdTest () {
		int expected = 10;
		validInstance.setParkId( 10 );
		int actual = validInstance.getParkId();

		assertEquals( expected, actual );
	}

	@Test
	void invalidsetParkIdTest () {
		int expected = -2;
		invalidInstance.setParkId( -2 );
		int actual = invalidInstance.getParkId();

		assertEquals( expected, actual );
	}

	@Test
	void validIsAvailableTest () {
		boolean actual = validInstance.isAvailable();

		assertTrue( actual );
	}

	@Test
	void invalidIsAvailableTest () {
		boolean actual = invalidInstance.isAvailable();
		assertFalse( actual );
	}

	@Test
	void validSetIsAvailableTest () {
		validInstance.setAvailable( false );
		boolean actual = validInstance.isAvailable();

		assertFalse( actual );
	}

	@Test
	void invalidSetIsAvailableTest () {
		invalidInstance.setAvailable( true );
		boolean actual = invalidInstance.isAvailable();

		assertTrue( actual );
	}

	@Test
	void sameObjectEqualsTest () {
		assertEquals( validInstance, validInstance );
	}

	@Test
	void notInstanceOfClassEqualsTest () {
		assertNotEquals(new Address("", "", "", 0), validInstance);
	}

	@Test
	void notEqualsTest () {
		assertNotEquals( validInstance, invalidInstance );
	}

	@Test
	void validEqualsTest () {

		int actual = invalidInstance.getId();

		assertNotEquals( 1,actual);
	}

	@Test
	void validEqualsTest2 () {
		int expected = validInstance.getId();
		int actual = validInstance.getId();

		assertEquals( expected,actual);
	}

	@Test
	void validEqualsTest3 () {
		int expected = validInstance.getParkId();
		int actual = invalidInstance.getParkId();

		assertNotEquals( expected,actual);
	}

	@Test
	void validEqualsTest4 () {
		int expected = validInstance.getParkId();
		int actual = validInstance.getParkId();

		assertEquals( expected,actual);
	}

	@Test
	void invalidEqualsTest () {
		assertFalse( validInstance.equals( new Address("", "", "", 1) ) );
	}

	@Test
	void invalidEqualsTest2 () {

		validInstance.setId(1);
		validInstance.setParkId(2);
		invalidInstance.setId(2);
		invalidInstance.setParkId(2);

		assertFalse( validInstance.equals( invalidInstance) );
	}

	@Test
	void invalidEqualsTest3 () {

		validInstance.setId(1);
		validInstance.setParkId(2);
		invalidInstance.setId(1);
		invalidInstance.setParkId(1);

		assertFalse( validInstance.equals( invalidInstance) );
	}

	@Test
	void invalidEqualsTest4 () {

		validInstance.setId(1);
		validInstance.setParkId(2);
		invalidInstance.setId(3);
		invalidInstance.setParkId(4);

		assertFalse( validInstance.equals( invalidInstance ) );
	}

	@Test
	void hashCodeValidTest () {
		int expected = 931;
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
