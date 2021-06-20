package lapr.project.controller;


import lapr.project.data.UserDB;
import lapr.project.data.UserSession;
import lapr.project.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserControllerTest {

    private static UserController instance;

    @BeforeAll
    public static void setUp() throws SQLException {

        UserDB teste = Mockito.mock(UserDB.class);
        instance = new UserController();
        when(teste.getUserPaper("Cliente", "cliente", 1)).thenReturn(1);
        when(teste.getUserPaper("PharmacyOwner", "pharmacyOwner", 2)).thenReturn(1);
        when(teste.getUserPaper("Wrong", "wrong", 2)).thenReturn(0);
        when(teste.addClient("Correct", 0, "Correct", "correct", "correct", "correct", "correct", 0)).thenReturn(true);
        when(teste.addClient("Incorrect", 0, "Incorrect", "incorrect", "incorrect", "incorrect", "incorrect", 0)).thenReturn(false);
        instance=new UserController(teste);
    }

    @Test
    void authenticateCliente() {
        System.out.println("AuthenticateClienteTest");
        User expResult = new User("Cliente", "cliente");
        expResult.setRole("CLIENT");

        assertTrue(instance.authenticate("Cliente", "cliente"));

        User result = UserSession.getInstance().getUser();
        assertEquals(result.getEmail(), expResult.getEmail());
        assertEquals(result.getPassword(), expResult.getPassword());
        assertEquals(result.getRole(), expResult.getRole());
    }

    @Test
    void authenticatePharmacyOwner() {
        System.out.println("AuthenticatePharmacyOwnerTest");
        User expResult = new User("PharmacyOwner", "pharmacyOwner");
        expResult.setRole("PHARMACY_OWNER");

        assertTrue(instance.authenticate("PharmacyOwner", "pharmacyOwner"));

        User result = UserSession.getInstance().getUser();
        assertEquals(result.getEmail(), expResult.getEmail());
        assertEquals(result.getPassword(), expResult.getPassword());
        assertEquals(result.getRole(), expResult.getRole());
    }

    @Test
    void authenticateFail() {
        System.out.println("AuthenticateFailTest");

        assertFalse(instance.authenticate("Wrong", "wrong"));
    }

    @Test
    void addClientCorrect() throws SQLException {
        System.out.println("addClienteWork");

        assertTrue(instance.addClient("Correct", 0, "Correct", "correct", "correct", "correct", "correct", 0));
    }

    @Test
    void addClientInCorrect() throws SQLException {
        System.out.println("addClienteWrong");

        assertFalse(instance.addClient("Incorrect", 0, "Incorrect", "incorrect", "incorrect", "incorrect", "incorrect", 0));
    }
}