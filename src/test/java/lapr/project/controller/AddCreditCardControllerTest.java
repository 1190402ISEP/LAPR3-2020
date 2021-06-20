package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.data.UserSession;
import lapr.project.model.CreditCard;
import lapr.project.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


class AddCreditCardControllerTest {


    private static AddCreditCardController controller;

    @BeforeAll
    static void setUp() {
        controller = new AddCreditCardController();
        ClientDB dbMock = mock(ClientDB.class);
        when(dbMock.newCreditCard(new CreditCard("dummy", 123, new Date()), "1")).thenReturn(true);
        when(dbMock.newCreditCard(new CreditCard("dummy", 123, new Date()), "2")).thenReturn(false);
        controller = new AddCreditCardController(dbMock);
    }

    @Test
    void newCreditCard() {

        UserSession.getInstance().setUser(new User("1", "Rui"));

        boolean result = controller.newCreditCard("dummy", 123, new Date());

        assertTrue(result);

    }

    @Test
    void newCreditCard2() {

        UserSession.getInstance().setUser(new User("2", "Rui"));

        boolean result = controller.newCreditCard("dummy", 123, new Date());

        assertFalse(result);

    }
}