package lapr.project.controller.task;

import lapr.project.data.PlanningDB;
import lapr.project.model.DeliveryPlanning;
import lapr.project.model.utils.Utils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DroneDeliveryPlanningTaskTest {

    @Test
    void planTrue() throws Exception {

        DroneDeliveryPlanningTask task = new DroneDeliveryPlanningTask();
        PlanningDB dbMock = mock(PlanningDB.class);
        when(dbMock.getAvailableDrone(any(Integer.class), any(Float.class))).thenReturn(1);
        when(dbMock.changeStates(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(true);
        when(dbMock.insertDelivery(any(Integer.class), any(Integer.class), any(Integer.class), any(Float.class), any(Float.class), any(Float.class))).thenReturn(1);
        when(dbMock.updateOrdersDeliveryID(new ArrayList<>(), 1)).thenReturn(true);
        when(dbMock.getActualBatteryVehicle(any(Integer.class), any(Integer.class))).thenReturn(500f);
        Map<String, Float> pesosPorEncomenda = new HashMap<>();
        pesosPorEncomenda.put("41.155035, -8.637854", 1f);
        pesosPorEncomenda.put("41.155273, -8.638757", 1f);
        pesosPorEncomenda.put("41.155964, -8.638500", 1f);
        pesosPorEncomenda.put("41.155766, -8.637508", 1f);
        pesosPorEncomenda.put("41.156360, -8.640266", 1f);

        LinkedList<String> intermedios = new LinkedList<>();

        intermedios.add("41.155035, -8.637854"); //3
        intermedios.add("41.155273, -8.638757"); //4
        intermedios.add("41.155964, -8.638500"); //5
        intermedios.add("41.155766, -8.637508"); //6
        intermedios.add("41.156360, -8.640266"); //10

        when(dbMock.getPesosPorEncomenda(new LinkedList<>())).thenReturn(pesosPorEncomenda);

        MockedStatic<DroneDeliveryPlanningTask> droneTask = mockStatic(DroneDeliveryPlanningTask.class);
        droneTask.when(() -> {
            DroneDeliveryPlanningTask.changeStateDrone(any(ChangeStatesTask.class));
        }).thenReturn(true);

        DeliveryPlanning plan = new DeliveryPlanning(dbMock);
        DeliveryPlanning.coordinatesClientsPath = "coordinates.txt";
        DeliveryPlanning.coordinatesPharmaciesPath = "";
        DeliveryPlanning.caminhosPathScooter = "conectionsScooter.txt";
        DeliveryPlanning.caminhosPathDrone = "conectionsDrone.txt";

        plan.start();

        boolean result = task.plan(new LinkedList<>(), 1, intermedios, "41.156178, -8.639322", 5f, dbMock, plan);
        droneTask.close();
        assertTrue(result);


    }

    @Test
    void plan2True() throws Exception {

        DroneDeliveryPlanningTask task = new DroneDeliveryPlanningTask();
        PlanningDB dbMock = mock(PlanningDB.class);
        when(dbMock.getAvailableDrone(any(Integer.class), any(Float.class))).thenReturn(1);
        when(dbMock.changeStates(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(true);
        when(dbMock.insertDelivery(any(Integer.class), any(Integer.class), any(Integer.class), any(Float.class), any(Float.class), any(Float.class))).thenReturn(1);
        when(dbMock.updateOrdersDeliveryID(new ArrayList<>(), 1)).thenReturn(true);
        when(dbMock.getActualBatteryVehicle(any(Integer.class), any(Integer.class))).thenReturn(100f);
        Map<Integer, String> cordsOfAllFarmacias = new HashMap<>();
        cordsOfAllFarmacias.put(1, "41.156178, -8.639322");
        when(dbMock.getCordsOfAllPharmacys()).thenReturn(cordsOfAllFarmacias);
        Map<String, Float> pesosPorEncomenda = new HashMap<>();
        pesosPorEncomenda.put("41.155035, -8.637854", 1f);
        pesosPorEncomenda.put("41.155273, -8.638757", 1f);
        pesosPorEncomenda.put("41.155964, -8.638500", 1f);
        pesosPorEncomenda.put("41.155766, -8.637508", 1f);
        pesosPorEncomenda.put("41.156360, -8.640266", 1f);

        LinkedList<String> intermedios = new LinkedList<>();

        intermedios.add("41.155035, -8.637854"); //3
        intermedios.add("41.155273, -8.638757"); //4
        intermedios.add("41.155964, -8.638500"); //5
        intermedios.add("41.155766, -8.637508"); //6
        intermedios.add("41.156360, -8.640266"); //10

        when(dbMock.getPesosPorEncomenda(new LinkedList<>())).thenReturn(pesosPorEncomenda);

        MockedStatic<DroneDeliveryPlanningTask> droneTask = mockStatic(DroneDeliveryPlanningTask.class);
        droneTask.when(() -> {
            DroneDeliveryPlanningTask.changeStateDrone(any(ChangeStatesTask.class));
        }).thenReturn(true);

        DeliveryPlanning plan = new DeliveryPlanning(dbMock);
        DeliveryPlanning.coordinatesClientsPath = "coordinates.txt";
        DeliveryPlanning.coordinatesPharmaciesPath = "";
        DeliveryPlanning.caminhosPathScooter = "conectionsScooter.txt";
        DeliveryPlanning.caminhosPathDrone = "conectionsDrone.txt";

        plan.start();


        boolean result = task.plan(new LinkedList<>(), 1, intermedios, "41.156178, -8.639322", 5f, dbMock, plan);
        droneTask.close();
        assertTrue(result);

    }

    @Test
    void planTrue3() throws Exception {

        DroneDeliveryPlanningTask task = new DroneDeliveryPlanningTask();
        PlanningDB dbMock = mock(PlanningDB.class);
        when(dbMock.getAvailableDrone(any(Integer.class), any(Float.class))).thenReturn(1);
        when(dbMock.changeStates(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(true);
        when(dbMock.insertDelivery(any(Integer.class), any(Integer.class), any(Integer.class), any(Float.class), any(Float.class), any(Float.class))).thenReturn(1);
        when(dbMock.updateOrdersDeliveryID(new ArrayList<>(), 1)).thenReturn(true);
        when(dbMock.getActualBatteryVehicle(any(Integer.class), any(Integer.class))).thenReturn(500f);
        Map<String, Float> pesosPorEncomenda = new HashMap<>();
        pesosPorEncomenda.put("41.155035, -8.637854", 1f);
        pesosPorEncomenda.put("41.155273, -8.638757", 1f);
        pesosPorEncomenda.put("41.155964, -8.638500", 1f);
        pesosPorEncomenda.put("41.155766, -8.637508", 1f);
        pesosPorEncomenda.put("41.156360, -8.640266", 1f);

        LinkedList<String> intermedios = new LinkedList<>();

        intermedios.add("41.155035, -8.637854"); //3
        intermedios.add("41.155273, -8.638757"); //4
        intermedios.add("41.155964, -8.638500"); //5
        intermedios.add("41.155766, -8.637508"); //6
        intermedios.add("41.156360, -8.640266"); //10

        when(dbMock.getPesosPorEncomenda(new LinkedList<>())).thenReturn(pesosPorEncomenda);

        MockedStatic<DroneDeliveryPlanningTask> droneTask = mockStatic(DroneDeliveryPlanningTask.class);
        droneTask.when(() -> {
            DroneDeliveryPlanningTask.changeStateDrone(any(ChangeStatesTask.class));
        }).thenReturn(false);

        DeliveryPlanning plan = new DeliveryPlanning(dbMock);
        DeliveryPlanning.coordinatesClientsPath = "coordinates.txt";
        DeliveryPlanning.coordinatesPharmaciesPath = "";
        DeliveryPlanning.caminhosPathScooter = "conectionsScooter.txt";
        DeliveryPlanning.caminhosPathDrone = "conectionsDrone.txt";

        plan.start();


        boolean result = task.plan(new LinkedList<>(), 1, intermedios, "41.156178, -8.639322", 5f, dbMock, plan);
        droneTask.close();
        assertFalse(result);


    }

    @Test
    void planTrue4() throws Exception {

        DroneDeliveryPlanningTask task = new DroneDeliveryPlanningTask();
        PlanningDB dbMock = mock(PlanningDB.class);
        when(dbMock.getAvailableDrone(any(Integer.class), any(Float.class))).thenReturn(1);
        when(dbMock.changeStates(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(true);
        when(dbMock.insertDelivery(any(Integer.class), any(Integer.class), any(Integer.class), any(Float.class), any(Float.class), any(Float.class))).thenReturn(0);
        when(dbMock.updateOrdersDeliveryID(new ArrayList<>(), 0)).thenReturn(true);
        when(dbMock.getActualBatteryVehicle(any(Integer.class), any(Integer.class))).thenReturn(500f);
        Map<String, Float> pesosPorEncomenda = new HashMap<>();
        pesosPorEncomenda.put("41.155035, -8.637854", 1f);
        pesosPorEncomenda.put("41.155273, -8.638757", 1f);
        pesosPorEncomenda.put("41.155964, -8.638500", 1f);
        pesosPorEncomenda.put("41.155766, -8.637508", 1f);
        pesosPorEncomenda.put("41.156360, -8.640266", 1f);

        LinkedList<String> intermedios = new LinkedList<>();

        intermedios.add("41.155035, -8.637854"); //3
        intermedios.add("41.155273, -8.638757"); //4
        intermedios.add("41.155964, -8.638500"); //5
        intermedios.add("41.155766, -8.637508"); //6
        intermedios.add("41.156360, -8.640266"); //10

        when(dbMock.getPesosPorEncomenda(new LinkedList<>())).thenReturn(pesosPorEncomenda);

        MockedStatic<DroneDeliveryPlanningTask> droneTask = mockStatic(DroneDeliveryPlanningTask.class);
        droneTask.when(() -> {
            DroneDeliveryPlanningTask.changeStateDrone(any(ChangeStatesTask.class));
        }).thenReturn(false);

        DeliveryPlanning plan = new DeliveryPlanning(dbMock);
        DeliveryPlanning.coordinatesClientsPath = "coordinates.txt";
        DeliveryPlanning.coordinatesPharmaciesPath = "";
        DeliveryPlanning.caminhosPathScooter = "conectionsScooter.txt";
        DeliveryPlanning.caminhosPathDrone = "conectionsDrone.txt";

        plan.start();


        boolean result = task.plan(new LinkedList<>(), 1, intermedios, "41.156178, -8.639322", 5f, dbMock, plan);
        droneTask.close();
        assertFalse(result);


    }

    @Test
    void createTimerToChangeStateDronTrue() {
        ChangeStatesTask dbMock = mock(ChangeStatesTask.class);
        when(dbMock.run()).thenReturn(true);
        boolean result = DroneDeliveryPlanningTask.changeStateDrone(dbMock);
        assertTrue(result);
    }

    @Test
    void createTimerToChangeStateDroneFalse() {
        ChangeStatesTask dbMock = mock(ChangeStatesTask.class);
        when(dbMock.run()).thenReturn(false);
        boolean result = DroneDeliveryPlanningTask.changeStateDrone(dbMock);
        assertFalse(result);
    }


    /*@Test
    void tratarRemainingPath() {

        PlanningDB dbMock = mock(PlanningDB.class);
        MockedStatic<DroneDeliveryPlanningTask> droneTask = mockStatic(DroneDeliveryPlanningTask.class);
        droneTask.when(() -> {
            DroneDeliveryPlanningTask.stopForCharging(Mockito.any());
        }).thenReturn(true);

        MockedStatic<DeliveryPlanning> planMock = mockStatic(DeliveryPlanning.class);
        droneTask.when(() -> {
            DeliveryPlanning.calculateEnergyCost(Mockito.any(), any(Float.class), Mockito.any(), Mockito.any());
        }).thenReturn(100f);
        droneTask.when(() -> {
            DeliveryPlanning.getSpentTimeLastDronePath();
        }).thenReturn(10f);


        DeliveryPlanning plan = mock(DeliveryPlanning.class);
        when(plan.shortestPathScooter(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(new LinkedList<>());
        when(plan.pathWeight(Mockito.any())).thenReturn(50f);


        LinkedList<String> remaining = new LinkedList<>();
        remaining.add("41.154389, -8.638143");
        remaining.add("41.155035, -8.637854");
        remaining.add("41.155766, -8.637508");
        remaining.add("41.155964, -8.638500");
        remaining.add("41.156360, -8.640266");
        remaining.add("41.155273, -8.638757");
        remaining.add("41.154389, -8.638143");

        Map<String, Float> pesosPorEncomenda = new HashMap<>();
        pesosPorEncomenda.put("41.155035, -8.637854", 1f);
        pesosPorEncomenda.put("41.155273, -8.638757", 1f);
        pesosPorEncomenda.put("41.155964, -8.638500", 1f);
        pesosPorEncomenda.put("41.155766, -8.637508", 1f);
        pesosPorEncomenda.put("41.156360, -8.640266", 1f);


        boolean result = DroneDeliveryPlanningTask.tratarRemainingPath(remaining, 1, 1, plan, pesosPorEncomenda, 1, dbMock, "teste");

        assertTrue(result);
        planMock.close();
        droneTask.close();


    }*/

    @Test
    void testNullDronePlan () throws Exception {

        DroneDeliveryPlanningTask planning = new DroneDeliveryPlanningTask();
        PlanningDB db = mock( PlanningDB.class );
        when( db.getAvailableDrone(-1, -1) ).thenReturn( null );

        assertThrows(
                Exception.class,
                () -> planning
                        .plan( null, -1, null, null, -1, db, null) );
    }
}