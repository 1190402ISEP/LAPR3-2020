package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AddressTest {

    private Address validInstance;
    private Address invalidInstance;
    private Address nullInstance;

    @BeforeEach
    void setUp() {
        validInstance = new Address(
                "41.178635, -8.609411",
                "ISEP",
                "4690-111",
                12 );

        invalidInstance = new Address(
                "",
                "",
                "",
                0 );

        nullInstance = new Address(
                null,
                null,
                null,
                0
        );
    }

    @Test
    void getValidCoordinatesTest() {
        String result = validInstance.getCoordinates();
        String expected = "41.178635, -8.609411";

        assertEquals( expected, result );
    }

    @Test
    void getInvalidCoordinatesTest() {
        String result = invalidInstance.getCoordinates();
        String expected = "";

        assertEquals( expected, result );
    }

    @Test
    void setValidCoordinatesTest() {
        validInstance.setCoordinates( "41.178635, -8.609411" );
        String result = validInstance.getCoordinates();
        String expected = "41.178635, -8.609411";

        assertEquals( expected, result );
    }

    @Test
    void setInvalidCoordinatesTest() {
        invalidInstance.setCoordinates( "" );
        String result = invalidInstance.getCoordinates();
        String expected = "";

        assertEquals( expected, result );
    }

    @Test
    void getValidStreetTest() {
        String result = validInstance.getStreet();
        String expected = "ISEP";

        assertEquals( expected, result );
    }

    @Test
    void getInvalidStreetTest() {
        String result = invalidInstance.getCoordinates();
        String expected = "";

        assertEquals( expected, result );
    }

    @Test
    void setValidStreetTest() {
        validInstance.setStreet( "ISEP" );
        String result = validInstance.getStreet();
        String expected = "ISEP";

        assertEquals( expected, result );
    }

    @Test
    void setInvalidStreetTest() {
        invalidInstance.setCoordinates( "" );
        String result = invalidInstance.getCoordinates();
        String expected = "";

        assertEquals( expected, result );
    }


    @Test
    void getValidCodeTest() {
        String result = validInstance.getPostalCode();
        String expected = "4690-111";

        assertEquals( expected, result );
    }

    @Test
    void getInvalidCodeTest() {
        String result = invalidInstance.getPostalCode();
        String expected = "";

        assertEquals( expected, result );
    }

    @Test
    void setValidCodeTest() {
        validInstance.setPostalCode( "4690-111" );
        String result = validInstance.getPostalCode();
        String expected = "4690-111";

        assertEquals( expected, result );
    }

    @Test
    void setInvalidCodeTest() {
        invalidInstance.setPostalCode( "" );
        String result = invalidInstance.getPostalCode();
        String expected = "";

        assertEquals( expected, result );
    }

    @Test
    void getValidDoorTest() {
        int result = validInstance.getDoorNumber();
        int expected = 12;

        assertEquals( expected, result );
    }

    @Test
    void getInvalidDoorTest() {
        int result = invalidInstance.getDoorNumber();
        int expected = 0;

        assertEquals( expected, result );
    }

    @Test
    void setValidDoorTest() {
        validInstance.setDoorNumber( 12 );
        int result = validInstance.getDoorNumber();
        int expected = 12;

        assertEquals( expected, result );
    }

    @Test
    void setInvalidDoorTest() {
        invalidInstance.setDoorNumber( 0 );
        int result = invalidInstance.getDoorNumber();
        int expected = 0;

        assertEquals( expected, result );
    }

    @Test
    void equalsTest() {
        assertEquals( validInstance.getCoordinates(), validInstance.getCoordinates() );
    }

    @Test
    void equalsTest2() {
        assertEquals( validInstance, validInstance );
    }

    @Test
    void notEqualsTest() {
        assertNotEquals( validInstance, invalidInstance );
    }

    @Test
    void notEqualsTest2() {
        assertEquals( "", invalidInstance.getCoordinates() );
    }

    @Test
    void notEqualsNullTest() {
        assertNotEquals( validInstance, null );
    }

    @Test
    void notEqualsNullTest2() {
        assertNotEquals( validInstance, new Pharmacy(1,"", "", "" ) );
    }

    @Test
    void hasInvalidCodeTest () {
        assertEquals( 923521, nullInstance.hashCode());
    }

    @Test
    void emptyToStringTest () {
        Address address = new Address( "", "", "", 0);
        String expected = "Address{" +
                "coordinates='" + '\'' +
                ", street='" + '\'' +
                ", postalCode='" + '\'' +
                ", doorNumber=" + 0 +
                '}';

        assertEquals( expected, address.toString());
    }
}
