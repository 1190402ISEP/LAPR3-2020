package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.model.Drone;

import lapr.project.ui.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DroneControllerTest {

private static DroneController instance;

    @BeforeAll
    public static void setUp() {

        DroneDB teste = Mockito.mock(DroneDB.class);
        instance = new DroneController();
        ArrayList<Drone> lista = new ArrayList<>();
        lista.add(new Drone(100,30));
        lista.add(new Drone(2,1));
        when(teste.droneHandlerAdd(new Drone(100,30),1,100)).thenReturn(1);
        when(teste.droneHandlerAdd(new Drone(100,30),2,100)).thenReturn(0);
        when(teste.droneHandlerUpdate(1,330,2,50,340,1)).thenReturn(true);
        when(teste.droneHandlerUpdate(1,2,3,44,233,1)).thenReturn(false);
        when(teste.droneHandlerRemove(1,1)).thenReturn(true);
        when(teste.droneHandlerRemove(2,2)).thenReturn(false);
        when(teste.getAllDrones(1)).thenReturn(lista);
        instance=new DroneController(teste);
    }

    @Test
    public void testAddDrone1()  {
        int result = (instance.insertDrone(100,30, 1,100).getId());
        assertEquals(1,result);
    }

    @Test
    public void testUpdateDrone1() {
        boolean result = instance.updateDrone(1,330,340,1,50,2);
        assertTrue(result);
    }

    @Test
    public void testUpdateDrone2() {
        boolean result = instance.updateDrone(1,2,233,1,44,3);
        assertFalse(result);
    }

    @Test
    public void testRemoveDrone1()  {
        boolean result = instance.removeDrone(1,1);
        assertTrue(result);
    }

    @Test
    public void testRemoveDrone2()  {
        boolean result = instance.removeDrone(2,1);
        assertFalse(result);
    }

    @Test
    public void testgetAllDrones1()  {
        List<Drone>test= new ArrayList<>();
        test.add(new Drone(100,30));
        test.add(new Drone(2,1));
        List<Drone> result = instance.getAllDrones(1);
        assertEquals(test,result);
    }

    @Test
    public void testgetAllDrones2()  {
        List<Drone> result = instance.getAllDrones(4);
        List<Drone> expResult = new ArrayList<>();
        assertEquals(result, expResult);
    }

    @Test
    public void testInsertDrone () {
        DroneDB teste = Mockito.mock( DroneDB.class );
        Drone drone = new Drone( -1, -1 );
        drone.setId( 0 );
        when( teste.droneHandlerAdd( drone, -1, -1 ) ).thenReturn( 0 );

        DroneController instance = new DroneController( teste );

        Drone actual = instance.insertDrone( -1, -1, -1, -1 );

        assertEquals( drone,  actual );
    }

    @Test
    void testInsertDrone2 () {
        DroneDB teste = Mockito.mock( DroneDB.class );
        Drone drone = new Drone( -1, -1 );
        drone.setId( 0 );
        when( teste.droneHandlerAdd( drone, -1, -1 ) ).thenReturn( 0 );

        DroneController instance = new DroneController( teste );

        Drone actual = instance.insertDrone( -1, -1, -1, -1 );

        assertEquals( drone,  actual );
    }

    @Test
    void testInsertDrone3 () {
        DroneDB teste = Mockito.mock( DroneDB.class );
        Drone drone = new Drone( 1, 1 );
        when( teste.droneHandlerAdd( drone, 1, 1 ) ).thenReturn( 10 );

        DroneController instance = new DroneController( teste );

        Drone actual = instance.insertDrone( 1, 1, 1, 1 );
        drone.setId( 10 );

        assertEquals( drone,  actual );
    }
}
