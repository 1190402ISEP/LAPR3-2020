package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChargingTest {

    @Test
    void getPharmacyID1() {
        Charging c1 = new Charging(1, 2, "email");
        Integer expResult = 1;
        Integer result = c1.getPharmacyID();
        assertEquals(expResult, result);
    }

    @Test
    void getPharmacyID2() {
        Charging c2 = new Charging(2, 2, "email");
        Integer expResult = 2;
        Integer result = c2.getPharmacyID();
        assertEquals(expResult, result);
    }

    @Test
    void getScooterID1() {
        Charging c1 = new Charging(1, 2, "email");
        Integer expResult = 2;
        Integer result = c1.getScooterID();
        assertEquals(expResult, result);
    }

    @Test
    void getScooterID2() {
        Charging c2 = new Charging(2, 3, "email");
        Integer expResult = 3;
        Integer result = c2.getScooterID();
        assertEquals(expResult, result);
    }

    @Test
    void getCourierEmail1() {
        Charging c1 = new Charging(1, 2, "email");
        String expResult = "email";
        String result = c1.getCourierEmail();
        assertEquals(expResult, result);
    }

    @Test
    void getCourierNif2() {
        Charging c2 = new Charging(1, 2, "email2");
        String expResult = "email2";
        String result = c2.getCourierEmail();
        assertEquals(expResult, result);
    }
}