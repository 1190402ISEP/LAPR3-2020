package lapr.project.controller;


import lapr.project.data.*;
import lapr.project.model.Pharmacy;
import lapr.project.model.Product;
import lapr.project.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    private static ProductController instance;

    @BeforeAll
    public static void setUp() {

        ProductDB teste = mock(ProductDB.class);
        PharmacyDB phaMock = mock(PharmacyDB.class);
        AddressDB addMock = mock(AddressDB.class);
        instance = new ProductController();


        when(phaMock.getPharmacyByUserEmail(any(String.class))).thenReturn(new Pharmacy(1,"teste", "teste", "teste"));


        when(teste.productHandlerCreate(new Product("TesteComprimido2",501,5f,3f,0.47f,"AH2S"),1)).thenReturn(false);
        when(teste.productHandlerRemove(5,1)).thenReturn(true);
        when(teste.productHandlerUpdate(10,"Brufen",230,18f,1.2f,0.45f, "A421Y",1)).thenReturn(true);
        when(teste.productHandlerCreate(new Product("TesteComprimido1",500,5f,3f,0.47f,"Y222"),1)).thenReturn(true);
        when(teste.productHandlerRemove(15,25)).thenReturn(false);
        when(teste.productHandlerUpdate(20,"Brufen",230,18f,1.2f,0.45f, "B23I",30)).thenReturn(false);
        when(teste.updateStockHandler(2, 2,2)).thenReturn(false);
        when(teste.updateStockHandler(1, 1, 1)).thenReturn(true);
        when(teste.getPharmacyByOwnerEmail("teste")).thenReturn(1);
        instance = new ProductController(phaMock, addMock, teste);
    }

    @Test
    public void testCreateProduct1()  {
        UserSession.getInstance().setUser(new User("teste", "teste"));
        boolean result = instance.createProduct("TesteComprimido1",500,5f,3f,0.47f,"Y222");
        assertTrue(result);
    }

    @Test
    public void testCreateProduct2()  {
        UserSession.getInstance().setUser(new User("teste", "teste"));
        boolean result = instance.createProduct("TesteComprimido2",501,5f,3f,0.47f,"AH2S");
        assertFalse(result);
    }

    @Test
    public void testRemoveProduct1()  {
        UserSession.getInstance().setUser(new User("teste", "teste"));
        boolean result = instance.removeProduct(5);
        assertTrue(result);
    }

    @Test
    public void testRemoveProduct2()  {
        UserSession.getInstance().setUser(new User("teste", "teste"));
        boolean result = instance.removeProduct(15);
        assertFalse(result);
    }

    @Test
    public void testUpdateProduct1()  {
        UserSession.getInstance().setUser(new User("teste", "teste"));
        boolean result = instance.updateProduct(10,"Brufen",230,18f,1.2f,0.45f,"A421Y");
        assertTrue(result);
    }

    @Test
    public void testUpdateProduct2()  {
        UserSession.getInstance().setUser(new User("teste", "teste"));
        boolean result = instance.updateProduct(20,"Brufen",230,18f,1.2f,0.45f,"B23I");
        assertFalse(result);
    }

    @Test
    void updateStock1() {
        boolean result = instance.updateStock(2,6);
        assertFalse(result);
    }

    @Test
    void updateStock2() {
        UserSession.getInstance().setUser(new User("teste", "teste"));
        boolean result = instance.updateStock(1,1);

        assertTrue(result);
    }
}