package lapr.project.model;


import lapr.project.data.PlanningDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeliveryPlanningTest {


    DeliveryPlanning plan;

    @BeforeEach
    void setUP() {

        PlanningDB mock = mock(PlanningDB.class);

        when(mock.initiatePoints(new HashMap<>())).thenReturn(true);

        plan = new DeliveryPlanning(mock);
        DeliveryPlanning.coordinatesClientsPath = "coordinates.txt";
        DeliveryPlanning.coordinatesPharmaciesPath = "";
        DeliveryPlanning.caminhosPathScooter = "conectionsScooter.txt";
        DeliveryPlanning.caminhosPathDrone = "conectionsDrone.txt";

        plan.start();

    }

    @Test
    void start() {

        Map<String, MapPoint> mapResult = DeliveryPlanning.getPoints();

        Map<String, MapPoint> expResult = new HashMap<>();

        expResult.put("41.154389, -8.638143", new MapPoint(41.154389f, -8.638143f, 100, "1"));
        expResult.put("41.154740, -8.639862", new MapPoint(41.154740f, -8.639862f, 120, "2"));
        expResult.put("41.155035, -8.637854", new MapPoint(41.155035f, -8.637854f, 120, "3"));
        expResult.put("41.155273, -8.638757", new MapPoint(41.155273f, -8.638757f, 120, "4"));
        expResult.put("41.155964, -8.638500", new MapPoint(41.155964f, -8.638500f, 120, "5"));
        expResult.put("41.155766, -8.637508", new MapPoint(41.155766f, -8.637508f, 120, "6"));
        expResult.put("41.155435, -8.639622", new MapPoint(41.155435f, -8.639622f, 120, "7"));
        expResult.put("41.155633, -8.640427", new MapPoint(41.155633f, -8.640427f, 120, "8"));
        expResult.put("41.154890, -8.640609", new MapPoint(41.154890f, -8.640609f, 120, "9"));
        expResult.put("41.156360, -8.640266", new MapPoint(41.156360f, -8.640266f, 120, "10"));
        expResult.put("41.156178, -8.639322", new MapPoint(41.156178f, -8.639322f, 120, "11"));

        assertEquals(expResult, mapResult);


    }

    @Test
    void shortestPath() {
        DeliveryPlanning d = new DeliveryPlanning();

        LinkedList<String> intermedios = new LinkedList<>();
        String farmaciaCords = "41.154389, -8.638143";

        intermedios.add("41.155035, -8.637854"); //3 1kg
        intermedios.add("41.155273, -8.638757"); //4 1kg
        intermedios.add("41.155964, -8.638500"); //5 1kg
        intermedios.add("41.155766, -8.637508"); //6 1kg
        intermedios.add("41.156360, -8.640266"); //10 1kg

        Map<Integer, String> allFarmacias = new HashMap<>();

        Map<String, Float> pesosPorEncomenda = new HashMap<>();

        pesosPorEncomenda.put(intermedios.get(0), 1f);
        pesosPorEncomenda.put(intermedios.get(1), 1f);
        pesosPorEncomenda.put(intermedios.get(2), 1f);
        pesosPorEncomenda.put(intermedios.get(3), 1f);
        pesosPorEncomenda.put(intermedios.get(4), 1f);

        allFarmacias.put(1, "41.154389, -8.638143");

        LinkedList<String> remaining = new LinkedList<>();

        LinkedList<String> path = (LinkedList<String>) d.mostEficientPath(intermedios, farmaciaCords, VehicleType.SCOOTER, 500f, 5, pesosPorEncomenda, allFarmacias, remaining);
        float distance = d.pathWeight(path);
        float energyCost = DeliveryPlanning.calculateEnergyCost(path, 5f, VehicleType.SCOOTER, pesosPorEncomenda, new HashMap<>());
        float spentTime = DeliveryPlanning.calculateSpentTime(distance, DeliveryPlanning.getVelocidadeScooter());

        LinkedList<String> exp = new LinkedList<>();

        float expDistance = 0.8f;
        float expEnergy = 118.15f;
        float expSpentTime = 0.0396f;
        exp.add("41.154389, -8.638143");
        exp.add("41.155035, -8.637854");
        exp.add("41.155273, -8.638757");
        exp.add("41.155964, -8.638500");
        exp.add("41.156178, -8.639322");
        exp.add("41.156360, -8.640266");
        exp.add("41.156178, -8.639322");
        exp.add("41.155964, -8.638500");
        exp.add("41.155766, -8.637508");
        exp.add("41.155035, -8.637854");
        exp.add("41.154389, -8.638143");


        assertEquals(exp, path);
        assertEquals(expDistance, distance, 0.1);
        assertEquals(expEnergy, energyCost, 0.1);
        assertEquals(expSpentTime, spentTime, 0.0001);

    }


    @Test
    void shortestPath2() {
        DeliveryPlanning d = new DeliveryPlanning();

        LinkedList<String> intermedios = new LinkedList<>();
        String farmaciaCords = "41.154389, -8.638143";

        intermedios.add("41.155035, -8.637854"); //3 1kg
        intermedios.add("41.155273, -8.638757"); //4 1kg
        intermedios.add("41.155964, -8.638500"); //5 1kg
        intermedios.add("41.155766, -8.637508"); //6 1kg
        intermedios.add("41.156360, -8.640266"); //10 1kg

        Map<Integer, String> allFarmacias = new HashMap<>();

        Map<String, Float> pesosPorEncomenda = new HashMap<>();

        pesosPorEncomenda.put(intermedios.get(0), 1f);
        pesosPorEncomenda.put(intermedios.get(1), 1f);
        pesosPorEncomenda.put(intermedios.get(2), 1f);
        pesosPorEncomenda.put(intermedios.get(3), 1f);
        pesosPorEncomenda.put(intermedios.get(4), 1f);

        allFarmacias.put(1, "41.154389, -8.638143");

        LinkedList<String> remaining = new LinkedList<>();

        LinkedList<String> path = (LinkedList<String>) d.mostEficientPath(intermedios, farmaciaCords, VehicleType.SCOOTER, 100f, 5, pesosPorEncomenda, allFarmacias, remaining);
        float distance = d.pathWeight(path);
        float energyCost = DeliveryPlanning.calculateEnergyCost(path, 5f, VehicleType.SCOOTER, pesosPorEncomenda, new HashMap<>());
        float spentTime = DeliveryPlanning.calculateSpentTime(distance, DeliveryPlanning.getVelocidadeScooter());

        LinkedList<String> exp = new LinkedList<>();

        float expDistance = 0.48f;
        float expEnergy = 66.25f;
        float expSpentTime = 0.0242f;
        exp.add("41.154389, -8.638143");
        exp.add("41.155035, -8.637854");
        exp.add("41.155273, -8.638757");
        exp.add("41.155964, -8.638500");
        exp.add("41.155766, -8.637508");
        exp.add("41.155035, -8.637854");
        exp.add("41.154389, -8.638143");

        LinkedList<String> remainingExp = new LinkedList<>();

        remainingExp.add("41.154389, -8.638143");
        remainingExp.add("41.156360, -8.640266");
        remainingExp.add("41.154389, -8.638143");


        assertEquals(remainingExp, remaining);
        assertEquals(exp, path);
        assertEquals(expDistance, distance, 0.1);
        assertEquals(expEnergy, energyCost, 0.1);
        assertEquals(expSpentTime, spentTime, 0.0001);

    }


    @Test
    void getIdClosestByOrigDest() {

        String cord1 = "41.154389, -8.638143";
        String cord2 = "41.154740, -8.639862";


        Map<Integer, String> map = new HashMap<>();

        map.put(2, cord1);

        int i = DeliveryPlanning.getIdClosestByOrigDest(cord2, map);
        assertEquals(2, i);

    }

    @Test
    void calculateSpentTime() {
        float result = DeliveryPlanning.calculateSpentTime(0f, DeliveryPlanning.getVelocidadeScooter());
        float exp = 0f;
        assertEquals(exp, result);
    }

    /*@Test
    void shortestPathDrone() {


        LinkedList<String> intermedios = new LinkedList<>();
        String farmaciaCords = "41.154389, -8.638143";

        intermedios.add("41.155035, -8.637854"); //3
        intermedios.add("41.155273, -8.638757"); //4
        intermedios.add("41.155964, -8.638500"); //5
        intermedios.add("41.155766, -8.637508"); //6
        intermedios.add("41.156360, -8.640266"); //10

        Map<Integer, String> allFarmacias = new HashMap<>();
        allFarmacias.put(1, "41.154389, -8.638143");

        Map<String, Float> pesosPorEncomenda = new HashMap<>();

        pesosPorEncomenda.put(intermedios.get(0), 1f);
        pesosPorEncomenda.put(intermedios.get(1), 1f);
        pesosPorEncomenda.put(intermedios.get(2), 1f);
        pesosPorEncomenda.put(intermedios.get(3), 1f);
        pesosPorEncomenda.put(intermedios.get(4), 1f);

        allFarmacias.put(1, "41.154389, -8.638143");

        LinkedList<String> remaining = new LinkedList<>();

        LinkedList<String> path = (LinkedList<String>) plan.mostEficientPath(intermedios, farmaciaCords, VehicleType.DRONE, 120f, 5f, pesosPorEncomenda, allFarmacias, remaining);
        LinkedList<String> exp = new LinkedList<>();
        exp.add("41.154389, -8.638143");
        exp.add("41.155035, -8.637854");
        exp.add("41.155766, -8.637508");
        exp.add("41.155964, -8.638500");
        exp.add("41.156178, -8.639322");
        exp.add("41.156360, -8.640266");
        exp.add("41.155633, -8.640427");
        exp.add("41.155435, -8.639622");
        exp.add("41.155273, -8.638757");
        exp.add("41.155035, -8.637854");
        exp.add("41.154389, -8.638143");


        assertTrue(remaining.isEmpty());
        assertEquals(exp, path);
        float energyCost = DeliveryPlanning.calculateEnergyCost(path, 5f, VehicleType.DRONE, pesosPorEncomenda);
        float spentTimeMinutes = DeliveryPlanning.getSpentTimeLastDronePath() * 60;
        float distance = plan.pathWeight(path);
        float expDistance = 0.79f;
        assertEquals(53.47, energyCost, 0.01);
        assertEquals(11.42, spentTimeMinutes, 0.1);
        assertEquals(expDistance, distance, 0.01);
    }*/

    @Test
    void getCoeficienteArrasto() {
        assertEquals(1.5f, DeliveryPlanning.getCoeficienteArrasto());
    }

    @Test
    void getCoeficienteAtrito() {
        assertEquals(0.7f, DeliveryPlanning.getCoeficienteAtritoAsfalto());
    }

    @Test
    void getMassaMediaHumano() {
        assertEquals(75f, DeliveryPlanning.getMassaMediaHumano());
    }

    @Test
    void getMassaScooter() {
        assertEquals(15f, DeliveryPlanning.getMassaScooter());
    }

    @Test
    void getMassaDrone() {
        assertEquals(3.5f, DeliveryPlanning.getMassaDrone());
    }

    @Test
    void getAreaFrontalVeiculos() {
        assertEquals(1f, DeliveryPlanning.getAreaFrontalVeiculos());
    }

    @Test
    void getDensidadeAr() {
        assertEquals(1.225f, DeliveryPlanning.getDensidadeAr());
    }


    @Test
    void getAceleracaoGravitica() {
        assertEquals(9.8f, DeliveryPlanning.getAceleracaoGravitica());
    }

    @Test
    void getVelocidadeVento() {
        assertEquals(2.77f, DeliveryPlanning.getVelocidadeVento());
    }

    @Test
    void getVelocidadeSubidaDrone() {
        assertEquals(5.56f, DeliveryPlanning.getVelocidadeSubidaDrone());
    }

    @Test
    void getVelocidadeDescidaDrone() {
        assertEquals(6f, DeliveryPlanning.getVelocidadeDescidaDrone());
    }

    @Test
    void getVelocidadeCruzeiroDrone() {
        assertEquals(4f, DeliveryPlanning.getVelocidadeCruzeiroDrone());
    }

    @Test
    void getVelocidadeScooter() {
        assertEquals(5.56f, DeliveryPlanning.getVelocidadeScooter());
    }


    @Test
    void getNomePontoByCord() {
        String result = DeliveryPlanning.getNomePontoByCord("41.154389, -8.638143");
        assertEquals("1", result);
    }

    @Test
    void getIdClosestPharmacyByUserEmail() {

        PlanningDB dbMock = mock(PlanningDB.class);
        when(dbMock.getClientCordByEmail(any(String.class))).thenReturn("41.155035, -8.637854");
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "41.154389, -8.638143");
        when(dbMock.getCordsOfAllPharmacys()).thenReturn(map);

        int result = DeliveryPlanning.getIdClosestPharmacyByUserEmail("teste", dbMock);
        assertEquals(1, result);


    }


    @Test
    void getTotalPayload() {
        Map<String, Float> pesosPorEncomenda = new HashMap<>();
        pesosPorEncomenda.put("teste", 5f);
        pesosPorEncomenda.put("teste2", 4f);

        float result = DeliveryPlanning.getTotalPayload(pesosPorEncomenda);
        assertEquals(9f, result);
    }
}