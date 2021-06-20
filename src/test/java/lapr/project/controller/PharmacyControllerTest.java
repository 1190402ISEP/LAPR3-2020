package lapr.project.controller;

import lapr.project.data.AddressDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.UserSession;
import lapr.project.model.Address;

import lapr.project.model.Pharmacy;
import lapr.project.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PharmacyControllerTest {

    private PharmacyController instance;


    @BeforeEach
    void setUp() {
        PharmacyDB mockPharmacy = mock( PharmacyDB.class );
        AddressDB mockAddress = mock( AddressDB.class );
        instance = new PharmacyController();

        Address address = new Address( "41.1496, 8.611", "Porto", "4000-101", 1 );
        Address fakeAddress = new Address("","","",0 );

        ArrayList<Pharmacy> lista = new ArrayList<>();
        lista.add(new Pharmacy(10,"teste", "teste", "teste"));
        lista.add(new Pharmacy(10,"teste", "teste", "teste"));

        when( mockAddress.createAddress( address ) ).thenReturn( true );
        when( mockAddress.createAddress( fakeAddress ) ).thenReturn( false );

        when( mockPharmacy.createPharmacy( "po@gmail.com", "Pharmacy2", address ) ).thenReturn( 1 );
        when( mockPharmacy.createPharmacy( "pa@gmail.com", "Pharmacy2", fakeAddress ) ).thenReturn( 2 );

        when( mockPharmacy.getAllPharmacies()).thenReturn(lista);

        when( mockPharmacy.getPharmacyByUserEmail("po@gmail.com"))
                .thenReturn( new Pharmacy( 1,"Pharmacy2", address.toString(), "po@gmail.com"));

        when( mockPharmacy.update("po@gmail.com", "Pharmacy1", address ) ).thenReturn( true );
        when( mockPharmacy.update("po@gmail.com", "Pharmacy2", fakeAddress ) ).thenReturn( false );

        instance = new PharmacyController( mockPharmacy, mockAddress );
    }


    @Test
    void createValidPharmacyTest () {
        Address address = new Address( "41.1496, 8.611", "Porto", "4000-101", 1 );
        int result = instance.createPharmacy( "po@gmail.com", "Pharmacy2", address );

        assertEquals(1, result);
    }

    @Test
    void createInvalidAddressPharmacyTest () {
        Address fakeAddress = new Address("","","",0 );
        int result = instance.createPharmacy( "po@gmail.com", "Pharmacy2", fakeAddress );

        assertEquals(0, result);
    }

    @Test
    void createInvalidPharmacyTest () {
        Address address = new Address( "41.1496, 8.611", "Porto", "4000-101", 1 );
        int result = instance.createPharmacy( "", "Pharmacy2", address );

        assertEquals(0, result);

    }

    @Test
    void createValidAddressTest () {
        Address address = new Address( "41.1496, 8.611", "Porto", "4000-101", 1 );
        boolean result = instance.createAddress( address );

        assertTrue( result );
    }

    @Test
    void createInvalidAddressTest () {
        Address fakeAddress = new Address("","","",0 );
        boolean result = instance.createAddress( fakeAddress );

        assertFalse( result );
    }

    @Test
    void getAllPharmacies(){
        List<Pharmacy> teste = new ArrayList<>();
        teste.add(new Pharmacy(10,"teste", "teste", "teste"));
        teste.add(new Pharmacy(10,"teste", "teste", "teste"));
        List<Pharmacy> result = instance.getAllPharmacies();
        assertEquals(teste,result);
    }

    @Test
    void getPharmacyByUserEmail() {
        Pharmacy expResult = new Pharmacy(1,"Pharmacy2","Address{coordinates='41.1496, 8.611', street='Porto', postalCode='4000-101', doorNumber=1}","po@gmail.com");
        User a = new User("po@gmail.com","123");
        UserSession.getInstance().setUser(a);
        a.setEmail("po@gmail.com");
        Pharmacy result = instance.getPharmacyByUserEmail();
        assertEquals(expResult, result);
    }

    @Test
    void updatePharmacy1(){
        Address address = new Address( "41.1496, 8.611", "Porto", "4000-101", 1 );
        boolean result = instance.updatePharmacy("po@gmail.com", "Pharmacy1", address);
        assertTrue(result);
    }

    @Test
    void updatePharmacy2(){
        Address fakeAddress = new Address("","","",0 );
        boolean result = instance.updatePharmacy("po@gmail.com", "Pharmacy1", fakeAddress);
        assertFalse(result);
    }

}


