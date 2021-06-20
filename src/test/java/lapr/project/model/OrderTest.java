package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrderTest {

    private Order validInstance;
    private Order invalidInstance;
    private Order toStringInstance;
    private LocalDateTime validDate;
    private LocalDateTime invalidDate;
    private LocalDateTime date;

    @BeforeEach
    void setUp() {
        validDate = LocalDateTime.of( LocalDate.now(), LocalTime.now() );
        invalidDate = LocalDateTime.of(
                LocalDate.now().plusDays( 2 ),
                LocalTime.now().plusHours( 2 )
        );
        date = LocalDateTime.of(2021, Month.JULY, 29, 19, 30, 40);

        toStringInstance = new Order( 1, date, 1 );
        validInstance = new Order( 1, validDate, 1 );
        invalidInstance = new Order( -1, invalidDate, -1 );
    }

    @Test
    void testValidGetId () {
        int expected = 1;
        int actual = validInstance.getId();

        assertEquals( expected, actual );
    }

    @Test
    void testinvalidGetId () {
        int expected = -1;
        int actual = invalidInstance.getId();

        assertEquals( expected, actual );
    }

    @Test
    void testValidSetId () {
        int expected = 10;
        validInstance.setId( 10 );
        int actual = validInstance.getId();

        assertEquals( expected, actual );
    }

    @Test
    void testinvalidSetId () {
        int expected = -1;
        invalidInstance.setId( -1 );
        int actual = invalidInstance.getId();

        assertEquals( expected, actual );
    }

    @Test
    void testValidGetOrderDate () {
        LocalDateTime expected = validDate;
        LocalDateTime actual = validInstance.getOrderDate();

        assertEquals( expected, actual );
    }

    @Test
    void testinvalidGetOrderDate () {
        LocalDateTime expected = invalidDate;
        LocalDateTime actual = invalidInstance.getOrderDate();

        assertEquals( expected, actual );
    }

    @Test
    void testValidSetOrderDate () {
        LocalDateTime expected = validDate;
        validInstance.setOrderDate( validDate );
        LocalDateTime actual = validInstance.getOrderDate();

        assertEquals( expected, actual );
    }

    @Test
    void testinvalidSetOrderDate () {
        LocalDateTime expected = invalidDate;
        invalidInstance.setOrderDate( invalidDate );
        LocalDateTime actual = invalidInstance.getOrderDate();

        assertEquals( expected, actual );
    }

    @Test
    void testValidGetStateId () {
        int expected = 1;
        int actual = validInstance.getStateId();

        assertEquals( expected, actual );
    }

    @Test
    void testInvalidGetStateId () {
        int expected = -1;
        int actual = invalidInstance.getStateId();

        assertEquals( expected, actual );
    }

    @Test
    void testValidSetStateId () {
        int expected = 12;
        validInstance.setStateId( 12 );
        int actual = validInstance.getStateId();

        assertEquals( expected, actual );
    }

    @Test
    void testInvalidSetOrderDate () {
        int expected = -2;
        invalidInstance.setStateId( -2);
        int actual = invalidInstance.getStateId();

        assertEquals( expected, actual );
    }

    @Test
    void testValidEquals() {
        assertEquals( validInstance, validInstance );
    }

    @Test
    void testValidEquals2() {
        validInstance.setId(1);
        invalidInstance.setId(1);

        assertEquals( validInstance, invalidInstance);
    }

    @Test
    void testInvalidEquals() {
        assertNotEquals( new Address("", "", "", 0),  invalidInstance);
    }

    @Test
    void testInvalidEquals2() {
        assertNotEquals( validInstance, invalidInstance);
    }

    @Test
    void testInvalidEquals3() {
        assertFalse( validInstance.equals(new Address("", "", "", 0)));
    }

    @Test
    void testInvalidEquals4() {
        validInstance.setId(1);
        invalidInstance.setId(2);
        assertFalse( validInstance.getId() == invalidInstance.getId() );
    }

    @Test
    void testInvalidEquals5() {
        validInstance.setId(1);
        invalidInstance.setId(2);
        assertFalse( validInstance.equals( invalidInstance ) );
    }

    @Test
    void testHashCode () {
        validInstance.setId( 1 );
        assertEquals( validInstance.hashCode(), 32);
    }

    @Test
    void testInvalidHashCode () {
        invalidInstance.setId( 0 );
        assertEquals( invalidInstance.hashCode(), 31);
    }

    @Test
    void testToString() {
        System.out.println("toString Test");
        String expResult = "Order:id=1, orderDate="+date+", stateId=1";
        String result = toStringInstance.toString();
        assertEquals(expResult, result);
    }
}
