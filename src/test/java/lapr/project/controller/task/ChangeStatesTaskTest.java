package lapr.project.controller.task;

import lapr.project.data.EmailHandler;
import lapr.project.data.PlanningDB;
import lapr.project.model.ChargerDivider;
import lapr.project.model.VehicleType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.*;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ChangeStatesTaskTest {

    @Test
    void runFalse1() {
        PlanningDB db = mock(PlanningDB.class);
        when(db.changeCourierToAvailable(123456789)).thenReturn(true);
        when(db.getAvailableChargingStation(1, VehicleType.SCOOTER)).thenReturn(0);
        ChangeStatesTask instance1 = new ChangeStatesTask(1, 123456789, db, 1);
        assertFalse(instance1.run());
    }

    @Test
    void runFalse2() {
        PlanningDB db = mock(PlanningDB.class);
        when(db.changeCourierToAvailable(123456789)).thenReturn(true);
        when(db.getAvailableChargingStation(1, VehicleType.DRONE)).thenReturn(0);
        ChangeStatesTask instance1 = new ChangeStatesTask(1, db, 1);
        assertFalse(instance1.run());
    }

    @Test
    void runTrue1() throws IOException {
        PlanningDB db = mock(PlanningDB.class);
        when(db.changeCourierToAvailable(any(Integer.class))).thenReturn(true);
        when(db.getAvailableChargingStation(1,VehicleType.DRONE)).thenReturn(1);
        ChangeStatesTask instance1 = new ChangeStatesTask(1, db, 1);
        ChangeStatesTask testspy = spy(instance1);
        Mockito.doReturn(1.0F).when(testspy).parkingAndChargingDrone100(1, 1);
        assertTrue(testspy.run());
    }

    @Test
    void runFalseDrone() throws IOException {
        PlanningDB db = mock(PlanningDB.class);
        when(db.changeCourierToAvailable(any(Integer.class))).thenReturn(true);
        when(db.getAvailableChargingStation(1,VehicleType.DRONE)).thenReturn(1);
        ChangeStatesTask instance1 = new ChangeStatesTask(1, db, 1);
        ChangeStatesTask testspy = spy(instance1);
        Mockito.doThrow(new IOException()).when(testspy).parkingAndChargingDrone100(1, 1);
        assertFalse(testspy.run());
    }

    @Test
    void runTrue2() throws IOException {
        PlanningDB db = mock(PlanningDB.class);
        when(db.changeCourierToAvailable(any(Integer.class))).thenReturn(true);
        when(db.getAvailableChargingStation(1,VehicleType.SCOOTER)).thenReturn(1);
        ChangeStatesTask instance1 = new ChangeStatesTask(1, 123456789, db, 1);
        ChangeStatesTask testspy = spy(instance1);
        Mockito.doReturn(1.0F).when(testspy).parkingAndChargingScooter100(1, 123456789, 1);
        assertTrue(testspy.run());
    }

    @Test
    void runFalseScooter() throws IOException {
        PlanningDB db = mock(PlanningDB.class);
        when(db.changeCourierToAvailable(any(Integer.class))).thenReturn(true);
        when(db.getAvailableChargingStation(1,VehicleType.SCOOTER)).thenReturn(1);
        ChangeStatesTask instance1 = new ChangeStatesTask(1, 123456789, db, 1);
        ChangeStatesTask testspy = spy(instance1);
        Mockito.doThrow(new IOException()).when(testspy).parkingAndChargingScooter100(1, 123456789, 1);
        assertFalse(testspy.run());
    }

    @Test
    void parkingAndChargingScooter1() throws IOException {
        String path = "CalculateTimeToFullyCharge/";
        PlanningDB db = mock(PlanningDB.class);
        MockedStatic<ChargerDivider> chscooters = mockStatic(ChargerDivider.class);
        chscooters.when(() -> { ChargerDivider.putMapndSend(any(Integer.class), any(Integer.class), any(String.class), any(Long.class), any(Long.class), any(EmailHandler.class));}).thenReturn(1);
        when(db.getBatteryByScooterID(1, 1)).thenReturn(0);
        when(db.getBatteryCapacityByScooterID(1, 1)).thenReturn(300);
        when(db.getVoltageByPharmacyID(1, VehicleType.SCOOTER)).thenReturn(240);
        when(db.getCourierEmailByCourierNif(123456789)).thenReturn("email");
        when(db.changeToCharging(1)).thenReturn(true);
        when(db.TrullyCharge(any(Integer.class),any(Integer.class), any(Integer.class))).thenReturn(true);
        ChangeStatesTask instance1 = new ChangeStatesTask(1, 123456789, db, 1);
        File estimativadata = new File(path + "estimativa_teste.data");
        File estimativaflag = new File(path + "estimativa_teste.flag");
        estimativadata.createNewFile();
        estimativaflag.createNewFile();
        PrintWriter writer = new PrintWriter(estimativadata);
        writer.printf("%s", "1.25");
        writer.close();

        assertEquals(instance1.parkingAndChargingScooter100(1, 123456789, 1), 1.25F);
        chscooters.close();

        File dir = new File(path);
        File[] foundFiles = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith("lock_");
            }
        });
            for (File file : foundFiles) {
                    file.delete();
            }

    }

    @Test
    void parkingAndChargingDrone1() throws IOException {
        PlanningDB db = mock(PlanningDB.class);
        MockedStatic<ChargerDivider> chscooters = mockStatic(ChargerDivider.class);
        chscooters.when(() -> { ChargerDivider.putMapndSend(any(Integer.class), any(Integer.class), any(String.class), any(Long.class), any(Long.class), any(EmailHandler.class));}).thenReturn(1);
        when(db.getBatteryByScooterID(1, 1)).thenReturn(0);
        when(db.getBatteryCapacityByScooterID(1, 1)).thenReturn(300);
        when(db.getVoltageByPharmacyID(1, VehicleType.DRONE)).thenReturn(240);
        when(db.changeToCharging(1)).thenReturn(true);
        when(db.TrullyCharge(any(Integer.class),any(Integer.class), any(Integer.class))).thenReturn(true);
        ChangeStatesTask instance1 = new ChangeStatesTask(1, db, 1);

        assertEquals(instance1.parkingAndChargingDrone100(1, 1), 1.2375F);
        chscooters.close();
    }

    @Test
    void CalculateMilli1() {
        ChangeStatesTask instance1 = new ChangeStatesTask(1, 123456789, new PlanningDB(), 1);
        Long expResult = 3600000L;
        Long result = instance1.calculateToMilli(1);
        assertEquals(result,expResult);
    }

    @Test
    void CalculateMilli2() {
        ChangeStatesTask instance1 = new ChangeStatesTask(1, 123456789, new PlanningDB(), 1);
        Long expResult = 7200000L;
        Long result = instance1.calculateToMilli(2);
        assertEquals(result,expResult);
    }

    @Test
    void CalculateMilli3() {
        ChangeStatesTask instance1 = new ChangeStatesTask(1, 123456789, new PlanningDB(), 1);
        Long expResult = 720000L;
        Long result = instance1.calculateToMilli(0.2F);
        assertEquals(result,expResult);
    }
}