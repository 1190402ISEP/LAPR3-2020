package lapr.project.controller.task;

import lapr.project.data.PlanningDB;
import lapr.project.model.DeliveryPlanning;
import lapr.project.model.VehicleType;
import lapr.project.ui.Print;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderBackPharmacyTaskTest {

    private static OrderBackPharmacyTask controller;

    @BeforeAll
    static void setUp() {
        controller = new OrderBackPharmacyTask(new PlanningDB(), new DeliveryPlanning());
    }

    @Test
    void getIsTrue1() {
        System.out.println("getIsTrue Test1");
        controller.setisTrue(true);
        boolean result = controller.getisTrue();
        assertTrue(result);
    }

    @Test
    void getIsTrue2() {
        System.out.println("getIsTrue Test2");
        controller.setisTrue(false);
        boolean result = controller.getisTrue();
        assertFalse(result);
    }

    @Test
    void setIsTrue1() {
        System.out.println("setIsTrue Test1");

        boolean istrue = true;
        controller.setisTrue(istrue);
        assertTrue(controller.getisTrue());
    }

    @Test
    void setIsTrue2() {
        System.out.println("setIsTrue Test2");

        boolean istrue = false;
        controller.setisTrue(istrue);
        assertFalse(controller.getisTrue());
    }

    @Test
    void runTest1() throws IOException {
        Map<String,Integer> productsnostock = new HashMap<>();
        productsnostock.put("code1", 2);
        Map<String, Float> pesosPorEncomenda = new HashMap<>();
        pesosPorEncomenda.put("ecomenda1", 2.0F);
        PlanningDB db = mock(PlanningDB.class);
        DeliveryPlanning planMock = mock(DeliveryPlanning.class);
        int pharmacyID = 1;
        int OrderId = 15;
        List<Integer> newlist = new ArrayList<>();
        newlist.add(OrderId);

        Map<Integer, String> example = new HashMap<>();
        example.put(1, "example");
        LinkedList<String> cords = new LinkedList<>();
        cords.add("pharmacy2cords");

        when(db.getCoordinatesByPharmacyID(pharmacyID)).thenReturn("pharmacy1cords");
        when(db.getTotalPayloadByPart(productsnostock, pharmacyID)).thenReturn(7.0F);
        when(db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.SCOOTER)).thenReturn(example);
        MockedStatic<DeliveryPlanning> deliveryplanning = mockStatic(DeliveryPlanning.class);
        deliveryplanning.when(() -> {DeliveryPlanning.getIdClosestByOrigDest("pharmacy1cords", example);}).thenReturn(2);
        when(db.getCoordinatesByPharmacyID(2)).thenReturn("pharmacy2cords");
        when(db.getAvailableCourier(pharmacyID)).thenReturn(123456789);
        when(db.getAvailableScooter(123456789, pharmacyID, 7.0F)).thenReturn(5);
        when(db.getActualBatteryVehicle(5, pharmacyID)).thenReturn(100.0F);
        when(db.getPesosPorEncomenda(newlist)).thenReturn(pesosPorEncomenda);
        when(planMock.mostEficientPath(new LinkedList<>(cords), "pharmacy1cords", VehicleType.SCOOTER, 100.0F, 7.0F, new HashMap<>(), example, new LinkedList<>())).thenReturn(cords);

        deliveryplanning.when(() -> {DeliveryPlanning.calculateEnergyCost(cords, 7.0F, VehicleType.SCOOTER, new HashMap<>(), new HashMap<>());}).thenReturn(100.0F);
        when(db.changeStates(5, 123456789, pharmacyID)).thenReturn(true);
        when(db.changeStock(productsnostock, 2)).thenReturn(true);
        when(db.takeVehicleEnergy(5, 100.0F, pharmacyID)).thenReturn(true);
        when(db.createPharmacyOrder(any(int.class), any(int.class))).thenReturn(true);

        MockedStatic<OrderBackPharmacyTask> orderback = mockStatic(OrderBackPharmacyTask.class);
        orderback.when(() -> {OrderBackPharmacyTask.changeStates(any(ChangeStatesTask.class));}).thenReturn(true);

        MockedStatic<Print> pp = mockStatic(Print.class);
        orderback.when(() -> {Print.sendOrderBackNotice(any(int.class), any(int.class), any(int.class), any(int.class), any(float.class), Mockito.any());}).thenReturn(true);



        controller = new OrderBackPharmacyTask(db, planMock);
        assertTrue(controller.run(productsnostock, pharmacyID, OrderId));
        assertTrue(controller.getisTrue());
        deliveryplanning.close();
        orderback.close();
        pp.close();
    }

    @Test
    void runTest2() throws IOException {
        Map<String,Integer> productsnostock = new HashMap<>();
        productsnostock.put("code1", 2);
        Map<String, Float> pesosPorEncomenda = new HashMap<>();
        pesosPorEncomenda.put("ecomenda1", 2.0F);
        PlanningDB db = mock(PlanningDB.class);
        DeliveryPlanning planMock = mock(DeliveryPlanning.class);
        int pharmacyID = 1;
        int OrderId = 15;
        List<Integer> newlist = new ArrayList<>();
        newlist.add(OrderId);

        Map<Integer, String> example = new HashMap<>();
        example.put(1, "example");
        LinkedList<String> cords = new LinkedList<>();
        cords.add("pharmacy2cords");

        when(db.getCoordinatesByPharmacyID(pharmacyID)).thenReturn("pharmacy1cords");
        when(db.getTotalPayloadByPart(productsnostock, pharmacyID)).thenReturn(7.0F);
        when(db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.SCOOTER)).thenReturn(example);
        MockedStatic<DeliveryPlanning> deliveryplanning = mockStatic(DeliveryPlanning.class);
        deliveryplanning.when(() -> {DeliveryPlanning.getIdClosestByOrigDest("pharmacy1cords", example);}).thenReturn(2);
        when(db.getCoordinatesByPharmacyID(2)).thenReturn("pharmacy2cords");
        when(db.getAvailableCourier(pharmacyID)).thenReturn(123456789);
        when(db.getAvailableScooter(123456789, pharmacyID, 7.0F)).thenReturn(5);
        when(db.getActualBatteryVehicle(5, pharmacyID)).thenReturn(100.0F);
        when(db.getPesosPorEncomenda(newlist)).thenReturn(pesosPorEncomenda);
        when(planMock.mostEficientPath(new LinkedList<>(cords), "pharmacy1cords", VehicleType.SCOOTER, 100.0F, 7.0F, new HashMap<>(), example, new LinkedList<>())).thenReturn(cords);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateEnergyCost(cords, 7.0F, VehicleType.SCOOTER, new HashMap<>(), new HashMap<>());}).thenReturn(100.0F);
        when(db.changeStates(5, 123456789, pharmacyID)).thenReturn(false);
        when(db.changeStock(productsnostock, 2)).thenReturn(true);
        when(db.takeVehicleEnergy(5, 100.0F, pharmacyID)).thenReturn(true);
        when(db.createPharmacyOrder(any(int.class), any(int.class))).thenReturn(true);

        MockedStatic<OrderBackPharmacyTask> orderback = mockStatic(OrderBackPharmacyTask.class);
        orderback.when(() -> {OrderBackPharmacyTask.changeStates(any(ChangeStatesTask.class));}).thenReturn(true);


        MockedStatic<Print> pp = mockStatic(Print.class);
        orderback.when(() -> {Print.sendOrderBackNotice(any(int.class), any(int.class), any(int.class), any(int.class), any(float.class), Mockito.any());}).thenReturn(true);



        controller = new OrderBackPharmacyTask(db, planMock);
        assertTrue(controller.run(productsnostock, pharmacyID, OrderId));
        assertFalse(controller.getisTrue());
        deliveryplanning.close();
        orderback.close();
        pp.close();
    }

    @Test
    void runTest3() throws IOException {
        Map<String,Integer> productsnostock = new HashMap<>();
        productsnostock.put("code1", 2);
        Map<String, Float> pesosPorEncomenda = new HashMap<>();
        pesosPorEncomenda.put("ecomenda1", 2.0F);
        PlanningDB db = mock(PlanningDB.class);
        DeliveryPlanning planMock = mock(DeliveryPlanning.class);
        int pharmacyID = 1;
        int OrderId = 15;
        List<Integer> newlist = new ArrayList<>();
        newlist.add(OrderId);

        Map<Integer, String> example = new HashMap<>();
        example.put(1, "example");
        LinkedList<String> cords = new LinkedList<>();
        cords.add("pharmacy2cords");

        when(db.getCoordinatesByPharmacyID(pharmacyID)).thenReturn("pharmacy1cords");
        when(db.getTotalPayloadByPart(productsnostock, pharmacyID)).thenReturn(7.0F);
        when(db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.SCOOTER)).thenReturn(example);
        MockedStatic<DeliveryPlanning> deliveryplanning = mockStatic(DeliveryPlanning.class);
        deliveryplanning.when(() -> {DeliveryPlanning.getIdClosestByOrigDest("pharmacy1cords", example);}).thenReturn(2);
        when(db.getCoordinatesByPharmacyID(2)).thenReturn("pharmacy2cords");
        when(db.getAvailableCourier(pharmacyID)).thenReturn(123456789);
        when(db.getAvailableScooter(123456789, pharmacyID, 7.0F)).thenReturn(5);
        when(db.getActualBatteryVehicle(5, pharmacyID)).thenReturn(100.0F);
        when(db.getPesosPorEncomenda(newlist)).thenReturn(pesosPorEncomenda);
        when(planMock.mostEficientPath(new LinkedList<>(cords), "pharmacy1cords", VehicleType.SCOOTER, 100.0F, 7.0F, new HashMap<>(), example, new LinkedList<>())).thenReturn(cords);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateEnergyCost(cords, 7.0F, VehicleType.SCOOTER, new HashMap<>(), new HashMap<>());}).thenReturn(100.0F);
        when(db.changeStates(5, 123456789, pharmacyID)).thenReturn(true);
        when(db.changeStock(productsnostock, 2)).thenReturn(false);
        when(db.takeVehicleEnergy(5, 100.0F, pharmacyID)).thenReturn(true);
        when(db.createPharmacyOrder(any(int.class), any(int.class))).thenReturn(true);

        MockedStatic<OrderBackPharmacyTask> orderback = mockStatic(OrderBackPharmacyTask.class);
        orderback.when(() -> {OrderBackPharmacyTask.changeStates(any(ChangeStatesTask.class));}).thenReturn(true);


        MockedStatic<Print> pp = mockStatic(Print.class);
        orderback.when(() -> {Print.sendOrderBackNotice(any(int.class), any(int.class), any(int.class), any(int.class), any(float.class), Mockito.any());}).thenReturn(true);



        controller = new OrderBackPharmacyTask(db, planMock);
        assertTrue(controller.run(productsnostock, pharmacyID, OrderId));
        assertFalse(controller.getisTrue());
        deliveryplanning.close();
        orderback.close();
        pp.close();
    }

    @Test
    void runTest4() throws IOException {
        Map<String,Integer> productsnostock = new HashMap<>();
        productsnostock.put("code1", 2);
        Map<String, Float> pesosPorEncomenda = new HashMap<>();
        pesosPorEncomenda.put("ecomenda1", 2.0F);
        PlanningDB db = mock(PlanningDB.class);
        DeliveryPlanning planMock = mock(DeliveryPlanning.class);
        int pharmacyID = 1;
        int OrderId = 15;
        List<Integer> newlist = new ArrayList<>();
        newlist.add(OrderId);

        Map<Integer, String> example = new HashMap<>();
        example.put(1, "example");
        LinkedList<String> cords = new LinkedList<>();
        cords.add("pharmacy2cords");

        when(db.getCoordinatesByPharmacyID(pharmacyID)).thenReturn("pharmacy1cords");
        when(db.getTotalPayloadByPart(productsnostock, pharmacyID)).thenReturn(7.0F);
        when(db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.SCOOTER)).thenReturn(example);
        MockedStatic<DeliveryPlanning> deliveryplanning = mockStatic(DeliveryPlanning.class);
        deliveryplanning.when(() -> {DeliveryPlanning.getIdClosestByOrigDest("pharmacy1cords", example);}).thenReturn(2);
        when(db.getCoordinatesByPharmacyID(2)).thenReturn("pharmacy2cords");
        when(db.getAvailableCourier(pharmacyID)).thenReturn(123456789);
        when(db.getAvailableScooter(123456789, pharmacyID, 7.0F)).thenReturn(5);
        when(db.getActualBatteryVehicle(5, pharmacyID)).thenReturn(100.0F);
        when(db.getPesosPorEncomenda(newlist)).thenReturn(pesosPorEncomenda);
        when(planMock.mostEficientPath(new LinkedList<>(cords), "pharmacy1cords", VehicleType.SCOOTER, 100.0F, 7.0F, new HashMap<>(), example, new LinkedList<>())).thenReturn(cords);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateEnergyCost(cords, 7.0F, VehicleType.SCOOTER, new HashMap<>(), new HashMap<>());}).thenReturn(100.0F);
        when(db.changeStates(5, 123456789, pharmacyID)).thenReturn(true);
        when(db.changeStock(productsnostock, 2)).thenReturn(true);
        when(db.takeVehicleEnergy(5, 100.0F, pharmacyID)).thenReturn(false);
        when(db.createPharmacyOrder(any(int.class), any(int.class))).thenReturn(true);

        MockedStatic<OrderBackPharmacyTask> orderback = mockStatic(OrderBackPharmacyTask.class);
        orderback.when(() -> {OrderBackPharmacyTask.changeStates(any(ChangeStatesTask.class));}).thenReturn(true);


        MockedStatic<Print> pp = mockStatic(Print.class);
        orderback.when(() -> {Print.sendOrderBackNotice(any(int.class), any(int.class), any(int.class), any(int.class), any(float.class), Mockito.any());}).thenReturn(true);



        controller = new OrderBackPharmacyTask(db, planMock);
        assertTrue(controller.run(productsnostock, pharmacyID, OrderId));
        assertFalse(controller.getisTrue());
        deliveryplanning.close();
        orderback.close();
        pp.close();
    }

    @Test
    void runTest5() throws IOException {
        Map<String,Integer> productsnostock = new HashMap<>();
        productsnostock.put("code1", 2);
        Map<String, Float> pesosPorEncomenda = new HashMap<>();
        pesosPorEncomenda.put("ecomenda1", 2.0F);
        PlanningDB db = mock(PlanningDB.class);
        DeliveryPlanning planMock = mock(DeliveryPlanning.class);
        int pharmacyID = 1;
        int OrderId = 15;
        List<Integer> newlist = new ArrayList<>();
        newlist.add(OrderId);

        Map<Integer, String> example = new HashMap<>();
        example.put(1, "example");
        LinkedList<String> cords = new LinkedList<>();
        cords.add("pharmacy2cords");

        when(db.getCoordinatesByPharmacyID(pharmacyID)).thenReturn("pharmacy1cords");
        when(db.getTotalPayloadByPart(productsnostock, pharmacyID)).thenReturn(7.0F);
        when(db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.SCOOTER)).thenReturn(example);
        MockedStatic<DeliveryPlanning> deliveryplanning = mockStatic(DeliveryPlanning.class);
        deliveryplanning.when(() -> {DeliveryPlanning.getIdClosestByOrigDest("pharmacy1cords", example);}).thenReturn(2);
        when(db.getCoordinatesByPharmacyID(2)).thenReturn("pharmacy2cords");
        when(db.getAvailableCourier(pharmacyID)).thenReturn(123456789);
        when(db.getAvailableScooter(123456789, pharmacyID, 7.0F)).thenReturn(5);
        when(db.getActualBatteryVehicle(5, pharmacyID)).thenReturn(100.0F);
        when(db.getPesosPorEncomenda(newlist)).thenReturn(pesosPorEncomenda);
        when(planMock.mostEficientPath(new LinkedList<>(cords), "pharmacy1cords", VehicleType.SCOOTER, 100.0F, 7.0F, new HashMap<>(), example, new LinkedList<>())).thenReturn(cords);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateEnergyCost(cords, 7.0F, VehicleType.SCOOTER, new HashMap<>(), new HashMap<>());}).thenReturn(100.0F);
        when(db.changeStates(5, 123456789, pharmacyID)).thenReturn(false);
        when(db.changeStock(productsnostock, 2)).thenReturn(false);
        when(db.takeVehicleEnergy(5, 100.0F, pharmacyID)).thenReturn(true);
        when(db.createPharmacyOrder(any(int.class), any(int.class))).thenReturn(true);

        MockedStatic<OrderBackPharmacyTask> orderback = mockStatic(OrderBackPharmacyTask.class);
        orderback.when(() -> {OrderBackPharmacyTask.changeStates(any(ChangeStatesTask.class));}).thenReturn(true);



        MockedStatic<Print> pp = mockStatic(Print.class);
        orderback.when(() -> {Print.sendOrderBackNotice(any(int.class), any(int.class), any(int.class), any(int.class), any(float.class), Mockito.any());}).thenReturn(true);



        controller = new OrderBackPharmacyTask(db, planMock);
        assertTrue(controller.run(productsnostock, pharmacyID, OrderId));
        assertFalse(controller.getisTrue());
        deliveryplanning.close();
        orderback.close();
        pp.close();
    }

    @Test
    void runTest6() throws IOException {
        Map<String,Integer> productsnostock = new HashMap<>();
        productsnostock.put("code1", 2);
        Map<String, Float> pesosPorEncomenda = new HashMap<>();
        pesosPorEncomenda.put("ecomenda1", 2.0F);
        PlanningDB db = mock(PlanningDB.class);
        DeliveryPlanning planMock = mock(DeliveryPlanning.class);
        int pharmacyID = 1;
        int OrderId = 15;
        List<Integer> newlist = new ArrayList<>();
        newlist.add(OrderId);

        Map<Integer, String> example = new HashMap<>();
        example.put(1, "example");
        LinkedList<String> cords = new LinkedList<>();
        cords.add("pharmacy2cords");

        when(db.getCoordinatesByPharmacyID(pharmacyID)).thenReturn("pharmacy1cords");
        when(db.getTotalPayloadByPart(productsnostock, pharmacyID)).thenReturn(7.0F);
        when(db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.SCOOTER)).thenReturn(example);
        MockedStatic<DeliveryPlanning> deliveryplanning = mockStatic(DeliveryPlanning.class);
        deliveryplanning.when(() -> {DeliveryPlanning.getIdClosestByOrigDest("pharmacy1cords", example);}).thenReturn(2);
        when(db.getCoordinatesByPharmacyID(2)).thenReturn("pharmacy2cords");
        when(db.getAvailableCourier(pharmacyID)).thenReturn(123456789);
        when(db.getAvailableScooter(123456789, pharmacyID, 7.0F)).thenReturn(5);
        when(db.getActualBatteryVehicle(5, pharmacyID)).thenReturn(100.0F);
        when(db.getPesosPorEncomenda(newlist)).thenReturn(pesosPorEncomenda);
        when(planMock.mostEficientPath(new LinkedList<>(cords), "pharmacy1cords", VehicleType.SCOOTER, 100.0F, 7.0F, new HashMap<>(), example, new LinkedList<>())).thenReturn(cords);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateEnergyCost(cords, 7.0F, VehicleType.SCOOTER, new HashMap<>(), new HashMap<>());}).thenReturn(100.0F);
        when(db.changeStates(5, 123456789, pharmacyID)).thenReturn(true);
        when(db.changeStock(productsnostock, 2)).thenReturn(false);
        when(db.takeVehicleEnergy(5, 100.0F, pharmacyID)).thenReturn(false);
        when(db.createPharmacyOrder(any(int.class), any(int.class))).thenReturn(true);

        MockedStatic<OrderBackPharmacyTask> orderback = mockStatic(OrderBackPharmacyTask.class);
        orderback.when(() -> {OrderBackPharmacyTask.changeStates(any(ChangeStatesTask.class));}).thenReturn(true);



        MockedStatic<Print> pp = mockStatic(Print.class);
        orderback.when(() -> {Print.sendOrderBackNotice(any(int.class), any(int.class), any(int.class), any(int.class), any(float.class), Mockito.any());}).thenReturn(true);



        controller = new OrderBackPharmacyTask(db, planMock);
        assertTrue(controller.run(productsnostock, pharmacyID, OrderId));
        assertFalse(controller.getisTrue());
        deliveryplanning.close();
        orderback.close();
        pp.close();
    }

    @Test
    void runTest7() throws IOException {
        Map<String,Integer> productsnostock = new HashMap<>();
        productsnostock.put("code1", 2);
        Map<String, Float> pesosPorEncomenda = new HashMap<>();
        pesosPorEncomenda.put("ecomenda1", 2.0F);
        PlanningDB db = mock(PlanningDB.class);
        DeliveryPlanning planMock = mock(DeliveryPlanning.class);
        int pharmacyID = 1;
        int OrderId = 15;
        List<Integer> newlist = new ArrayList<>();
        newlist.add(OrderId);

        Map<Integer, String> example = new HashMap<>();
        example.put(1, "example");
        LinkedList<String> cords = new LinkedList<>();
        cords.add("pharmacy2cords");

        when(db.getCoordinatesByPharmacyID(pharmacyID)).thenReturn("pharmacy1cords");
        when(db.getTotalPayloadByPart(productsnostock, pharmacyID)).thenReturn(7.0F);
        when(db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.SCOOTER)).thenReturn(example);
        MockedStatic<DeliveryPlanning> deliveryplanning = mockStatic(DeliveryPlanning.class);
        deliveryplanning.when(() -> {DeliveryPlanning.getIdClosestByOrigDest("pharmacy1cords", example);}).thenReturn(2);
        when(db.getCoordinatesByPharmacyID(2)).thenReturn("pharmacy2cords");
        when(db.getAvailableCourier(pharmacyID)).thenReturn(123456789);
        when(db.getAvailableScooter(123456789, pharmacyID, 7.0F)).thenReturn(5);
        when(db.getActualBatteryVehicle(5, pharmacyID)).thenReturn(100.0F);
        when(db.getPesosPorEncomenda(newlist)).thenReturn(pesosPorEncomenda);
        when(planMock.mostEficientPath(new LinkedList<>(cords), "pharmacy1cords", VehicleType.SCOOTER, 100.0F, 7.0F, new HashMap<>(), example, new LinkedList<>())).thenReturn(cords);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateEnergyCost(cords, 7.0F, VehicleType.SCOOTER, new HashMap<>(), new HashMap<>());}).thenReturn(100.0F);
        when(db.changeStates(5, 123456789, pharmacyID)).thenReturn(false);
        when(db.changeStock(productsnostock, 2)).thenReturn(true);
        when(db.takeVehicleEnergy(5, 100.0F, pharmacyID)).thenReturn(false);
        when(db.createPharmacyOrder(any(int.class), any(int.class))).thenReturn(true);


        MockedStatic<OrderBackPharmacyTask> orderback = mockStatic(OrderBackPharmacyTask.class);
        orderback.when(() -> {OrderBackPharmacyTask.changeStates(any(ChangeStatesTask.class));}).thenReturn(true);


        MockedStatic<Print> pp = mockStatic(Print.class);
        orderback.when(() -> {Print.sendOrderBackNotice(any(int.class), any(int.class), any(int.class), any(int.class), any(float.class), Mockito.any());}).thenReturn(true);


        controller = new OrderBackPharmacyTask(db, planMock);
        assertTrue(controller.run(productsnostock, pharmacyID, OrderId));
        assertFalse(controller.getisTrue());
        deliveryplanning.close();
        orderback.close();
        pp.close();
    }

    @Test
    void runTest8() throws IOException {
        Map<String,Integer> productsnostock = new HashMap<>();
        productsnostock.put("code1", 2);
        Map<String, Float> pesosPorEncomenda = new HashMap<>();
        pesosPorEncomenda.put("ecomenda1", 2.0F);
        PlanningDB db = mock(PlanningDB.class);
        DeliveryPlanning planMock = mock(DeliveryPlanning.class);
        int pharmacyID = 1;
        int OrderId = 15;
        List<Integer> newlist = new ArrayList<>();
        newlist.add(OrderId);

        Map<Integer, String> example = new HashMap<>();
        example.put(1, "example");
        LinkedList<String> cords = new LinkedList<>();
        cords.add("pharmacy2cords");

        when(db.getCoordinatesByPharmacyID(pharmacyID)).thenReturn("pharmacy1cords");
        when(db.getTotalPayloadByPart(productsnostock, pharmacyID)).thenReturn(7.0F);
        when(db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.SCOOTER)).thenReturn(example);
        MockedStatic<DeliveryPlanning> deliveryplanning = mockStatic(DeliveryPlanning.class);
        deliveryplanning.when(() -> {DeliveryPlanning.getIdClosestByOrigDest("pharmacy1cords", example);}).thenReturn(2);
        when(db.getCoordinatesByPharmacyID(2)).thenReturn("pharmacy2cords");
        when(db.getAvailableCourier(pharmacyID)).thenReturn(123456789);
        when(db.getAvailableScooter(123456789, pharmacyID, 7.0F)).thenReturn(5);
        when(db.getActualBatteryVehicle(5, pharmacyID)).thenReturn(100.0F);
        when(db.getPesosPorEncomenda(newlist)).thenReturn(pesosPorEncomenda);
        when(planMock.mostEficientPath(new LinkedList<>(cords), "pharmacy1cords", VehicleType.SCOOTER, 100.0F, 7.0F, new HashMap<>(), example, new LinkedList<>())).thenReturn(cords);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateEnergyCost(cords, 7.0F, VehicleType.SCOOTER, new HashMap<>(), new HashMap<>());}).thenReturn(100.0F);
        when(db.changeStates(5, 123456789, pharmacyID)).thenReturn(false);
        when(db.changeStock(productsnostock, 2)).thenReturn(false);
        when(db.takeVehicleEnergy(5, 100.0F, pharmacyID)).thenReturn(false);
        when(db.createPharmacyOrder(any(int.class), any(int.class))).thenReturn(true);

        MockedStatic<OrderBackPharmacyTask> orderback = mockStatic(OrderBackPharmacyTask.class);
        orderback.when(() -> {OrderBackPharmacyTask.changeStates(any(ChangeStatesTask.class));}).thenReturn(true);


        MockedStatic<Print> pp = mockStatic(Print.class);
        orderback.when(() -> {Print.sendOrderBackNotice(any(int.class), any(int.class), any(int.class), any(int.class), any(float.class), Mockito.any());}).thenReturn(true);



        controller = new OrderBackPharmacyTask(db, planMock);
        assertTrue(controller.run(productsnostock, pharmacyID, OrderId));
        assertFalse(controller.getisTrue());
        deliveryplanning.close();
        orderback.close();
        pp.close();
    }

    @Test
    void runTest9() throws IOException {
        Map<String,Integer> productsnostock = new HashMap<>();
        productsnostock.put("code1", 2);
        Map<String, Float> pesosPorEncomenda = new HashMap<>();
        pesosPorEncomenda.put("ecomenda1", 2.0F);
        PlanningDB db = mock(PlanningDB.class);
        DeliveryPlanning planMock = mock(DeliveryPlanning.class);
        int pharmacyID = 1;
        int OrderId = 15;
        List<Integer> newlist = new ArrayList<>();
        newlist.add(OrderId);

        Map<Integer, String> example = new HashMap<>();
        example.put(1, "example");
        LinkedList<String> cords = new LinkedList<>();
        cords.add("pharmacy2cords");

        when(db.getCoordinatesByPharmacyID(pharmacyID)).thenReturn("pharmacy1cords");
        when(db.getTotalPayloadByPart(productsnostock, pharmacyID)).thenReturn(7.0F);
        when(db.getCordsOfPharmacysWithAvailableChargingStation(VehicleType.SCOOTER)).thenReturn(example);
        MockedStatic<DeliveryPlanning> deliveryplanning = mockStatic(DeliveryPlanning.class);
        deliveryplanning.when(() -> {DeliveryPlanning.getIdClosestByOrigDest("pharmacy1cords", example);}).thenReturn(2);
        when(db.getCoordinatesByPharmacyID(2)).thenReturn("pharmacy2cords");
        when(db.getAvailableCourier(pharmacyID)).thenReturn(123456789);
        when(db.getAvailableScooter(123456789, pharmacyID, 7.0F)).thenReturn(5);
        when(db.getActualBatteryVehicle(5, pharmacyID)).thenReturn(100.0F);
        when(db.getPesosPorEncomenda(newlist)).thenReturn(pesosPorEncomenda);
        when(planMock.mostEficientPath(new LinkedList<>(cords), "pharmacy1cords", VehicleType.SCOOTER, 100.0F, 7.0F, new HashMap<>(), example, new LinkedList<>())).thenReturn(cords);
        deliveryplanning.when(() -> {DeliveryPlanning.calculateEnergyCost(cords, 7.0F, VehicleType.SCOOTER, new HashMap<>(), new HashMap<>());}).thenReturn(100.0F);
        when(db.changeStates(5, 123456789, pharmacyID)).thenReturn(false);
        when(db.changeStock(productsnostock, 2)).thenReturn(false);
        when(db.takeVehicleEnergy(5, 100.0F, pharmacyID)).thenReturn(false);
        when(db.createPharmacyOrder(any(int.class), any(int.class))).thenReturn(true);

        MockedStatic<OrderBackPharmacyTask> orderback = mockStatic(OrderBackPharmacyTask.class);
        orderback.when(() -> {OrderBackPharmacyTask.changeStates(any(ChangeStatesTask.class));}).thenReturn(false);


        MockedStatic<Print> pp = mockStatic(Print.class);
        orderback.when(() -> {Print.sendOrderBackNotice(any(int.class), any(int.class), any(int.class), any(int.class), any(float.class), Mockito.any());}).thenReturn(true);



        controller = new OrderBackPharmacyTask(db, planMock);
        assertFalse(controller.run(productsnostock, pharmacyID, OrderId));
        assertFalse(controller.getisTrue());
        deliveryplanning.close();
        orderback.close();
        pp.close();
    }

    @Test
    void changeStates1() {
        ChangeStatesTask task = mock(ChangeStatesTask.class);
        when(task.run()).thenReturn(true);
        assertTrue(OrderBackPharmacyTask.changeStates(task));
    }

    @Test
    void changeStates2() {
        ChangeStatesTask task = mock(ChangeStatesTask.class);
        when(task.run()).thenReturn(false);
        assertFalse(OrderBackPharmacyTask.changeStates(task));
    }
}