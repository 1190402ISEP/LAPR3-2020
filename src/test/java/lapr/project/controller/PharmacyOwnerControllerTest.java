package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.data.PharmacyOwnerDB;
import lapr.project.data.UserSession;
import lapr.project.model.Pharmacy;
import lapr.project.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PharmacyOwnerControllerTest {

    private static PharmacyOwnerController instance;

    @BeforeAll
    public static void setUp() throws IOException {
        PharmacyOwnerDB teste = mock(PharmacyOwnerDB.class);
        PharmacyDB phaMock = mock(PharmacyDB.class);
        List<String> list = new ArrayList<>();
        list.add("ola");

        instance = new PharmacyOwnerController();
        when(phaMock.getPharmacyByUserEmail(any(String.class))).thenReturn(new Pharmacy(1,"teste", "teste", "teste"));

        when(teste.pharmacyOwnerHandlerCreate("nunofmdomingues2001@gmail.com",11234,"Jony Moreiras")).thenReturn(true);
        when(teste.pharmacyOwnerHandlerCreate("1190914@isep.ipp.pt",11235,"Ruizex")).thenReturn(false);
        when(teste.show()).thenReturn(list);
        instance = new PharmacyOwnerController(teste);
    }

    @Test
    void createPharmacyOwner1() {
        UserSession.getInstance().setUser(new User("teste", "teste"));
        boolean result = instance.createPharmacyOwner("nunofmdomingues2001@gmail.com",11234,"Jony Moreiras");
        assertTrue(result);
    }

    @Test
    void createPharmacyOwner2() {
        UserSession.getInstance().setUser(new User("teste", "teste"));
        boolean result = instance.createPharmacyOwner("1190914@isep.ipp.pt",11235,"Ruizex");
        assertFalse(result);
    }

    @Test
    void showOrders() {
        List<String> result = instance.showOwners();
        assertEquals("ola", result.get(0));
    }
}