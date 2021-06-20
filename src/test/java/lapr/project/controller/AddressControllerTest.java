package lapr.project.controller;

import lapr.project.data.AddressDB;
import lapr.project.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressControllerTest {

    private AddressController instance;

    @BeforeEach
    void setUp() {
        AddressDB mockAddress = mock( AddressDB.class );

        Address address = new Address( "41.1496, 8.611", "Porto", "4000-101", 1 );
        Address fakeAddress = new Address("","","",0 );

        when( mockAddress.createAddress( address ) ).thenReturn( true );
        when( mockAddress.createAddress( fakeAddress ) ).thenReturn( false );

        instance = new AddressController( mockAddress );
    }

    @Test
    void testCreateValidAddress () {
        Address address = new Address( "41.1496, 8.611", "Porto", "4000-101", 1 );

        boolean result = instance.createAddress( address );

        assertTrue( result );
    }

    @Test
    void testCreateInvalidAddress () {
        Address fakeAddress = new Address("","","",0 );

        boolean result = instance.createAddress( fakeAddress );

        assertFalse( result );
    }
}
