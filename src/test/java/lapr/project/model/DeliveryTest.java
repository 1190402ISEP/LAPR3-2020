package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    Delivery d;

    @BeforeEach
    void setUP() {
        d= new Delivery(1, 123456789, 1,1,10f, 1f, 50f);
    }

    @Test
    void getIdDelivery() {
        int result = d.getIdDelivery();
        assertEquals(1,result);
    }

    @Test
    void getNif() {
        int result = d.getNif();
        assertEquals(123456789,result);
    }

    @Test
    void getVehicleID() {
        int result = d.getVehicleID();
        assertEquals(1,result);
    }

    @Test
    void getPharmacyID() {
        int result = d.getPharmacyID();
        assertEquals(1,result);
    }

    @Test
    void getDistance() {
        float result = d.getDistance();
        assertEquals(10f,result);
    }

    @Test
    void getTimeMinutes() {
        float result = d.getTimeMinutes();
        assertEquals(1f,result);
    }

    @Test
    void getEnergyCost() {
        float result = d.getEnergyCost();
        assertEquals(50f,result);
    }
}