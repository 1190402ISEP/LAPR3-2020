package lapr.project.model.utils;


import lapr.project.model.CreditCard;
import lapr.project.model.CourierState;
import lapr.project.model.VehicleUtils;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void getPath() {
        System.out.println("getPathTest");

        String before = Utils.getPath();
        Utils.setPath("TestScenario/");

        String expResult1 = "TestScenario/";
        String result = Utils.getPath();
        assertEquals(expResult1, result);
        Utils.setPath(before);
    }

    @Test
    void setPath() {
        System.out.println("getSetTest");

        String before = Utils.getPath();

        String expResult1 = "TestScenario/";
        Utils.setPath(expResult1);
        assertEquals(expResult1, Utils.getPath());
        Utils.setPath(before);
    }

    @Test
    void createDate() {
        System.out.println("createDateTest");
        Calendar c = Calendar.getInstance();
        c.set(2021, 1, 1);

        Date result = Utils.createDate("1-1-2021");
        assertTrue((c.getTime().getTime() - result.getTime()) < 1000);
    }

    @Test
    void getItemByOption() {
        List<CreditCard> list = new ArrayList<>();
        CreditCard test = new CreditCard("teste", 123, new Date());
        list.add(test);

        CreditCard result = Utils.getItemByOption(list, 1);

        assertEquals(test, result);
    }

    @Test
    void start() {
        Utils.start("ScenarioTEST.txt");
        assertEquals(Utils.getPath(), "TestScenario/AddProductToBasketScenario/");

    }

    @Test
    void start2() {

        Exception exception = assertThrows(NullPointerException.class, () -> {
            Utils.start("NAOHAFICHEIRO.txt");
        });


    }

    @Test
    public void testGetStateByID1() {
        int id = 1;
        CourierState result = Utils.getStateByID(id);
        assertEquals(CourierState.AVAILABLE, result);
    }
    
    @Test
    public void testGetStateByID2() {
        int id = 2;
        CourierState result = Utils.getStateByID(id);
        assertEquals(CourierState.UNAVAILABLE, result);
    }

    
    @Test
    public void testGetStateByID3() {
        int id = 3;
        CourierState result = Utils.getStateByID(id);
        assertNull(result);
    }

    @Test
    public void test(){
        Utils.listStates();
    }


    @Test
    void timeMinutesFormat() {

        float timeMinutes= 1.5F;
        String result = Utils.timeMinutesFormat(timeMinutes);
        assertEquals("01:30 min", result);
    }
}