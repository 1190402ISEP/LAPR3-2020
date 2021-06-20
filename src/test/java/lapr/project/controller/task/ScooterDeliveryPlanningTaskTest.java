package lapr.project.controller.task;

import lapr.project.data.PlanningDB;
import lapr.project.model.DeliveryPlanning;
import lapr.project.model.OrdersQueue;
import lapr.project.model.VehicleType;
import lapr.project.model.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScooterDeliveryPlanningTaskTest {

    private static ScooterDeliveryPlanningTask controller;

    @BeforeEach
     void setUp() {
        controller = new ScooterDeliveryPlanningTask();
        Utils.setPath("");
    }
    @Test
    void runTesteFalso1() throws Exception {
        List<Integer> lista = new ArrayList<>();
        lista.add(1);
        Map<Integer,List<Integer>> map = new HashMap<>();
        map.put(1, lista);
        Map<Integer, String> allpharmacies = new HashMap<>();
        allpharmacies.put(1,"teste");
        Map<String, Float> pesosPorEcomenda = new HashMap<>();
        pesosPorEcomenda.put("testes", 7.0F);
        MockedStatic<OrdersQueue> ordersQueue = mockStatic(OrdersQueue.class);
        ordersQueue.when(OrdersQueue::getOrders).thenReturn(map);

        LinkedList<String> linkedlista = new LinkedList<>();
        linkedlista.add("cords");
        LinkedList<String> path = new LinkedList<>();
        path.add("cords1");
        path.add("cords2");
        PlanningDB db = mock(PlanningDB.class);
        DeliveryPlanning planMock = mock(DeliveryPlanning.class);
        when(db.getCordsByOrders(lista)).thenReturn(linkedlista);
        when(db.getCoordinatesByPharmacyID(1)).thenReturn("pharmacycords");
        when(db.getAvailableCourier(1)).thenReturn(123456789);
        when(db.getAvailableScooter(123456789, 1, 7.0F)).thenReturn(2);
        when(db.getActualBatteryVehicle(2,1)).thenReturn(100.0F);
        when(db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.SCOOTER)).thenReturn(allpharmacies);
        when(planMock.shortestPath(VehicleType.SCOOTER,new LinkedList<>(linkedlista), "pharmacycords", "pharmacycords")).thenReturn(path);
        when(db.getPesosPorEncomenda(lista)).thenReturn(pesosPorEcomenda);
        when(planMock.mostEficientPath(new LinkedList<>(linkedlista), "pharmacycords", VehicleType.SCOOTER, 100.0F, 7.0F, pesosPorEcomenda, allpharmacies, new LinkedList<>())).thenReturn(path);
        when(planMock.pathWeight(path)).thenReturn(2.0F);
        MockedStatic<DeliveryPlanning> deliveryplanning = mockStatic(DeliveryPlanning.class);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateSpentTime(2.0F, DeliveryPlanning.getVelocidadeScooter());}).thenReturn(10.0F);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateEnergyCost(path, 7.0F, VehicleType.SCOOTER, pesosPorEcomenda, new HashMap<>());}).thenReturn(100.0F);
        when(db.changeStates(2, 123456789, 1)).thenReturn(false);
        when(db.insertDelivery(123456789, 2, 1, 2.0F, 10.0F, 100.0F)).thenReturn(11);
        when(db.updateOrdersDeliveryID(lista, 11)).thenReturn(true);
        MockedStatic<ScooterDeliveryPlanningTask> scooterplan = mockStatic(ScooterDeliveryPlanningTask.class);
        scooterplan.when(() -> {ScooterDeliveryPlanningTask.changeStates(any(ChangeStatesTask.class));}).thenReturn(true);
        scooterplan.when(() -> {ScooterDeliveryPlanningTask.sendEmailInformingDelivery(Mockito.any(), Mockito.any());}).thenReturn(true);
        when(db.getClientEmailByOrder(1)).thenReturn("email");
        ordersQueue.when(OrdersQueue::clearQueue).thenReturn(false);


        deliveryplanning.when(() -> {DeliveryPlanning.getTotalPayload(pesosPorEcomenda);}).thenReturn(7f);

        controller = new ScooterDeliveryPlanningTask(db, planMock);
        controller.run(VehicleType.SCOOTER);

        assertFalse(controller.getIsTrue());
        ordersQueue.close();
        deliveryplanning.close();
        scooterplan.close();
    }

    @Test
    void runTesteFalso2() throws Exception {
        List<Integer> lista = new ArrayList<>();
        lista.add(1);
        Map<Integer,List<Integer>> map = new HashMap<>();
        map.put(1, lista);
        Map<Integer, String> allpharmacies = new HashMap<>();
        allpharmacies.put(1,"teste");
        Map<String, Float> pesosPorEcomenda = new HashMap<>();
        pesosPorEcomenda.put("testes", 7.0F);
        MockedStatic<OrdersQueue> ordersQueue = mockStatic(OrdersQueue.class);
        ordersQueue.when(OrdersQueue::getOrders).thenReturn(map);

        LinkedList<String> linkedlista = new LinkedList<>();
        linkedlista.add("cords");
        LinkedList<String> path = new LinkedList<>();
        path.add("cords1");
        path.add("cords2");
        PlanningDB db = mock(PlanningDB.class);
        DeliveryPlanning planMock = mock(DeliveryPlanning.class);
        when(db.getCordsByOrders(lista)).thenReturn(linkedlista);
        when(db.getCoordinatesByPharmacyID(1)).thenReturn("pharmacycords");
        when(db.getAvailableCourier(1)).thenReturn(123456789);
        when(db.getAvailableScooter(123456789, 1, 7.0F)).thenReturn(2);
        when(db.getActualBatteryVehicle(2,1)).thenReturn(100.0F);
        when(db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.SCOOTER)).thenReturn(allpharmacies);
        when(planMock.shortestPath(VehicleType.SCOOTER,new LinkedList<>(linkedlista), "pharmacycords", "pharmacycords")).thenReturn(path);
        when(db.getPesosPorEncomenda(lista)).thenReturn(pesosPorEcomenda);
        when(planMock.mostEficientPath(new LinkedList<>(linkedlista), "pharmacycords", VehicleType.SCOOTER, 100.0F, 7.0F, pesosPorEcomenda, allpharmacies, new LinkedList<>())).thenReturn(path);
        when(planMock.pathWeight(path)).thenReturn(2.0F);
        MockedStatic<DeliveryPlanning> deliveryplanning = mockStatic(DeliveryPlanning.class);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateSpentTime(2.0F, DeliveryPlanning.getVelocidadeScooter());}).thenReturn(10.0F);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateEnergyCost(path, 7.0F, VehicleType.SCOOTER, pesosPorEcomenda, new HashMap<>());}).thenReturn(100.0F);
        when(db.changeStates(2, 123456789, 1)).thenReturn(true);
        when(db.insertDelivery(123456789, 2, 1, 2.0F, 10.0F, 100.0F)).thenReturn(11);
        when(db.updateOrdersDeliveryID(lista, 11)).thenReturn(false);
        MockedStatic<ScooterDeliveryPlanningTask> scooterplan = mockStatic(ScooterDeliveryPlanningTask.class);
        scooterplan.when(() -> {ScooterDeliveryPlanningTask.changeStates(any(ChangeStatesTask.class));}).thenReturn(true);
        scooterplan.when(() -> {ScooterDeliveryPlanningTask.sendEmailInformingDelivery(Mockito.any(), Mockito.any());}).thenReturn(true);

        when(db.getClientEmailByOrder(1)).thenReturn("email");
        ordersQueue.when(OrdersQueue::clearQueue).thenReturn(false);

        deliveryplanning.when(() -> {DeliveryPlanning.getTotalPayload(pesosPorEcomenda);}).thenReturn(7f);

        controller = new ScooterDeliveryPlanningTask(db, planMock);
        controller.run(VehicleType.SCOOTER);

        assertFalse(controller.getIsTrue());
        ordersQueue.close();
        deliveryplanning.close();
        scooterplan.close();
    }

    @Test
    void runTesteFalso3() throws Exception {
        List<Integer> lista = new ArrayList<>();
        lista.add(1);
        Map<Integer,List<Integer>> map = new HashMap<>();
        map.put(1, lista);
        Map<Integer, String> allpharmacies = new HashMap<>();
        allpharmacies.put(1,"teste");
        Map<String, Float> pesosPorEcomenda = new HashMap<>();
        pesosPorEcomenda.put("testes", 7.0F);
        MockedStatic<OrdersQueue> ordersQueue = mockStatic(OrdersQueue.class);
        ordersQueue.when(OrdersQueue::getOrders).thenReturn(map);

        LinkedList<String> linkedlista = new LinkedList<>();
        linkedlista.add("cords");
        LinkedList<String> path = new LinkedList<>();
        path.add("cords1");
        path.add("cords2");
        PlanningDB db = mock(PlanningDB.class);
        DeliveryPlanning planMock = mock(DeliveryPlanning.class);
        when(db.getCordsByOrders(lista)).thenReturn(linkedlista);
        when(db.getCoordinatesByPharmacyID(1)).thenReturn("pharmacycords");
        when(db.getAvailableCourier(1)).thenReturn(123456789);
        when(db.getAvailableScooter(123456789, 1, 7.0F)).thenReturn(2);
        when(db.getActualBatteryVehicle(2,1)).thenReturn(100.0F);
        when(db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.SCOOTER)).thenReturn(allpharmacies);
        when(planMock.shortestPath(VehicleType.SCOOTER,new LinkedList<>(linkedlista), "pharmacycords", "pharmacycords")).thenReturn(path);
        when(db.getPesosPorEncomenda(lista)).thenReturn(pesosPorEcomenda);
        when(planMock.mostEficientPath(new LinkedList<>(linkedlista), "pharmacycords", VehicleType.SCOOTER, 100.0F, 7.0F, pesosPorEcomenda, allpharmacies, new LinkedList<>())).thenReturn(path);
        when(planMock.pathWeight(path)).thenReturn(2.0F);
        MockedStatic<DeliveryPlanning> deliveryplanning = mockStatic(DeliveryPlanning.class);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateSpentTime(2.0F, DeliveryPlanning.getVelocidadeScooter());}).thenReturn(10.0F);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateEnergyCost(path, 7.0F, VehicleType.SCOOTER, pesosPorEcomenda, new HashMap<>());}).thenReturn(100.0F);
        when(db.changeStates(2, 123456789, 1)).thenReturn(true);
        when(db.insertDelivery(123456789, 2, 1, 2.0F, 10.0F, 100.0F)).thenReturn(11);
        when(db.updateOrdersDeliveryID(lista, 11)).thenReturn(true);
        MockedStatic<ScooterDeliveryPlanningTask> scooterplan = mockStatic(ScooterDeliveryPlanningTask.class);
        scooterplan.when(() -> {ScooterDeliveryPlanningTask.changeStates(any(ChangeStatesTask.class));}).thenReturn(false);
        scooterplan.when(() -> {ScooterDeliveryPlanningTask.sendEmailInformingDelivery(Mockito.any(), Mockito.any());}).thenReturn(true);
        when(db.getClientEmailByOrder(1)).thenReturn("email");
        ordersQueue.when(OrdersQueue::clearQueue).thenReturn(false);

        deliveryplanning.when(() -> {DeliveryPlanning.getTotalPayload(pesosPorEcomenda);}).thenReturn(7f);

        controller = new ScooterDeliveryPlanningTask(db, planMock);
        controller.run(VehicleType.SCOOTER);

        assertFalse(controller.getIsTrue());
        ordersQueue.close();
        deliveryplanning.close();
        scooterplan.close();
    }

    @Test
    void runTesteFalso4() throws Exception {
        List<Integer> lista = new ArrayList<>();
        lista.add(1);
        Map<Integer,List<Integer>> map = new HashMap<>();
        map.put(1, lista);
        Map<Integer, String> allpharmacies = new HashMap<>();
        allpharmacies.put(1,"teste");
        Map<String, Float> pesosPorEcomenda = new HashMap<>();
        pesosPorEcomenda.put("testes", 7.0F);
        MockedStatic<OrdersQueue> ordersQueue = mockStatic(OrdersQueue.class);
        ordersQueue.when(OrdersQueue::getOrders).thenReturn(map);

        LinkedList<String> linkedlista = new LinkedList<>();
        linkedlista.add("cords");
        LinkedList<String> path = new LinkedList<>();
        path.add("cords1");
        path.add("cords2");
        PlanningDB db = mock(PlanningDB.class);
        DeliveryPlanning planMock = mock(DeliveryPlanning.class);
        when(db.getCordsByOrders(lista)).thenReturn(linkedlista);
        when(db.getCoordinatesByPharmacyID(1)).thenReturn("pharmacycords");
        when(db.getAvailableCourier(1)).thenReturn(123456789);
        when(db.getAvailableScooter(123456789, 1, 7.0F)).thenReturn(2);
        when(db.getActualBatteryVehicle(2,1)).thenReturn(100.0F);
        when(db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.SCOOTER)).thenReturn(allpharmacies);
        when(planMock.shortestPath(VehicleType.SCOOTER,new LinkedList<>(linkedlista), "pharmacycords", "pharmacycords")).thenReturn(path);
        when(db.getPesosPorEncomenda(lista)).thenReturn(pesosPorEcomenda);
        when(planMock.mostEficientPath(new LinkedList<>(linkedlista), "pharmacycords", VehicleType.SCOOTER, 100.0F, 7.0F, pesosPorEcomenda, allpharmacies, new LinkedList<>())).thenReturn(path);
        when(planMock.pathWeight(path)).thenReturn(2.0F);
        MockedStatic<DeliveryPlanning> deliveryplanning = mockStatic(DeliveryPlanning.class);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateSpentTime(2.0F, DeliveryPlanning.getVelocidadeScooter());}).thenReturn(10.0F);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateEnergyCost(path, 7.0F, VehicleType.SCOOTER, pesosPorEcomenda, new HashMap<>());}).thenReturn(100.0F);
        when(db.changeStates(2, 123456789, 1)).thenReturn(false);
        when(db.insertDelivery(123456789, 2, 1, 2.0F, 10.0F, 100.0F)).thenReturn(11);
        when(db.updateOrdersDeliveryID(lista, 11)).thenReturn(false);
        MockedStatic<ScooterDeliveryPlanningTask> scooterplan = mockStatic(ScooterDeliveryPlanningTask.class);
        scooterplan.when(() -> {ScooterDeliveryPlanningTask.changeStates(any(ChangeStatesTask.class));}).thenReturn(true);
        scooterplan.when(() -> {ScooterDeliveryPlanningTask.sendEmailInformingDelivery(Mockito.any(), Mockito.any());}).thenReturn(true);
        when(db.getClientEmailByOrder(1)).thenReturn("email");
        ordersQueue.when(OrdersQueue::clearQueue).thenReturn(false);

        deliveryplanning.when(() -> {DeliveryPlanning.getTotalPayload(pesosPorEcomenda);}).thenReturn(7f);

        controller = new ScooterDeliveryPlanningTask(db, planMock);
        controller.run(VehicleType.SCOOTER);

        assertFalse(controller.getIsTrue());
        ordersQueue.close();
        deliveryplanning.close();
        scooterplan.close();
    }

    @Test
    void runTesteFalso5() throws Exception {
        List<Integer> lista = new ArrayList<>();
        lista.add(1);
        Map<Integer,List<Integer>> map = new HashMap<>();
        map.put(1, lista);
        Map<Integer, String> allpharmacies = new HashMap<>();
        allpharmacies.put(1,"teste");
        Map<String, Float> pesosPorEcomenda = new HashMap<>();
        pesosPorEcomenda.put("testes", 7.0F);
        MockedStatic<OrdersQueue> ordersQueue = mockStatic(OrdersQueue.class);
        ordersQueue.when(OrdersQueue::getOrders).thenReturn(map);

        LinkedList<String> linkedlista = new LinkedList<>();
        linkedlista.add("cords");
        LinkedList<String> path = new LinkedList<>();
        path.add("cords1");
        path.add("cords2");
        PlanningDB db = mock(PlanningDB.class);
        DeliveryPlanning planMock = mock(DeliveryPlanning.class);
        when(db.getCordsByOrders(lista)).thenReturn(linkedlista);
        when(db.getCoordinatesByPharmacyID(1)).thenReturn("pharmacycords");
        when(db.getAvailableCourier(1)).thenReturn(123456789);
        when(db.getAvailableScooter(123456789, 1, 7.0F)).thenReturn(2);
        when(db.getActualBatteryVehicle(2,1)).thenReturn(100.0F);
        when(db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.SCOOTER)).thenReturn(allpharmacies);
        when(planMock.shortestPath(VehicleType.SCOOTER,new LinkedList<>(linkedlista), "pharmacycords", "pharmacycords")).thenReturn(path);
        when(db.getPesosPorEncomenda(lista)).thenReturn(pesosPorEcomenda);
        when(planMock.mostEficientPath(new LinkedList<>(linkedlista), "pharmacycords", VehicleType.SCOOTER, 100.0F, 7.0F, pesosPorEcomenda, allpharmacies, new LinkedList<>())).thenReturn(path);
        when(planMock.pathWeight(path)).thenReturn(2.0F);
        MockedStatic<DeliveryPlanning> deliveryplanning = mockStatic(DeliveryPlanning.class);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateSpentTime(2.0F, DeliveryPlanning.getVelocidadeScooter());}).thenReturn(10.0F);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateEnergyCost(path, 7.0F, VehicleType.SCOOTER, pesosPorEcomenda, new HashMap<>());}).thenReturn(100.0F);
        when(db.changeStates(2, 123456789, 1)).thenReturn(true);
        when(db.insertDelivery(123456789, 2, 1, 2.0F, 10.0F, 100.0F)).thenReturn(11);
        when(db.updateOrdersDeliveryID(lista, 11)).thenReturn(false);
        MockedStatic<ScooterDeliveryPlanningTask> scooterplan = mockStatic(ScooterDeliveryPlanningTask.class);
        scooterplan.when(() -> {ScooterDeliveryPlanningTask.changeStates(any(ChangeStatesTask.class));}).thenReturn(false);
        scooterplan.when(() -> {ScooterDeliveryPlanningTask.sendEmailInformingDelivery(Mockito.any(), Mockito.any());}).thenReturn(true);
        when(db.getClientEmailByOrder(1)).thenReturn("email");
        ordersQueue.when(OrdersQueue::clearQueue).thenReturn(false);

        deliveryplanning.when(() -> {DeliveryPlanning.getTotalPayload(pesosPorEcomenda);}).thenReturn(7f);

        controller = new ScooterDeliveryPlanningTask(db, planMock);
        controller.run(VehicleType.SCOOTER);

        assertFalse(controller.getIsTrue());
        ordersQueue.close();
        deliveryplanning.close();
        scooterplan.close();
    }

    @Test
    void runTesteFalso6() throws Exception {
        List<Integer> lista = new ArrayList<>();
        lista.add(1);
        Map<Integer,List<Integer>> map = new HashMap<>();
        map.put(1, lista);
        Map<Integer, String> allpharmacies = new HashMap<>();
        allpharmacies.put(1,"teste");
        Map<String, Float> pesosPorEcomenda = new HashMap<>();
        pesosPorEcomenda.put("testes", 7.0F);
        MockedStatic<OrdersQueue> ordersQueue = mockStatic(OrdersQueue.class);
        ordersQueue.when(OrdersQueue::getOrders).thenReturn(map);

        LinkedList<String> linkedlista = new LinkedList<>();
        linkedlista.add("cords");
        LinkedList<String> path = new LinkedList<>();
        path.add("cords1");
        path.add("cords2");
        PlanningDB db = mock(PlanningDB.class);
        DeliveryPlanning planMock = mock(DeliveryPlanning.class);
        when(db.getCordsByOrders(lista)).thenReturn(linkedlista);
        when(db.getCoordinatesByPharmacyID(1)).thenReturn("pharmacycords");
        when(db.getAvailableCourier(1)).thenReturn(123456789);
        when(db.getAvailableScooter(123456789, 1, 7.0F)).thenReturn(2);
        when(db.getActualBatteryVehicle(2,1)).thenReturn(100.0F);
        when(db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.SCOOTER)).thenReturn(allpharmacies);
        when(planMock.shortestPath(VehicleType.SCOOTER,new LinkedList<>(linkedlista), "pharmacycords", "pharmacycords")).thenReturn(path);
        when(db.getPesosPorEncomenda(lista)).thenReturn(pesosPorEcomenda);
        when(planMock.mostEficientPath(new LinkedList<>(linkedlista), "pharmacycords", VehicleType.SCOOTER, 100.0F, 7.0F, pesosPorEcomenda, allpharmacies, new LinkedList<>())).thenReturn(path);
        when(planMock.pathWeight(path)).thenReturn(2.0F);
        MockedStatic<DeliveryPlanning> deliveryplanning = mockStatic(DeliveryPlanning.class);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateSpentTime(2.0F, DeliveryPlanning.getVelocidadeScooter());}).thenReturn(10.0F);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateEnergyCost(path, 7.0F, VehicleType.SCOOTER, pesosPorEcomenda, new HashMap<>());}).thenReturn(100.0F);
        when(db.changeStates(2, 123456789, 1)).thenReturn(false);
        when(db.insertDelivery(123456789, 2, 1, 2.0F, 10.0F, 100.0F)).thenReturn(11);
        when(db.updateOrdersDeliveryID(lista, 11)).thenReturn(true);
        MockedStatic<ScooterDeliveryPlanningTask> scooterplan = mockStatic(ScooterDeliveryPlanningTask.class);
        scooterplan.when(() -> {ScooterDeliveryPlanningTask.changeStates(any(ChangeStatesTask.class));}).thenReturn(false);
        scooterplan.when(() -> {ScooterDeliveryPlanningTask.sendEmailInformingDelivery(Mockito.any(), Mockito.any());}).thenReturn(true);
        when(db.getClientEmailByOrder(1)).thenReturn("email");
        ordersQueue.when(OrdersQueue::clearQueue).thenReturn(false);

        deliveryplanning.when(() -> {DeliveryPlanning.getTotalPayload(pesosPorEcomenda);}).thenReturn(7f);

        controller = new ScooterDeliveryPlanningTask(db, planMock);
        controller.run(VehicleType.SCOOTER);

        assertFalse(controller.getIsTrue());
        ordersQueue.close();
        deliveryplanning.close();
        scooterplan.close();
    }

    @Test
    void runTestVazio() throws Exception {
        List<Integer> lista = new ArrayList<>();
        Map<Integer,List<Integer>> map = new HashMap<>();
        MockedStatic<OrdersQueue> ordersQueue = mockStatic(OrdersQueue.class);
        ordersQueue.when(OrdersQueue::getOrders).thenReturn(map);
        ordersQueue.when(OrdersQueue::clearQueue).thenReturn(false);
        PlanningDB db = mock(PlanningDB.class);
        DeliveryPlanning planMock = mock(DeliveryPlanning.class);

        controller = new ScooterDeliveryPlanningTask(db, planMock);
        controller.run(VehicleType.SCOOTER);

        assertFalse(controller.getIsTrue());
        ordersQueue.close();
    }


    @Test
    void createTimeToChangeStateTest1() {
        ChangeStatesTask task = mock(ChangeStatesTask.class);
        when(task.run()).thenReturn(true);
        assertTrue(ScooterDeliveryPlanningTask.changeStates(task));
    }

    @Test
    void createTimeToChangeStateTest2() {
        ChangeStatesTask task = mock(ChangeStatesTask.class);
        when(task.run()).thenReturn(false);
        assertFalse(ScooterDeliveryPlanningTask.changeStates(task));
    }


    @Test
    void getIsTrue1() {
        System.out.println("getIsTrue Test1");
        controller.setIsTrue(true);
        boolean result = controller.getIsTrue();
        assertTrue(result);
    }

    @Test
    void getIsTrue2() {
        System.out.println("getIsTrue Test2");
        controller.setIsTrue(false);
        boolean result = controller.getIsTrue();
        assertFalse(result);
    }

    @Test
    void setIsTrue1() {
        System.out.println("setIsTrue Test1");

        boolean istrue = true;
        controller.setIsTrue(istrue);
        assertTrue(controller.getIsTrue());
    }

    @Test
    void setIsTrue2() {
        System.out.println("setIsTrue Test2");

        boolean istrue = false;
        controller.setIsTrue(istrue);
        assertFalse(controller.getIsTrue());
    }

}