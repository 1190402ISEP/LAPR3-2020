/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author anton
 */
public class VehicleUtilsTest {


    
    /**
     * Test of getStateByID method, of class VehicleUtils.
     */
    @Test
    public void testGetStateByID1() {
        int id = 1;
        VehicleState result = VehicleUtils.getStateByID(id);
        assertEquals(VehicleState.AVAILABLE, result);
    }
    /**
     * Test of getStateByID method, of class VehicleUtils.
     */
    @Test
    public void testGetStateByID2() {
        int id = 2;
        VehicleState result = VehicleUtils.getStateByID(id);
        assertEquals(VehicleState.UNAVAILABLE, result);
    }
    /**
     * Test of getStateByID method, of class VehicleUtils.
     */
    @Test
    public void testGetStateByID3() {
        int id = 3;
        VehicleState result = VehicleUtils.getStateByID(id);
        assertEquals(VehicleState.BROKEN, result);
    }
    /**
     * Test of getStateByID method, of class VehicleUtils.
     */
    @Test
    public void testGetStateByID4() {
        int id = 4;
        VehicleState result = VehicleUtils.getStateByID(id);
        assertEquals(VehicleState.CHARGING, result);
    }
    /**
     * Test of getStateByID method, of class VehicleUtils.
     */
    @Test
    public void testGetStateByID5() {
        int id = 5;
        VehicleState result = VehicleUtils.getStateByID(id);
        assertNull(result);
    }

    @Test
    public void test(){
        VehicleUtils.listarStates();
    }
}
