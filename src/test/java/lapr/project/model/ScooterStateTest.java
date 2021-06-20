package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScooterStateTest {

    @Test
    void getId1() {
        System.out.println("getId Test1");
        int expResult = 1;
        int result = VehicleState.AVAILABLE.getId();
        assertEquals(expResult, result);
    }

    @Test
    void getId2() {
        System.out.println("getId Test2");
        int expResult = 2;
        int result = VehicleState.UNAVAILABLE.getId();
        assertEquals(expResult, result);
    }

    @Test
    void getId3() {
        System.out.println("getId Test3");
        int expResult = 3;
        int result = VehicleState.BROKEN.getId();
        assertEquals(expResult, result);
    }

    @Test
    void getId4() {
        System.out.println("getId Test4");
        int expResult = 4;
        int result = VehicleState.CHARGING.getId();
        assertEquals(expResult, result);
    }

    @Test
    void ToString1() {
        System.out.println("toString Test1");
        String expResult = "Available";
        String result = VehicleState.AVAILABLE.toString();
        assertEquals(expResult, result);
    }

    @Test
    void ToString2() {
        System.out.println("toString Test2");
        String expResult = "UnAvailable";
        String result = VehicleState.UNAVAILABLE.toString();
        assertEquals(expResult, result);
    }

    @Test
    void ToString3() {
        System.out.println("toString Test3");
        String expResult = "Broken";
        String result = VehicleState.BROKEN.toString();
        assertEquals(expResult, result);
    }

    @Test
    void ToString4() {
        System.out.println("toString Test4");
        String expResult = "Charging";
        String result = VehicleState.CHARGING.toString();
        assertEquals(expResult, result);
    }

}