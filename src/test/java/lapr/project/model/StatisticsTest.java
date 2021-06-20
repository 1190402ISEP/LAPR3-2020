package lapr.project.model;

import lapr.project.data.StatisticsDB;
import lapr.project.ui.Print;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    static Statistics instance;

    @BeforeAll
    static void setUp() {
        StatisticsDB db = mock(StatisticsDB.class);

        List<Delivery> list = new ArrayList<>();
        list.add(null);
        list.add(null);
        when(db.getAllDeliverys()).thenReturn(list);
        instance = new Statistics(db);
    }

    @Test
    void getAllDeliverys() {
        List<Delivery> result = new ArrayList<>();
        result.add(null);
        result.add(null);
        List<Delivery> exprresult = instance.getAllDeliverys();
        assertEquals(result, exprresult);
    }

    @Test
    void printStatistics1() {
        MockedStatic<Print> write = mockStatic(Print.class);
        List<Delivery> parse = new ArrayList<>();
        Delivery d1 = new Delivery( 1, 123456789, 2, 2, 2, 20, 50);
        Delivery d2 = new Delivery( 2, 123456789, 3, 3, 3, 30, 70);
        parse.add(d1);
        parse.add(d2);
        float[] exprresult = new float[16];
        exprresult[0] = 25.0F;
        exprresult[1] = Float.NaN;
        exprresult[2] = 2.5F;
        exprresult[3] = Float.NaN;
        exprresult[4] = 60.0F;
        exprresult[5] = Float.NaN;
        exprresult[6] = 50.0F;
        exprresult[7] = 0.0F;
        exprresult[8] = 5.0F;
        exprresult[9] = 0.0F;
        exprresult[10] = 120.0F;
        exprresult[11] = 0.0F;
        exprresult[12] = 24000.0F;
        exprresult[13] = Float.NaN;
        exprresult[14] = 0.006F;
        exprresult[15] = Float.NaN;
        write.when(() -> Print.formatPrintStatistics(any(float[].class))).thenReturn(true);
        float[] result = instance.printStatistics(parse);
        assertEquals(result[0], exprresult[0]);
        assertEquals(result[1], exprresult[1]);
        assertEquals(result[2], exprresult[2]);
        assertEquals(result[3], exprresult[3]);
        assertEquals(result[4], exprresult[4]);
        assertEquals(result[5], exprresult[5]);
        assertEquals(result[6], exprresult[6]);
        assertEquals(result[7], exprresult[7]);
        assertEquals(result[8], exprresult[8]);
        assertEquals(result[9], exprresult[9]);
        assertEquals(result[10], exprresult[10]);
        assertEquals(result[11], exprresult[11]);
        assertEquals(result[12], exprresult[12]);
        assertEquals(result[13], exprresult[13]);
        assertEquals(result[14], exprresult[14]);
        assertEquals(result[15], exprresult[15]);
        write.close();
    }

    @Test
    void printStatistics2() {
        MockedStatic<Print> write = mockStatic(Print.class);
        List<Delivery> parse = new ArrayList<>();
        Delivery d1 = new Delivery( 1, 0, 2, 2, 2, 20, 50);
        Delivery d2 = new Delivery( 2, 0, 3, 3, 3, 30, 70);
        parse.add(d1);
        parse.add(d2);
        float[] exprresult = new float[16];
        exprresult[0] = Float.NaN;
        exprresult[1] = 25.0F;
        exprresult[2] = Float.NaN;
        exprresult[3] = 2.5F;
        exprresult[4] = Float.NaN;
        exprresult[5] = 60.0F;
        exprresult[6] = 0.0F;
        exprresult[7] = 50.0F;
        exprresult[8] = 0.0F;
        exprresult[9] = 5.0F;
        exprresult[10] = 0.0F;
        exprresult[11] = 120.0F;
        exprresult[12] = Float.NaN;
        exprresult[13] = 24000.0F;
        exprresult[14] = Float.NaN;
        exprresult[15] = 0.006F;
        write.when(() -> Print.formatPrintStatistics(any(float[].class))).thenReturn(false);
        float[] result = instance.printStatistics(parse);
        assertEquals(result[0], exprresult[0]);
        assertEquals(result[1], exprresult[1]);
        assertEquals(result[2], exprresult[2]);
        assertEquals(result[3], exprresult[3]);
        assertEquals(result[4], exprresult[4]);
        assertEquals(result[5], exprresult[5]);
        assertEquals(result[6], exprresult[6]);
        assertEquals(result[7], exprresult[7]);
        assertEquals(result[8], exprresult[8]);
        assertEquals(result[9], exprresult[9]);
        assertEquals(result[10], exprresult[10]);
        assertEquals(result[11], exprresult[11]);
        assertEquals(result[12], exprresult[12]);
        assertEquals(result[13], exprresult[13]);
        assertEquals(result[14], exprresult[14]);
        assertEquals(result[15], exprresult[15]);
        write.close();
    }
}