package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourierStateTest {

    @Test
    void getId1() {
        System.out.println("getId Test1");
        int expResult = 1;
        int result = CourierState.AVAILABLE.getId();
        assertEquals(expResult, result);
    }

    @Test
    void getId2() {
        System.out.println("getId Test2");
        int expResult = 2;
        int result = CourierState.UNAVAILABLE.getId();
        assertEquals(expResult, result);
    }

    @Test
    void ToString1() {
        System.out.println("toString Test1");
        String expResult = "Available";
        String result = CourierState.AVAILABLE.toString();
        assertEquals(expResult, result);
    }

    @Test
    void ToString2() {
        System.out.println("toString Test2");
        String expResult = "Unavailable";
        String result = CourierState.UNAVAILABLE.toString();
        assertEquals(expResult, result);
    }
    
}