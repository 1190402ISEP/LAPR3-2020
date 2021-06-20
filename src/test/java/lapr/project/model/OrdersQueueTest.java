package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrdersQueueTest {

    @BeforeEach
    void setup() {
        OrdersQueue.setOrders(new HashMap<>());
    }

    @Test
    void addOrderToQueue() {
        boolean ver = OrdersQueue.addOrderToQueue(1, 1);
        assertTrue(ver);
        Map<Integer, List<Integer>> mapResult = OrdersQueue.getOrders();
        Map<Integer, List<Integer>> exp = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        exp.put(1, list);
        assertEquals(exp, mapResult);
    }

    @Test
    void addOrderToQueue2() {
        OrdersQueue.addOrderToQueue(1, 1);
        Map<Integer, List<Integer>> exp = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        exp.put(1, list);
        boolean ver = OrdersQueue.addOrderToQueue(1, 2);
        assertTrue(ver);
        Map<Integer, List<Integer>> mapResult = OrdersQueue.getOrders();
        list.add(2);
        exp.put(1, list);

        assertEquals(exp, mapResult);
    }

    @Test
    void removeOrderFromQueue() {
        OrdersQueue.addOrderToQueue(1, 1);
        Map<Integer, List<Integer>> mapResult = OrdersQueue.getOrders();
        Map<Integer, List<Integer>> exp = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        exp.put(1, list);
        boolean ver = OrdersQueue.removeOrderFromQueue(1);
        assertTrue(ver);
        list.remove(Integer.valueOf(1));
        assertEquals(exp, mapResult);
    }

    @Test
    void removeOrderFromQueue2() {
        boolean ver = OrdersQueue.removeOrderFromQueue(1);
        assertFalse(ver);
    }

    @Test
    void removeOrderFromQueue3() {
        OrdersQueue.addOrderToQueue(1, 1);
        Map<Integer, List<Integer>> mapResult = OrdersQueue.getOrders();
        Map<Integer, List<Integer>> exp = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        exp.put(1, list);
        boolean ver = OrdersQueue.removeOrderFromQueue(2);
        assertFalse(ver);
        assertEquals(exp, mapResult);
    }


    @Test
    void clearQueue() {


        OrdersQueue.addOrderToQueue(1, 1);


        boolean ver = OrdersQueue.clearQueue();

        assertTrue(ver);


    }


    @Test
    void getOrders() {
        Map<Integer, List<Integer>> temp = new HashMap<>();
        OrdersQueue.setOrders(temp);
        assertEquals(OrdersQueue.getOrders(), temp);
    }

    @Test
    void getOrder2() {
        Map<Integer, List<Integer>> temp = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(2);
        temp.put(1, list);
        OrdersQueue.setOrders(temp);
        assertEquals(OrdersQueue.getOrders(), temp);
    }


}