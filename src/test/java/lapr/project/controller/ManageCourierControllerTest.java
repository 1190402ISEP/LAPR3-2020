package lapr.project.controller;

import lapr.project.data.CourierDB;
import lapr.project.model.Courier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ManageCourierControllerTest {

    private static ManageCourierController instance;

    @BeforeAll
    public static void setUp() {
        CourierDB teste = mock(CourierDB.class);
        instance = new ManageCourierController();
        List<Courier> lista = new ArrayList<>();
        Courier jose=new Courier("José","jose@gmail.com",111111111,"11111111111");
        lista.add(jose);
        when(teste.newCourier("José","jose@gmail.com",111111111,"11111111111")).thenReturn(new Courier("José","jose@gmail.com",111111111,"11111111111"));
        when(teste.addCourierHandler(new Courier("José","jose@gmail.com",111111111,"11111111111"),1,1)).thenReturn(true);
        when(teste.addCourierHandler(new Courier("José","jose@gmail.com",111111111,"11111111111"),1,2)).thenReturn(false);
        when(teste.updateCourier("José","jose@gmail.com",111111111,"11111111111",1,1,1)).thenReturn(true);
        when(teste.updateCourier("José","jose2@gmail.com",111111111,"11111111111",1,1,2)).thenReturn(false);
        when(teste.getCourierList(1)).thenReturn(lista);
        instance = new ManageCourierController(teste);
    }

    @Test
    void newCourier() {
        Courier c=new Courier("José","jose@gmail.com",111111111,"11111111111");
        Courier result = instance.newCourier("José","jose@gmail.com",111111111,"11111111111");
        assertEquals(c,result);
    }

    @Test
    void addCourier() {
        Courier expected=new Courier("José","jose@gmail.com",111111111,"11111111111");
        Courier result = instance.addCourier("José","jose@gmail.com",111111111,"11111111111",1,1);
        assertEquals(expected,result);
    }

    @Test
    void addCourier2() {
        Courier expected=new Courier("José","jose@gmail.com",111111111,"11111111111");
        Courier result = instance.addCourier("José","jose@gmail.com",111111111,"11111111111",1,2);
        assertEquals(expected,result);
    }


    @Test
    void getCourierList() {
        List<Courier>test= new ArrayList<>();
        test.add(new Courier("José","jose@gmail.com",111111111,"11111111111"));
        List<Courier> result = instance.getCourierList(1);
        assertEquals(test,result);

    }


    @Test
    void updateCourier() {
        boolean result = instance.updateCourier("José","jose@gmail.com",111111111,"11111111111",1,1,1);
        assertTrue(result);
    }

    @Test
    void updateCourier2() {
        boolean result = instance.updateCourier("José","jose2@gmail.com",111111111,"11111111111",1,1,2);
        assertFalse(result);
    }
}