package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.data.PlanningDB;
import lapr.project.data.UserSession;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CheckoutBasketControllerTest {

    private static CheckoutBasketController controller;

    @BeforeAll
    static void setUp() {
        controller = new CheckoutBasketController();
        ClientDB dbMock = mock(ClientDB.class);
        when(dbMock.getClientCreditCardsByEmail("41.155035, -8.637854")).thenReturn(new ArrayList<>());
        List<CreditCard> list = new ArrayList<>();
        List<Order> list2 = new ArrayList<>();
        list.add(new CreditCard("dummy", 123, new Date()));
        LocalDateTime time = LocalDateTime.of(2021, Month.JULY, 29, 19, 30, 40);
        list2.add(new Order(1,time, 1));
        when(dbMock.getClientCreditCardsByEmail("2")).thenReturn(list);
        when(dbMock.getClientLastAddedCard(any(String.class))).thenReturn(new CreditCard("dummy", 123, new Date()));
        when(dbMock.hasClientNif("1")).thenReturn(true);
        when(dbMock.hasClientNif("2")).thenReturn(false);
        when(dbMock.getClientNifByEmail(any(String.class))).thenReturn(1);
        when(dbMock.getClientCreditsByEmail(any(String.class))).thenReturn(1);
        when(dbMock.newOrder("1", new ArrayList<>(), new CreditCard("dummy", 123, new Date()), 1, 1, 1)).thenReturn(true);
        when(dbMock.getProductsInBasketByClientEmail(any(String.class))).thenReturn(new ArrayList<>());
        when(dbMock.changeOrderToCanceled(1)).thenReturn(true);
        when(dbMock.getClientOrdersByEmail(any(String.class))).thenReturn(new ArrayList<>());
        when(dbMock.getClientOrdersByEmail("2")).thenReturn(list2);

        controller = new CheckoutBasketController(dbMock);


    }

    @Test
    void getClient() {
        UserSession.getInstance().setUser(new User("teste", "teste"));
        String result = controller.getClient();
        assertEquals("teste", result);
    }

    @Test
    void getCreditCardsClient() {
        UserSession.getInstance().setUser(new User("1", "teste"));
        controller.getClient();
        List<CreditCard> result = controller.getCreditCardsClient();
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void getCreditCardsClient2() {
        UserSession.getInstance().setUser(new User("2", "teste"));
        controller.getClient();
        List<CreditCard> list = new ArrayList<>();
        list.add(new CreditCard("dummy", 123, new Date()));
        List<CreditCard> result = controller.getCreditCardsClient();
        assertEquals(list, result);
    }

    @Test
    void getCardByOption() {
        CreditCard expResult = new CreditCard("dummy", 123, new Date());
        List<CreditCard> list = new ArrayList<>();
        list.add(expResult);

        CreditCard result = controller.getCardByOption(list, 1);

        assertEquals(expResult, result);


    }

    @Test
    void getClientLastAddedCard() {
        UserSession.getInstance().setUser(new User("teste", "teste"));
        controller.getClient();
        CreditCard expResult = new CreditCard("dummy", 123, new Date());
        CreditCard result = controller.getClientLastAddedCard();
        assertEquals(expResult, result);
    }

    @Test
    void hasClientNif() {
        UserSession.getInstance().setUser(new User("1", "teste"));
        controller.getClient();
        boolean result = controller.hasClientNif();
        assertTrue(result);
    }

    @Test
    void hasClientNif2() {
        UserSession.getInstance().setUser(new User("2", "teste"));
        controller.getClient();
        boolean result = controller.hasClientNif();
        assertFalse(result);

    }

    @Test
    void setNif() {
        boolean result = controller.setNif(1);
        assertTrue(result);
    }


    @Test
    void getClientCreditsByEmail() {
        UserSession.getInstance().setUser(new User("1", "teste"));
        controller.getClient();
        int result = controller.getClientCreditsByEmail();
        assertEquals(result, 1);
    }

    @Test
    void newOrder() {
        controller = new CheckoutBasketController();
        ClientDB dbMock = mock(ClientDB.class);
        when(dbMock.getClientCreditCardsByEmail("41.155035, -8.637854")).thenReturn(new ArrayList<>());
        List<CreditCard> list = new ArrayList<>();
        list.add(new CreditCard("dummy", 123, new Date()));
        when(dbMock.getClientCreditCardsByEmail("2")).thenReturn(list);
        when(dbMock.getClientLastAddedCard(any(String.class))).thenReturn(new CreditCard("dummy", 123, new Date()));
        when(dbMock.hasClientNif("1")).thenReturn(true);
        when(dbMock.hasClientNif("2")).thenReturn(false);
        when(dbMock.getClientNifByEmail(any(String.class))).thenReturn(1);
        when(dbMock.getClientCreditsByEmail(any(String.class))).thenReturn(1);
        when(dbMock.newOrder("1", new ArrayList<>(), new CreditCard("dummy", 123, new Date()), 1, 1, 1)).thenReturn(true);
        when(dbMock.getProductsInBasketByClientEmail(any(String.class))).thenReturn(new ArrayList<>());

        MockedStatic<DeliveryPlanning> planMock = mockStatic(DeliveryPlanning.class);
        planMock.when(() -> {
            DeliveryPlanning.getIdClosestPharmacyByUserEmail(any(String.class), any(PlanningDB.class));
        }).thenReturn(1);

        controller = new CheckoutBasketController(dbMock);


        UserSession.getInstance().setUser(new User("1", "teste"));
        controller.getClient();
        controller.setNif(1);
        controller.getClientLastAddedCard();
        controller.getClientCreditsByEmail();
        boolean result = controller.newOrder(1);
        planMock.close();
        assertTrue(result);
    }

    @Test
    void getIdClosestPharmacy() {
        controller = new CheckoutBasketController();


        MockedStatic<DeliveryPlanning> planMock = mockStatic(DeliveryPlanning.class);
        planMock.when(() -> {
            DeliveryPlanning.getIdClosestPharmacyByUserEmail(any(String.class), any(PlanningDB.class));
        }).thenReturn(0);

        UserSession.getInstance().setUser(new User("1", "teste"));

        controller.getClient();

        int result = controller.getIdClosestPharmacy();

        planMock.close();

        assertEquals(0, result);


    }

    @Test
    void getNif(){
        controller.setNif(1);
        int result=controller.getNif();
        assertEquals(1,result);
    }

    @Test
    void changeOrderToCanceled(){
        UserSession.getInstance().setUser(new User("2", "teste"));
        controller.getClient();
        boolean result = controller.changeOrderToCanceled(1);
        assertTrue(result);

    }

    @Test
    void getClientOrdersByEmail(){
        UserSession.getInstance().setUser(new User("1", "teste"));
        controller.getClient();
        List<Order> result = controller.getClientOrdersByEmail();
        List<Order> expResult= new ArrayList<>();

        assertEquals(result, expResult);
    }
/*
    @Test
    void getClientOrdersByEmail2() {
        UserSession.getInstance().setUser(new User("2", "teste"));
        controller.getClient();
        List<Order> expResult = new ArrayList<>();
        LocalDateTime time = LocalDateTime.of(2021, Month.JULY, 29, 19, 30, 40);
        expResult.add(new Order(1,time, 1));
        List<Order> result = controller.getClientOrdersByEmail();
        assertEquals(expResult,result);
    }*/

    /*@Test
    void getOrderByOption() {
        LocalDateTime rightNow = LocalDateTime.now();
        Order expResult = new Order(1, rightNow,1);
        List<Order> list = new ArrayList<>();
        list.add(expResult);

        Order result = controller.getOrderByOption(list, 1);

        assertEquals(expResult, result);
    }*/




}