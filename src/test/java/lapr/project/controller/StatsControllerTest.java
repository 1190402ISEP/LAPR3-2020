package lapr.project.controller;

import lapr.project.model.Delivery;
import lapr.project.model.Statistics;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatsControllerTest {

    @Test
    void showStats() {
        Statistics mm = mock(Statistics.class);
        List<Delivery> expresult = new ArrayList<>();
        Delivery d1 = new Delivery( 1, 123456789, 2, 2, 2, 20, 50);
        Delivery d2 = new Delivery( 2, 123456789, 3, 3, 3, 30, 70);
        expresult.add(d1);
        expresult.add(d2);
        when(mm.getAllDeliverys()).thenReturn(expresult);
        when(mm.printStatistics(expresult)).thenReturn(new float[16]);
        StatsController instance = new StatsController(mm);

        List<Delivery> result = instance.showStats();
        assertEquals(expresult, result);

    }
}