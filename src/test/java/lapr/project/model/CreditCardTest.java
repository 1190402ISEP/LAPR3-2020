package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

    CreditCard test;

    @BeforeEach
    void setUp() {
        Calendar c = Calendar.getInstance();
        c.set(2021, Calendar.JUNE, 1);
        Date d = c.getTime();
        test = new CreditCard("1234555555555555", 123, d);
    }

    @Test
    void getNumber() {
        String expResult = "1234555555555555";
        assertEquals(expResult, test.getNumber());
    }

    @Test
    void setNumber() {
        test.setNumber("5555555555555555");
        assertEquals("5555555555555555", test.getNumber());
    }

    @Test
    void getCvv() {
        int cvv = 123;
        assertEquals(cvv, test.getCvv());
    }

    @Test
    void setCvv() {
        test.setCvv(222);
        assertEquals(222, test.getCvv());
    }

    @Test
    void getExpirateDate() {
        Calendar expResult = Calendar.getInstance();
        expResult.set(2021, Calendar.JUNE, 1);

        Calendar result = Calendar.getInstance();
        result.setTime(test.getExpirateDate());


        assertEquals(expResult.get(Calendar.DATE), result.get(Calendar.DATE));
        assertEquals(expResult.get(Calendar.MONTH), result.get(Calendar.MONTH));
        assertEquals(expResult.get(Calendar.YEAR), result.get(Calendar.YEAR));

    }

    @Test
    void setExpirateDate() {
        Calendar expResult = Calendar.getInstance();
        expResult.set(2022, Calendar.JULY, 5);

        test.setExpirateDate(expResult.getTime());

        Calendar result = Calendar.getInstance();
        result.setTime(test.getExpirateDate());

        assertEquals(expResult.get(Calendar.DATE), result.get(Calendar.DATE));
        assertEquals(expResult.get(Calendar.MONTH), result.get(Calendar.MONTH));
        assertEquals(expResult.get(Calendar.YEAR), result.get(Calendar.YEAR));


    }



    @Test
    void testEquals() {

        Calendar c = Calendar.getInstance();
        c.set(2021, Calendar.JUNE, 1);
        Date d = c.getTime();
        CreditCard card = new CreditCard("1234555555555555", 123, d);

        assertEquals(card, test);


    }

    @Test
    void testEquals2() {

        Calendar c = Calendar.getInstance();
        c.set(2021, Calendar.JUNE, 1);
        Date d = c.getTime();
        CreditCard card = new CreditCard("1234555555655555", 123, d);

        assertNotEquals(card, test);


    }

    @Test
    void testEquals3() {


        assertNotEquals(new Product("ola", 1, 1.0f, 1.0f, 1.0f, "A23"), test);


    }

    @Test
    void testEquals4() {
        assertNotEquals( test, null);
    }

    @Test
    void testEquals5() {
        assertNotEquals( test, new Address( "", "", "", 0));
    }

    @Test
    void testHashCode() {
        int expResult = -1362131710;
        assertEquals(expResult, test.hashCode());
    }

    @Test
    void testHashCode2() {
        int expResult = 0;
        test.setNumber(null);
        assertEquals(expResult, test.hashCode());
    }
}