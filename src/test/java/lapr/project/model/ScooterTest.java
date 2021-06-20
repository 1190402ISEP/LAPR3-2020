
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.project.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author anton
 */
public class ScooterTest {

    Scooter s1 = new Scooter(true, 1.0F, 5);
    Scooter s2 = new Scooter(s1);
    Scooter s3 = new Scooter(3, false, 3, VehicleState.UNAVAILABLE, 90, 3);

    @Test
    void getUniqueNumber1() {
        int expResult = -1;
        int result = s1.getId();
        assertEquals(expResult, result);
    }

    @Test
    void getUniqueNumber2() {
        int expResult = -1;
        int result = s2.getId();
        assertEquals(expResult, result);
    }

    @Test
    void getUniqueNumber3() {
        int expResult = 3;
        int result = s3.getId();
        assertEquals(expResult, result);
    }

    @Test
    void getQrCode1() {
        boolean result = s1.getQrCode();
        assertTrue(result);
    }

    @Test
    void getQrCode2() {
        boolean result = s2.getQrCode();
        assertTrue(result);
    }

    @Test
    void getQrCode3() {
        boolean result = s3.getQrCode();
        assertFalse(result);
    }

    @Test
    void getBatteryCapacity1() {
        float expResult = 1.0F;
        float result = s1.getBatteryCapacity();
        assertEquals(expResult, result);
    }

    @Test
    void getBatteryCapacity2() {
        float expResult = 1.0F;
        float result = s2.getBatteryCapacity();
        assertEquals(expResult, result);
    }

    @Test
    void getBatteryCapacity3() {
        float expResult = 3.0F;
        float result = s3.getBatteryCapacity();
        assertEquals(expResult, result);
    }

    @Test
    void getState1() {
        VehicleState expResult = VehicleState.AVAILABLE;
        s1.setState(VehicleState.AVAILABLE);
        VehicleState result = s1.getState();
        assertEquals(expResult, result);
    }

    @Test
    void getState2() {
        VehicleState expResult = VehicleState.BROKEN;
        s2.setState(VehicleState.BROKEN);
        VehicleState result = s2.getState();
        assertEquals(expResult, result);
    }

    @Test
    void getState3() {
        VehicleState expResult = VehicleState.UNAVAILABLE;
        s3.setState(VehicleState.UNAVAILABLE);
        VehicleState result = s3.getState();
        assertEquals(expResult, result);
    }

    @Test
    void getStateSpecial() {
        VehicleState expResult = VehicleState.CHARGING;
        s2.setState(VehicleState.CHARGING);
        VehicleState result = s2.getState();
        assertEquals(expResult, result);
    }

    @Test
    void getBattery1() {
        float expResult = 100;
        float result = s1.getBattery();
        assertEquals(expResult, result);
    }

    @Test
    void getBattery2() {
        float expResult = 100;
        float result = s2.getBattery();
        assertEquals(expResult, result);
    }

    @Test
    void getBattery3() {
        float expResult = 90;
        float result = s3.getBattery();
        assertEquals(expResult, result);
    }

    @Test
    void getMaxweight1() {
        double expResult = 5;
        double result = s1.getMaxPayload();
        assertEquals(expResult, result);
    }

    @Test
    void getMaxweight2() {
        double expResult = 5;
        double result = s2.getMaxPayload();
        assertEquals(expResult, result);
    }

    @Test
    void getMaxweight3() {
        double expResult = 3;
        double result = s3.getMaxPayload();
        assertEquals(expResult, result);
    }

    @Test
    void setUniqueNumber1() {
        int expResult = 1;
        s1.setId(1);
        assertEquals(expResult, s1.getId());
    }

    @Test
    void setUniqueNumber2() {
        int expResult = 2;
        s2.setId(2);
        assertEquals(expResult, s2.getId());
    }

    @Test
    void setUniqueNumber3() {
        int expResult = 3;
        s3.setId(3);
        assertEquals(expResult, s3.getId());
    }

    @Test
    void setQrCode11() {
        s1.setQrCode(true);
        assertTrue(s1.getQrCode());
    }

    @Test
    void setQrCode12() {
        s1.setQrCode(false);
        assertFalse(s1.getQrCode());
    }

    @Test
    void setQrCode21() {
        s2.setQrCode(true);
        assertTrue(s2.getQrCode());
    }

    @Test
    void setQrCode22() {
        s2.setQrCode(false);
        assertFalse(s2.getQrCode());
    }

    @Test
    void setQrCode31() {
        s3.setQrCode(true);
        assertTrue(s3.getQrCode());
    }

    @Test
    void setQrCode32() {
        s3.setQrCode(false);
        assertFalse(s3.getQrCode());
    }

    @Test
    void setBatteryCapacity1() {
        int expResult = 1;
        s1.setBatteryCapacity(1);
        assertEquals(expResult, s1.getBatteryCapacity());
    }

    @Test
    void setBatteryCapacity2() {
        int expResult = 2;
        s2.setBatteryCapacity(2);
        assertEquals(expResult, s2.getBatteryCapacity());
    }

    @Test
    void setBatteryCapacity3() {
        int expResult = 3;
        s3.setBatteryCapacity(3);
        assertEquals(expResult, s3.getBatteryCapacity());
    }

    @Test
    void setState1() {
        VehicleState expResult = VehicleState.UNAVAILABLE;
        s1.setState(VehicleState.UNAVAILABLE);
        assertEquals(expResult, s1.getState());
    }

    @Test
    void setState2() {
        VehicleState expResult = VehicleState.AVAILABLE;
        s2.setState(VehicleState.AVAILABLE);
        assertEquals(expResult, s2.getState());
    }

    @Test
    void setState3() {
        VehicleState expResult = VehicleState.CHARGING;
        s3.setState(VehicleState.CHARGING);
        assertEquals(expResult, s3.getState());
    }

    @Test
    void setStateSpecial() {
        VehicleState expResult = VehicleState.BROKEN;
        s3.setState(VehicleState.BROKEN);
        assertEquals(expResult, s3.getState());
    }

    @Test
    void setBattery1() {
        float expResult = 1.0F;
        s1.setBattery(1.0F);
        assertEquals(expResult, s1.getBattery());
    }

    @Test
    void setBattery2() {
        float expResult = 2.0F;
        s2.setBattery(2.0F);
        assertEquals(expResult, s2.getBattery());
    }

    @Test
    void setBattery3() {
        float expResult = 3.0F;
        s3.setBattery(3.0F);
        assertEquals(expResult, s3.getBattery());
    }

    @Test
    void setMaxWeight1() {
        double expResult = 1.0D;
        s1.setMaxPayload(1.0D);
        assertEquals(expResult, s1.getMaxPayload());
    }

    @Test
    void setMaxWeight2() {
        double expResult = 2.0D;
        s2.setMaxPayload(2.0D);
        assertEquals(expResult, s2.getMaxPayload());
    }

    @Test
    void setMaxWeight3() {
        double expResult = 3.0D;
        s3.setMaxPayload(3.0D);
        assertEquals(expResult, s3.getMaxPayload());
    }

    @Test
    public void testEquals1() {
        Scooter o = new Scooter(true, 1.0F, 5);
        assertEquals(o, s1);
    }

    @Test
    void testEqualsNull1() {
        assertNotEquals(s1, null);
    }

    @Test
    public void testEqualsSameObject1() {
        assertEquals(s1, s1);
    }

    @Test
    public void testEqualsDifferentClassesObjects1() {
        assertNotEquals(new Product("produto1", 20, 5, 3, 1, "A65"), s1);
    }

    @Test
    public void testEquals2() {
        Scooter o = new Scooter(s1);
        assertEquals(o, s2);
    }

    @Test
    void testEqualsNull2() {
        assertNotEquals(s2, null);
    }

    @Test
    public void testEqualsSameObject2() {
        assertEquals(s2, s2);
    }

    @Test
    public void testEqualsDifferentClassesObjects2() {
        assertNotEquals(new Product("produto1", 20, 5, 3, 1, "OAWA2"), s2);
    }

    @Test
    public void testEquals3() {
        Scooter o = new Scooter(3, false, 3, VehicleState.UNAVAILABLE, 90, 3);
        assertEquals(o, s3);
    }

    @Test
    void testEqualsNull3() {
        assertNotEquals(s3, null);
    }

    @Test
    public void testEqualsSameObject3() {
        assertEquals(s3, s3);
    }

    @Test
    public void testEqualsDifferentClassesObjects3() {
        assertNotEquals(new Product("produto1", 20, 5, 3, 1, "JAWNBD"), s3);
    }


    @Test
    public void testEqualsSameClassDiferentInformation2() {
        assertNotEquals(s3, s1);
    }

    @Test
    public void testEqualsSameClassDiferentInformation4() {
        assertNotEquals(s3, s2);
    }

    @Test
    public void testEqualsSameClassDiferentInformation5() {
        assertNotEquals(s1, s3);
    }

    @Test
    public void testEqualsSameClassDiferentInformation6() {
        assertNotEquals(s2, s3);
    }

    @Test
    void testHashCode1() {
        int result = s1.hashCode();
        int expResult = 30;
        assertEquals(expResult, result);
    }

    @Test
    void testHashCode2() {
        int result = s2.hashCode();
        int expResult = 30;
        assertEquals(expResult, result);
    }

    @Test
    void testHashCode3() {
        int result = s3.hashCode();
        int expResult = 34;
        assertEquals(expResult, result);
    }

    @Test
    void toString1() {
        String expResult = String.format("|ID: %d |Battery Cappacity: %.2f Wh|State: %s |Battery: %.2f %%|Max Payload: %.2f Kg| "+
                        " Qr code: %s"
                , s1.getId(), s1.getBatteryCapacity(), s1.getState(), s1.getBattery(),
                s1.getMaxPayload(), s1.getQrCode());
        assertEquals(expResult, s1.toString());
    }

    @Test
    void toString2() {
        String expResult = String.format("|ID: %d |Battery Cappacity: %.2f Wh|State: %s |Battery: %.2f %%|Max Payload: %.2f Kg| " +
                        " Qr code: %s"
                , s2.getId(), s2.getBatteryCapacity(), s2.getState(), s2.getBattery(),
                s2.getMaxPayload(), s2.getQrCode());
        assertEquals(expResult, s2.toString());
    }

    @Test
    void toString3() {
        String expResult = String.format("|ID: %d |Battery Cappacity: %.2f Wh|State: %s |Battery: %.2f %%|Max Payload: %.2f Kg| " +
                        " Qr code: %s", s3.getId(), s3.getBatteryCapacity(), s3.getState(), s3.getBattery(),
                s3.getMaxPayload(), s3.getQrCode());
        assertEquals(expResult, s3.toString());
    }

}

