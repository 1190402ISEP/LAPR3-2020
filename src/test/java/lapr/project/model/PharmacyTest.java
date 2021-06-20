package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PharmacyTest {

    private Pharmacy validInstance;
    private Pharmacy invalidInstance;
    private Pharmacy secondValidInstance;

    @BeforeEach
    void setUp() {
        validInstance = new Pharmacy(1,"farma", "isep", "carlos@gmail.com");
        secondValidInstance = new Pharmacy(1,"farma", "isep", "carlos@gmail.com");
        invalidInstance = new Pharmacy( 1,"", "", "" );
    }

    @Test
    void getValidDesignationTest() {
        String result = validInstance.getDesignation();
        String expected = "farma";

        assertEquals(expected, result);
    }

    @Test
    void getInvalidDesignationTest() {
        String result = invalidInstance.getDesignation();
        String expected = "";

        assertEquals(expected, result);
    }

    @Test
    void setValidDesignationTest() {
        validInstance.setDesignation("fama");
        String result = validInstance.getDesignation();
        String expected = "fama";

        assertEquals(expected, result);
    }

    @Test
    void setInvalidDesignationTest() {
        invalidInstance.setDesignation("");
        String result = invalidInstance.getDesignation();
        String expected = "";

        assertEquals(expected, result);
    }

    @Test
    void getValidPharmacyOwnerTest() {
        String result = validInstance.getPharmacyOwner();
        String expected = "carlos@gmail.com";

        assertEquals(expected, result);
    }

    @Test
    void getInvalidPharmacyOwnerTest() {
        String result = invalidInstance.getPharmacyOwner();
        String expected = "";

        assertEquals(expected, result);
    }

    @Test
    void setValidPharmacyOwnerTest() {
        validInstance.setPharmacyOwner("calra@gmail.com");
        String result = validInstance.getPharmacyOwner();
        String expected = "calra@gmail.com";

        assertEquals(expected, result);
    }

    @Test
    void setInvalidPharmacyOwnerTest() {
        invalidInstance.setPharmacyOwner("");
        String result = invalidInstance.getPharmacyOwner();
        String expected = "";

        assertEquals(expected, result);
    }


    @Test
    void getValidAddressTest() {
        String result = validInstance.getAddress();
        String expected = "isep";

        assertEquals(expected, result);
    }

    @Test
    void getInvalidAddressTest() {
        String result = invalidInstance.getAddress();
        String expected = "";

        assertEquals(expected, result);
    }

    @Test
    void setValidAddressTest() {
        validInstance.setAddress("isepp");
        String result = validInstance.getAddress();
        String expected = "isepp";

        assertEquals(expected, result);
    }

    @Test
    void setInvalidAddressTest() {
        invalidInstance.setAddress("");
        String result = invalidInstance.getAddress();
        String expected = "";

        assertEquals(expected, result);
    }

    @Test
    void getValidIdTest() {
        validInstance.setId( 1 );
         assertEquals( 1, validInstance.getId() );
    }

    @Test
    void getValidIdTest2() {
        validInstance.setId( 0 );
        assertEquals( 0, validInstance.getId() );
    }

    @Test
    void invalidToStringTest() {
        invalidInstance.setId( 0 );
        invalidInstance.setPharmacyOwner( "" );
        invalidInstance.setAddress( "" );
        invalidInstance.setDesignation( "" );

        assertEquals( "Pharmacy{id=0, designation=, address=, pharmacyOwner=}", invalidInstance.toString() );
    }

    @Test
    void validToStringTest() {
        validInstance.setId( 1 );

        assertEquals( "Pharmacy{id=1, designation=farma, address=isep, pharmacyOwner=carlos@gmail.com}", validInstance.toString() );
    }

    @Test
    void equalsTest() {
        validInstance.setId( 1 );
        assertEquals( validInstance, validInstance );
    }

    @Test
    void notEqualsTest() {
        validInstance.setId( 1 );
        invalidInstance.setId( 0 );
        assertNotEquals( validInstance, invalidInstance );
    }

    @Test
    void notEqualsTest2() {
        validInstance.setId( 1 );
        invalidInstance.setId( 0 );
        assertNotEquals(new Address("", "", "", 0), validInstance);
    }

    @Test
    void notEqualsFalseTest() {
        assertNotEquals(validInstance, null);
    }


    @Test
    void notEqualsNullTest() {
        assertNotEquals( validInstance, null );
    }

    @Test
    void notEqualsNotClassTest() {
        assertNotEquals(new Address("", "", "", 0), validInstance);
    }

    @Test
    void notEqualsDifferentIdTest () {
        validInstance.setId( 5 );
        secondValidInstance.setId( 10 );

        assertNotEquals( validInstance, secondValidInstance );
    }

    @Test
    void invalidHashCodeTest () {
        assertEquals( 953312, invalidInstance.hashCode() );
    }

    @Test
    void validHashCodeTest () {
        assertEquals( 861365571, validInstance.hashCode() );
    }

}
