package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DroneTest {

    Drone d1 = new Drone( 2, 3);

    Drone d2 = new Drone(1, 2, VehicleState.UNAVAILABLE, 5, 10);

    @Test
    void getID1() {
        assertEquals(-1, d1.getId());
    }

    @Test
    void getID2() {
        assertEquals(1, d2.getId());
    }

    @Test
    void getBatteryCompany1() {
        assertEquals(2, d1.getBatteryCapacity());
    }

    @Test
    void getBatteryCompany2() {
        assertEquals(2, d2.getBatteryCapacity());
    }

    @Test
    void getMaxPayload1() {
        assertEquals(3, d1.getMaxPayload());
    }

    @Test
    void getMaxPayload2() {
        assertEquals(10, d2.getMaxPayload());
    }

    @Test
    void getState1() {
        assertEquals(VehicleState.AVAILABLE, d1.getState());
    }

    @Test
    void getState2() {
        assertEquals(VehicleState.UNAVAILABLE, d2.getState());
    }

    @Test
    void getBattery1() {
        assertEquals(100, d1.getBattery());
    }

    @Test
    void getBattery2() {
        assertEquals(5, d2.getBattery());
    }

    @Test
    void setId1() {
        d1.setId(2);
        assertEquals(2, d1.getId());
    }

    @Test
    void setId2() {
        d2.setId(3);
        assertEquals(3, d2.getId());
    }

    @Test
    void setBatteryCapacity1() {
        d1.setBatteryCapacity(3);
        assertEquals(3, d1.getBatteryCapacity());
    }

    @Test
    void setBatteryCapacity2() {
        d2.setBatteryCapacity(55);
        assertEquals(55, d2.getBatteryCapacity());
    }

    @Test
    void setMaxPayload1() {
        d1.setMaxPayload(50);
        assertEquals(50, d1.getMaxPayload());
    }

    @Test
    void setMaxPayload2() {
        d2.setMaxPayload(50);
        assertEquals(50, d2.getMaxPayload());
    }

    @Test
    void setState1() {
        d1.setState(VehicleState.BROKEN);
        assertEquals(VehicleState.BROKEN, d1.getState());
    }

    @Test
    void setState2() {
        d2.setState(VehicleState.CHARGING);
        assertEquals(VehicleState.CHARGING, d2.getState());
    }

    @Test
    void setBattery1() {
        d1.setBattery(0);
        assertEquals(0, d1.getBattery());
    }

    @Test
    void setBattery2() {
        d2.setBattery(100);
        assertEquals(100, d2.getBattery());
    }
@Test
    void toString1(){
        String res=String.format("|ID: %d |Battery Cappacity: %.2f Wh|State: %s |Battery: %.2f %%|Max Payload: %.2f Kg| "
                , d1.getId(), d1.getBatteryCapacity(), d1.getState(),d1.getBattery(),d1.getMaxPayload());
        assertEquals(d1.toString(),res);
}
    @Test
    void toString2(){
        String res=String.format("|ID: %d |Battery Cappacity: %.2f Wh|State: %s |Battery: %.2f %%|Max Payload: %.2f Kg| "
                , d2.getId(), d2.getBatteryCapacity(), d2.getState(),d2.getBattery(),d2.getMaxPayload());
        assertEquals(d2.toString(),res);
    }
}
