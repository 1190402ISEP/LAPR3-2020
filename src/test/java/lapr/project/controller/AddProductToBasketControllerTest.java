package lapr.project.controller;

import lapr.project.data.ProductDB;
import lapr.project.data.UserSession;
import lapr.project.model.Product;
import lapr.project.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddProductToBasketControllerTest {

    private static AddProductToBasketController controller;



    @Test
    void getProductsList() {
        controller = new AddProductToBasketController();
        ProductDB dbMock = mock(ProductDB.class);
        List<Product> list = new ArrayList<>();
        list.add(new Product("A23Y"));
        when(dbMock.getProductsList()).thenReturn(list);

        controller = new AddProductToBasketController(dbMock);

        List<Product> result = controller.getProductsList();
        assertEquals(list, result);
    }

    @Test
    void getProductsList2() {
        controller = new AddProductToBasketController();
        ProductDB dbMock = mock(ProductDB.class);

        when(dbMock.getProductsList()).thenReturn(null);

        controller = new AddProductToBasketController(dbMock);

        List<Product> result = controller.getProductsList();
        assertNull(result);
    }

    @Test
    void addProductToBasket() {
        controller = new AddProductToBasketController();
        ProductDB dbMock = mock(ProductDB.class);
        when(dbMock.addBasketProduct(any(String.class), any(String.class), any(Integer.class))).thenReturn(true);
        List<Product> list = new ArrayList<>();
        list.add(new Product("1", "1", 2,2));
        when(dbMock.getProductsList()).thenReturn(list);
        controller = new AddProductToBasketController(dbMock);


        UserSession.getInstance().setUser(new User("teste@gmail.com", "teste"));

        controller.getProductsList();

        boolean result = controller.addProductToBasket(1, 1);

        assertTrue(result);

    }

    @Test
    void addProductToBasket2() {
        controller = new AddProductToBasketController();
        ProductDB dbMock = mock(ProductDB.class);
        when(dbMock.addBasketProduct(any(String.class), any(String.class), any(Integer.class))).thenReturn(false);
        List<Product> list = new ArrayList<>();
        list.add(new Product("1", "1", 2,2));
        when(dbMock.getProductsList()).thenReturn(list);
        controller = new AddProductToBasketController(dbMock);


        UserSession.getInstance().setUser(new User("teste@gmail.com", "teste"));

        controller.getProductsList();

        boolean result = controller.addProductToBasket(1, 1);
        System.out.println(result);
        assertFalse(result);

    }
}