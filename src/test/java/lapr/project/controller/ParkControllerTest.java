package lapr.project.controller;

import lapr.project.data.ParkDB;
import lapr.project.model.Park;
import lapr.project.model.ParkPlace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkControllerTest {

    private ParkController instance;
    private Park validPark;
    private Park invalidPark;
    private ParkDB mock;

    @BeforeEach
    void setUp() {
        mock = mock( ParkDB.class );
        instance = new ParkController();

        validPark = new Park( 1, 240, 10, 1, 10 );
        invalidPark = new Park( -1, -1, 10, -1, 0 );

        when( mock.createPark( validPark ) ).thenReturn( true );
        when( mock.createPark( invalidPark ) ).thenReturn( false );

        instance = new ParkController( mock );
    }

    @Test
    void createValidParkTest () {
        Park validPark = new Park( 1, 240, 10, 1, 10 );
        boolean result = instance.createPark( validPark );

        assertTrue( result );
    }

    @Test
    void createInvalidParkTest () {
        Park invalidPark = new Park( -1, -1, 10, -1, 0 );
        boolean actual = instance.createPark( invalidPark );

        assertFalse( actual );
    }

    @Test
    void updateValidParkTest () {
        when( mock.updatePark( validPark )).thenReturn( true );
        assertTrue( instance.updatePark( validPark ) );
    }

    @Test
    void updateInvalidParkTest () {
        when( mock.updatePark( invalidPark )).thenReturn( false );
        assertFalse( instance.updatePark( invalidPark ) );
    }

    @Test
    void removeValidParkTest () {
        when( mock.removePark( validPark.getID() )).thenReturn( true );
        assertTrue( instance.removePark( validPark.getID() ) );
    }

    @Test
    void removeInvalidParkTest () {
        when( mock.removePark( invalidPark.getID() )).thenReturn( false );
        assertFalse( instance.removePark( invalidPark.getID() ) );
    }

    @Test
    void showParkTest () {
        when( mock.showParks() ).thenReturn( Collections.emptyList() );
        assertEquals( new ArrayList<>(), instance.showParks());
    }

    @Test
    void showParkInvalidTest () {
        List<Park> parks = new ArrayList<>();
        parks.add(validPark);
        parks.add(validPark);
        parks.add(validPark);
        when(mock.showParks()).thenReturn(parks);
        assertEquals(parks, instance.showParks());
    }

    @Test
    void createParkPlaceTest () {
        when( mock.createPlace( new ParkPlace( 1, true),true) ).thenReturn( true );
        assertTrue( instance.createParkPlace(new ParkPlace(1, true),true));
    }

    @Test
    void createParkPlaceInvalidTest () {
        when( mock.createPlace( new ParkPlace( -1, false),true) ).thenReturn( false );
        assertFalse( instance.createParkPlace( new ParkPlace( -1, false) ,true) );
    }


}
