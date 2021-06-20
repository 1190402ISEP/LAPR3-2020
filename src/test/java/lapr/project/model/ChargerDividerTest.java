package lapr.project.model;

import lapr.project.data.EmailHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ChargerDividerTest {

    @BeforeEach
    void setUp() {
        ChargerDivider.chargingscooters = new HashMap<>();

        //Primeiro Charging
        int pharmacyId = 1;
        int ScooterId = 1;
        String CourierEmail = "jpvcmoreira12@gmail.com";
        Long timestart = 2L;
        Long timeend = 3600002L;
        Charging c = new Charging(pharmacyId, ScooterId, CourierEmail);
        List<Long> time = new ArrayList<>();
        time.add(timestart);
        time.add(timeend);
        ChargerDivider.chargingscooters.put(time, c);

        //Segundo Charging
        int pharmacyId2 = 2;
        int ScooterId2 = 3;
        String CourierEmail2 = "email";
        Long timestart2 = 0L;
        Long timeend2 = 10L;
        Charging c2 = new Charging(pharmacyId2, ScooterId2, CourierEmail2);
        List<Long> time2 = new ArrayList<>();
        time2.add(timestart2);
        time2.add(timeend2);
        ChargerDivider.chargingscooters.put(time2, c2);

        //Terceiro Charging
        int pharmacyId3 = 2;
        int ScooterId3 = 3;
        String CourierEmail3 = "email";
        Long timestart3 = 50L;
        Long timeend3 = 100L;
        Charging c3 = new Charging(pharmacyId3, ScooterId3, CourierEmail3);
        List<Long> time3 = new ArrayList<>();
        time3.add(timestart3);
        time3.add(timeend3);
        ChargerDivider.chargingscooters.put(time3, c3);
    }

    @Test
    void putMapndSend1() {
        EmailHandler eh = mock(EmailHandler.class);

        when(eh.sendEmail("moussamanafa@outlook.pt", "Vehicle charging", "The vehicle with the ID-2 is now in a charging state and it will take 2 hours and 0 minutes to charge")).thenReturn(true);
        when(eh.sendEmail("moussamanafa@outlook.pt", "Vehicle charging delay", "The vehicle with the ID-1 has delayed its charging time and now it will take 2 hours and 0 minutes to charge")).thenReturn(true);


        int expResult = 1;
        int result = ChargerDivider.putMapndSend(1, 2, "jpvcmoreira12@gmail.com", 3600002L, 7200002L, eh);

        assertEquals(expResult, result);
    }

    @Test
    void putMapndSend2() {
        EmailHandler eh = mock(EmailHandler.class);

        when(eh.sendEmail("moussamanafa@outlook.pt", "Vehicle charging", "The vehicle with the ID-2 is now in a charging state and it will take 2 hours and 0 minutes to charge")).thenReturn(true);
        when(eh.sendEmail("moussamanafa@outlook.pt", "Vehicle charging delay", "The vehicle with the ID-1 has delayed its charging time and now it will take 2 hours and 0 minutes to charge")).thenReturn(true);


        int expResult = 1;
        int result = ChargerDivider.putMapndSend(1, 2, "jpvcmoreira12@gmail.com", 1800000L, 5400000L, eh);

        assertEquals(expResult, result);
    }

    @Test
    void putMapndSend3() {
        EmailHandler eh = mock(EmailHandler.class);

        when(eh.sendEmail("moussamanafa@outlook.pt", "Vehicle charging", "The vehicle with the ID-2 is now in a charging state and it will take 2 hours and 12 minutes to charge")).thenReturn(true);

        int expResult = 0;
        int result = ChargerDivider.putMapndSend(1, 2, "jpvcmoreira12@gmail.com", 3600005L, 4320005L, eh);

        assertEquals(expResult, result);
    }

    @Test
    void countHowManyCharging1() {

        //A esquerda
        int expResult = 0;
        int result = ChargerDivider.countHowManyCharging(0L,1L, 1);
        assertEquals(result, expResult);
        //Outra farmacia
        int expResult2 = 0;
        int result2 = ChargerDivider.countHowManyCharging(11L,15L, 2);
        assertEquals(result2, expResult2);
        //A direita
        int expResult3 = 0;
        int result3 = ChargerDivider.countHowManyCharging(5400005L,5400010L, 1);
        assertEquals(result3, expResult3);
        //A esquerda como contacto
        int expResult4 = 1;
        int result4 = ChargerDivider.countHowManyCharging(0L,20L, 1);
        assertEquals(result4, expResult4);
        //A direita com contacto
        int expResult5 = 1;
        int result5 = ChargerDivider.countHowManyCharging(3600000L,5400010L, 1);
        assertEquals(result5, expResult5);
        //A centro com contacto
        int expResult6 = 1;
        int result6 = ChargerDivider.countHowManyCharging(20L,30L, 1);
        assertEquals(result6, expResult6);
        //Iguais
        int expResult7 = 1;
        int result7 = ChargerDivider.countHowManyCharging(2L,3600002L, 1);
        assertEquals(result7, expResult7);
        //Tocar em dois pelas pontas
        int expResult8 = 2;
        int result8 = ChargerDivider.countHowManyCharging(5L,55L, 2);
        assertEquals(result8, expResult8);
    }

    @Test
    void toHoursTest1() {
        float result = ChargerDivider.toHours(0, 3600000);
        float expresult = 1F;
        assertEquals(result, expresult);
    }

    @Test
    void toHoursTest2() {
        float result = ChargerDivider.toHours(0, 5400000);
        float expresult = 1.5F;
        assertEquals(result, expresult);
    }

    @Test
    void onlyHoursTest1() {
        int result = ChargerDivider.onlyhours(1.2F);
        int expresult = 1;
        assertEquals(result, expresult);
    }

    @Test
    void onlyHoursTest2() {
        int result = ChargerDivider.onlyhours(2.0F);
        int expresult = 2;
        assertEquals(result, expresult);
    }

    @Test
    void onlyMinutesTest1() {
        int result = ChargerDivider.onlyminutes(1.5F);
        int expresult = 30;
        assertEquals(result, expresult);
    }

    @Test
    void onlyMinutesTest2() {
        int result = ChargerDivider.onlyminutes(1.0F);
        int expresult = 0;
        assertEquals(result, expresult);
    }

    @Test
    void toHoursTest() {
        float result = ChargerDivider.toHours( 1000, 10000);

        assertNotEquals( result, (float) ((((1000 + 10000) /60) / 60) / 1000));
    }

    @Test
    void toHoursTest3() {
        float result = ChargerDivider.toHours( 1000, 10000);

        assertNotEquals( (float) ((((1000 + 10000) /60) / 60) / 1000), result );
    }

    @Test
    void toHoursTest4 () {
        float result = ChargerDivider.toHours( 0, 0);

        assertEquals( (float) ((((0 + 0) /60) / 60) / 1000), result );
    }

    @Test
    void toHoursTest5 () {
        float result = ChargerDivider.toHours( 10, 0);

        assertNotEquals( (float) ((((0 + 10) /60) / 60) / 1000), result );
    }

    @Test
    void toHoursTest6 () {
        float result = ChargerDivider.toHours( 10, 0);

        assertEquals( ((( (float)  (-10) /60) / 60) / 1000), result );
    }
}