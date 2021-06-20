package lapr.project.controller;
import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;
import lapr.project.model.utils.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScooterControllerTest {

private static ScooterController instance;

    @BeforeAll
    public static void setUp() {

        ScooterDB teste = Mockito.mock(ScooterDB.class);
        instance = new ScooterController();
        ArrayList<Scooter> lista = new ArrayList<>();
        lista.add(new Scooter( true, 1.0F, 2.0D));
        lista.add(new Scooter( true, 1.0F, 2.0D));
        when(teste.scooterHandlerAdd(new Scooter( true, 5000, 30),1,100)).thenReturn(1);
        when(teste.scooterHandlerAdd(new Scooter( true, 5000, 30),2,100)).thenReturn(0);
        when(teste.scooterHandlerUpdate(1,1,120,3,44,233,1)).thenReturn(true);
        when(teste.scooterHandlerUpdate(1,1,1.0F,3,44,2.0D,1)).thenReturn(false);
        when(teste.scooterHandlerRemove(0,1)).thenReturn(true);
        when(teste.scooterHandlerRemove(2,2)).thenReturn(false);
        when(teste.getAllScooters(1)).thenReturn(lista);
        Scooter novaScooter= new Scooter (true,500,30);
        when(teste.getScooterByID(0)).thenReturn(new Scooter(novaScooter));
        when(teste.getScooterByID(2)).thenReturn(null);

        Scooter s = null;
        when( teste.scooterHandlerAdd( s, 0,0 ) ).thenReturn( 0 );
        instance=new ScooterController(teste);
        Utils.setPath("");
    }

    @Test
    public void testAddScooter1()  {
        int result = (instance.insertScooter(1,5000,30,1,100)).getId();
        assertEquals(1,result);
    }

    @Test
    public void testAddScooter2()  {
        int result = (instance.insertScooter(1,5000,30,2,100)).getId();
        assertEquals(0,result);
    }

    @Test
    public void testUpdateScooter1() {
        boolean result = instance.updateScooter(1, 1, 120, 233, 1, 44, 3);
        assertTrue(result);
    }

    @Test
    public void testUpdateScooter2() {
        boolean result = instance.updateScooter(1, 1, 1.0F, 2.0D, 1, 44, 3);
        assertFalse(result);
    }

    @Test
    public void testRemoveScooter1()  {
        boolean result = instance.removeScooter(0,1);
        assertTrue(result);
    }

    @Test
    public void testRemoveScooter2()  {
        boolean result = instance.removeScooter(2,1);
        assertFalse(result);
    }

    @Test
    public void testgetAllScooter1()  {
        List<Scooter>test= new ArrayList<>();
        test.add(new Scooter( true, 1.0F, 2.0D));
        test.add(new Scooter( true, 1.0F, 2.0D));
        List<Scooter> result = instance.getAllScooters(1);
        assertEquals(test,result);
    }

    @Test
    public void testgetAllScooter2()  {
        List<Scooter> result = instance.getAllScooters(4);
        List<Scooter> expResult = new ArrayList<>();
        assertEquals(result, expResult);
    }
}
