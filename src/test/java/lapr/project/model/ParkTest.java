package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ParkTest {

    private Park validInstance;
    private Park invalidInstance;

    @BeforeEach
    void setUp() {
        validInstance = new Park( 1, 260,10 , 1, 0 );
        invalidInstance = new Park( -1, -10,-1, -2, -2);
    }

    @Test
    void testValidGetPharmacyID() {
        int expected = 1;
        int actual = validInstance.getPharmacyID();

        assertEquals( expected, actual );
    }

    @Test
    void testInvalidGetPharmacyID() {
        int expected = 0;
        int actual = invalidInstance.getPharmacyID();

        assertEquals( expected, actual );
    }

    @Test
    void testValidSetPharmacyID() {
        validInstance.setPharmacyID( 10 );
        int expected = 10;
        int actual = validInstance.getPharmacyID();

        assertEquals( expected, actual );
    }

    @Test
    void testInvalidSetPharmacyID() {
        invalidInstance.setPharmacyID( 0 );
        int expected = 0;
        int actual = invalidInstance.getPharmacyID();

        assertEquals( expected, actual );
    }

    @Test
    void testValidGetMaxCapacity() {
        int expected = 10;
        int actual = validInstance.getMaxCapacity();

        assertEquals( expected, actual );
    }

    @Test
    void testValidSetMaxCapacity() {
        validInstance.setMaxCapacity( 12 );
        int expected = 12;
        int actual = validInstance.getMaxCapacity();

        assertEquals( expected, actual );
    }

    @Test
    void testInvalidSetMaxCapacity() {
        invalidInstance.setMaxCapacity( 0 );
        int expected = 0;
        int actual = invalidInstance.getMaxCapacity();

        assertEquals( expected, actual );
    }

    @Test
    void testValidGetParkType() {
        int expected = 1;
        int actual = validInstance.getParkType();

        assertEquals( expected, actual );
    }

    @Test
    void testValidSetParkType() {
        validInstance.setParkType( 12 );
        int expected = 12;
        int actual = validInstance.getParkType();

        assertEquals( expected, actual );
    }

    @Test
    void testInvalidSetParkType() {
        invalidInstance.setParkType( 0 );
        int expected = 0;
        int actual = invalidInstance.getParkType();

        assertEquals( expected, actual );
    }

    @Test
    void testValidGetCurrentOcupation() {
        int expected = 0;
        int actual = validInstance.getCurrentOcupation();

        assertEquals( expected, actual );
    }

    @Test
    void testValidSetCurrentOcupation() {
        validInstance.setCurrentOcupation( 10 );
        int expected = 10;
        int actual = validInstance.getCurrentOcupation();

        assertEquals( expected, actual );
    }

    @Test
    void testValidSetCurrentOcupation2() {
        int expected = validInstance.getMaxCapacity() + 1;

        validInstance.setCurrentOcupation( expected );
        int actual = validInstance.getCurrentOcupation();

        assertNotEquals( expected, actual );
    }

    @Test
    void testValidGetTotalVoltage() {
        int expected = 260;
        int actual = validInstance.getTotalVoltage();

        assertEquals( expected, actual );
    }

    @Test
    void testValidSetTotalVoltage() {
        validInstance.setCurrentOcupation( 10 );
        int expected = 10;
        int actual = validInstance.getCurrentOcupation();

        assertEquals( expected, actual );
    }

    @Test
    void equalsTest () {
        Park validPark = new Park( 1, 240,10, 1, 10 );
        validPark.setID( 1 );
        assertEquals( validPark, validPark );
    }

    @Test
    void equalsTest2 () {
        Park validPark = new Park( 1, 240, 10, 1, 10 );
        validPark.setID( 1 );
        Park invalidPark = new Park( -1, -1, 10, -1, 0 );
        invalidPark.setID( 1 );
        assertEquals( validPark.getID(),  invalidPark.getID());
    }

    @Test
    void equalsTest3 () {
        Park validPark = new Park( 1, 240, 10, 1, 10 );
        validPark.setID( 1 );
        Park invalidPark = new Park( 1, -1,10, -1, 0 );
        invalidPark.setID( 1 );
        assertEquals( validPark.getPharmacyID(),  invalidPark.getPharmacyID());
    }

    @Test
    void notEqualsTest () {
        Park validPark = new Park( 1, 240, 10, 1, 10 );
        Park invalidPark = new Park( -1, -1,10, -1, 0 );

        validPark.setID( 1 );
        invalidPark.setID( 2 );

        assertNotEquals( validPark, invalidPark );
    }

    @Test
    void notEqualsTest2 () {
        Park validPark = new Park( 1, 240, 10, 1, 10 );
        validPark.setID(1);
        assertNotEquals( validPark, "");
    }

    @Test
    void notEqualsTest3 () {
        Park validPark = new Park( 1, 240, 10, 1, 10 );

        assertNotEquals( validPark, null );
    }

    @Test
    void notEqualsTest4 () {
        Park validPark = new Park( 1, 240, 10, 1, 10 );
        validPark.setID( 1 );
        Park invalidPark = new Park( -1, -1, 10, -1, 0 );
        invalidPark.setID( 2 );
        assertNotEquals( validPark.getID(),  invalidPark.getID());
    }

    @Test
    void notEqualsTest5 () {
        Park validPark = new Park( 1, 240, 10, 1, 10 );
        validPark.setID( 1 );
        Park invalidPark = new Park( -1, -1, 10, -1, 0 );
        invalidPark.setID( 2 );
        assertNotEquals( validPark.getID(),  invalidPark.getID());
    }

    @Test
    void validHashCodeTest () {
        Park validPark = new Park( 1, 40, 10, 1, 10 );

        assertEquals( 931, validPark.hashCode() );
    }

    @Test
    void validHashCodeTest2 () {
        Park validPark = new Park( 1, 10, 10, 1, 10 );
        validPark.setID( 1 );

        assertEquals( 993, validPark.hashCode() );
    }

    @Test
    void notHashCodeTest () {
        Park validPark = new Park( 1, 250, 10, 1, 10 );

        assertNotEquals( -1, validPark.hashCode() );
    }

    @Test
    void notHashCodeTest2 () {
        Park validPark = new Park( 1, 240,10, 1, 10 );
        validPark.setID( 1 );

        assertNotEquals( 0, validPark.hashCode() );
    }

    @Test
    void toStringTest () {
        String expected = "Park {\n identification -> " + -1 +
                ", belongs to pharmacy with ID -> " + 0 +
                ", maximum capacity -> " + 0 +
                ", park type ID -> " + 0 +
                ", current ocupation -> " + 0 +
                ", total voltage shared between charging spots->" + 0 +
                "\n}";

        Park park = new Park(0,0,0,0,0);

        assertEquals( expected, park.toString() );
    }
}
