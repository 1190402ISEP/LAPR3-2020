package lapr.project.model;

import lapr.project.controller.task.ChangeStatesTask;
import lapr.project.controller.task.DroneDeliveryPlanningTask;
import lapr.project.controller.task.ScooterDeliveryPlanningTask;
import lapr.project.data.PlanningDB;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StopForChargingTest {

    @Test
    void runTrueScooter1() throws IOException {
        PlanningDB dbMock = mock(PlanningDB.class);
        when(dbMock.updateDelivery(any(Float.class), any(Float.class), any(Float.class), any(Integer.class))).thenReturn(true);
        when(dbMock.takeVehicleEnergy(any(Integer.class), any(Float.class), any(Integer.class))).thenReturn(true);
        when(dbMock.getAvailableChargingStation(1, VehicleType.SCOOTER)).thenReturn(1);
        when(dbMock.getActualBatteryVehicle(any(Integer.class), any(Integer.class))).thenReturn(6f);
        ChangeStatesTask tdk;
        MockedStatic<ScooterDeliveryPlanningTask> scooter = mockStatic(ScooterDeliveryPlanningTask.class);
        scooter.when(() -> ScooterDeliveryPlanningTask.changeStates(any(ChangeStatesTask.class))).thenReturn(true);
        tdk = mock(ChangeStatesTask.class);
        when(tdk.parkingAndChargingScooter(1,1, 1)).thenReturn(1.0F);

        StopForCharging stop = new StopForCharging(new LinkedList<>(), 1f, 1f, 1f, 1,1,1,1, dbMock);

        boolean result = stop.run(tdk);
        assertTrue(result);
        scooter.close();
    }

    @Test
    void runFalseScooter1() throws IOException {
        PlanningDB dbMock = mock(PlanningDB.class);
        when(dbMock.updateDelivery(any(Float.class), any(Float.class), any(Float.class), any(Integer.class))).thenReturn(true);
        when(dbMock.takeVehicleEnergy(any(Integer.class), any(Float.class), any(Integer.class))).thenReturn(true);
        when(dbMock.getAvailableChargingStation(1, VehicleType.SCOOTER)).thenReturn(1);
        when(dbMock.getActualBatteryVehicle(any(Integer.class), any(Integer.class))).thenReturn(6f);
        ChangeStatesTask tdk;
        MockedStatic<ScooterDeliveryPlanningTask> scooter = mockStatic(ScooterDeliveryPlanningTask.class);
        scooter.when(() -> ScooterDeliveryPlanningTask.changeStates(any(ChangeStatesTask.class))).thenReturn(true);
        tdk = mock(ChangeStatesTask.class);
        when(tdk.parkingAndChargingScooter(1,1, 2)).thenThrow(new IOException());

        StopForCharging stop = new StopForCharging(new LinkedList<>(), 1f, 1f, 1f, 1,1,1,1, dbMock);

        boolean result = stop.run(tdk);
        assertFalse(result);
        scooter.close();
    }

    @Test
    void runFalseScooter2() throws IOException {
        PlanningDB dbMock = mock(PlanningDB.class);
        when(dbMock.updateDelivery(any(Float.class), any(Float.class), any(Float.class), any(Integer.class))).thenReturn(true);
        when(dbMock.takeVehicleEnergy(any(Integer.class), any(Float.class), any(Integer.class))).thenReturn(true);
        when(dbMock.getAvailableChargingStation(1, VehicleType.SCOOTER)).thenReturn(0);
        when(dbMock.getActualBatteryVehicle(any(Integer.class), any(Integer.class))).thenReturn(6f);
        ChangeStatesTask tdk;
        MockedStatic<ScooterDeliveryPlanningTask> scooter = mockStatic(ScooterDeliveryPlanningTask.class);
        scooter.when(() -> ScooterDeliveryPlanningTask.changeStates(any(ChangeStatesTask.class))).thenReturn(true);
        tdk = mock(ChangeStatesTask.class);
        when(tdk.parkingAndChargingScooter(1,1, 1)).thenThrow(new IOException());

        StopForCharging stop = new StopForCharging(new LinkedList<>(), 1f, 1f, 1f, 1,1,1,1, dbMock);

        boolean result = stop.run(tdk);
        assertFalse(result);
        scooter.close();
    }

    @Test
    void runFalseScooter3() throws IOException {
        PlanningDB dbMock = mock(PlanningDB.class);
        when(dbMock.updateDelivery(any(Float.class), any(Float.class), any(Float.class), any(Integer.class))).thenReturn(true);
        when(dbMock.takeVehicleEnergy(any(Integer.class), any(Float.class), any(Integer.class))).thenReturn(true);
        when(dbMock.getAvailableChargingStation(1, VehicleType.SCOOTER)).thenReturn(0);
        when(dbMock.getActualBatteryVehicle(any(Integer.class), any(Integer.class))).thenReturn(6f);
        ChangeStatesTask tdk;
        MockedStatic<ScooterDeliveryPlanningTask> scooter = mockStatic(ScooterDeliveryPlanningTask.class);
        scooter.when(() -> ScooterDeliveryPlanningTask.changeStates(any(ChangeStatesTask.class))).thenReturn(false);
        tdk = mock(ChangeStatesTask.class);
        when(tdk.parkingAndChargingScooter(1,1, 1)).thenThrow(new IOException());

        StopForCharging stop = new StopForCharging(new LinkedList<>(), 1f, 1f, 1f, 1,1,1,1, dbMock);

        boolean result = stop.run(tdk);
        assertFalse(result);
        scooter.close();
    }

    @Test
    void runTrueDrone1() throws IOException {
        PlanningDB dbMock = mock(PlanningDB.class);
        when(dbMock.updateDelivery(any(Float.class), any(Float.class), any(Float.class), any(Integer.class))).thenReturn(true);
        when(dbMock.takeVehicleEnergy(any(Integer.class), any(Float.class), any(Integer.class))).thenReturn(true);
        when(dbMock.getAvailableChargingStation(1, VehicleType.DRONE)).thenReturn(1);
        when(dbMock.getActualBatteryVehicle(any(Integer.class), any(Integer.class))).thenReturn(6f);
        ChangeStatesTask tdk;
        MockedStatic<DroneDeliveryPlanningTask> scooter = mockStatic(DroneDeliveryPlanningTask.class);
        scooter.when(() -> DroneDeliveryPlanningTask.changeStateDrone(Mockito.any())).thenReturn(true);
        tdk = mock(ChangeStatesTask.class);
        when(tdk.parkingAndCharginDrones(1,1, 1)).thenReturn(1.0F);

        StopForCharging stop = new StopForCharging(new LinkedList<>(), 1f, 1f, 1f, 1,1,1, dbMock);

        boolean result = stop.run(tdk);
        assertTrue(result);
        scooter.close();
    }

    @Test
    void runFalseDrone1() throws IOException {
        PlanningDB dbMock = mock(PlanningDB.class);
        when(dbMock.updateDelivery(any(Float.class), any(Float.class), any(Float.class), any(Integer.class))).thenReturn(true);
        when(dbMock.takeVehicleEnergy(any(Integer.class), any(Float.class), any(Integer.class))).thenReturn(true);
        when(dbMock.getAvailableChargingStation(1, VehicleType.DRONE)).thenReturn(1);
        when(dbMock.getActualBatteryVehicle(any(Integer.class), any(Integer.class))).thenReturn(6f);
        ChangeStatesTask tdk;
        MockedStatic<DroneDeliveryPlanningTask> scooter = mockStatic(DroneDeliveryPlanningTask.class);
        scooter.when(() -> DroneDeliveryPlanningTask.changeStateDrone(any(ChangeStatesTask.class))).thenReturn(true);
        tdk = mock(ChangeStatesTask.class);
        when(tdk.parkingAndCharginDrones(1,1, 2)).thenThrow(new IOException());

        StopForCharging stop = new StopForCharging(new LinkedList<>(), 1f, 1f, 1f, 1,1,1, dbMock);

        boolean result = stop.run(tdk);
        assertFalse(result);
        scooter.close();
    }

    @Test
    void runFalseDrone2() throws IOException {
        PlanningDB dbMock = mock(PlanningDB.class);
        when(dbMock.updateDelivery(any(Float.class), any(Float.class), any(Float.class), any(Integer.class))).thenReturn(true);
        when(dbMock.takeVehicleEnergy(any(Integer.class), any(Float.class), any(Integer.class))).thenReturn(true);
        when(dbMock.getAvailableChargingStation(1, VehicleType.DRONE)).thenReturn(0);
        when(dbMock.getActualBatteryVehicle(any(Integer.class), any(Integer.class))).thenReturn(6f);
        ChangeStatesTask tdk;
        MockedStatic<DroneDeliveryPlanningTask> scooter = mockStatic(DroneDeliveryPlanningTask.class);
        scooter.when(() -> DroneDeliveryPlanningTask.changeStateDrone(any(ChangeStatesTask.class))).thenReturn(true);
        tdk = mock(ChangeStatesTask.class);
        when(tdk.parkingAndCharginDrones(1,1, 1)).thenThrow(new IOException());

        StopForCharging stop = new StopForCharging(new LinkedList<>(), 1f, 1f, 1f, 1,1,1, dbMock);

        boolean result = stop.run(tdk);
        assertFalse(result);
        scooter.close();
    }

    @Test
    void runFalseDrone3() throws IOException {
        PlanningDB dbMock = mock(PlanningDB.class);
        when(dbMock.updateDelivery(any(Float.class), any(Float.class), any(Float.class), any(Integer.class))).thenReturn(true);
        when(dbMock.takeVehicleEnergy(any(Integer.class), any(Float.class), any(Integer.class))).thenReturn(true);
        when(dbMock.getAvailableChargingStation(1, VehicleType.DRONE)).thenReturn(0);
        when(dbMock.getActualBatteryVehicle(any(Integer.class), any(Integer.class))).thenReturn(6f);
        ChangeStatesTask tdk ;
        MockedStatic<DroneDeliveryPlanningTask> scooter = mockStatic(DroneDeliveryPlanningTask.class);
        scooter.when(() -> DroneDeliveryPlanningTask.changeStateDrone(any(ChangeStatesTask.class))).thenReturn(false);
        tdk = mock(ChangeStatesTask.class);
        when(tdk.parkingAndCharginDrones(1,1, 1)).thenThrow(new IOException());

        StopForCharging stop = new StopForCharging(new LinkedList<>(), 1f, 1f, 1f, 1,1,1, dbMock);

        boolean result = stop.run(tdk);
        assertFalse(result);
        scooter.close();
    }

}