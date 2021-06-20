package lapr.project.model.MapGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author DEI-ISEP
 */
public class GraphAlgorithmsTest {

    Graph<String, String> completeMap = new Graph<>(false);
    Graph<String, String> incompleteMap = new Graph<>(false);

    public GraphAlgorithmsTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {

        completeMap.insertVertex("Porto");
        completeMap.insertVertex("Braga");
        completeMap.insertVertex("Vila Real");
        completeMap.insertVertex("Aveiro");
        completeMap.insertVertex("Coimbra");
        completeMap.insertVertex("Leiria");

        completeMap.insertVertex("Viseu");
        completeMap.insertVertex("Guarda");
        completeMap.insertVertex("Castelo Branco");
        completeMap.insertVertex("Lisboa");
        completeMap.insertVertex("Faro");

        completeMap.insertEdge("Porto", "Aveiro", "A1", 75);
        completeMap.insertEdge("Porto", "Braga", "A3", 60);
        completeMap.insertEdge("Porto", "Vila Real", "A4", 100);
        completeMap.insertEdge("Viseu", "Guarda", "A25", 75);
        completeMap.insertEdge("Guarda", "Castelo Branco", "A23", 100);
        completeMap.insertEdge("Aveiro", "Coimbra", "A1", 60);
        completeMap.insertEdge("Coimbra", "Lisboa", "A1", 200);
        completeMap.insertEdge("Coimbra", "Leiria", "A34", 80);
        completeMap.insertEdge("Aveiro", "Leiria", "A17", 120);
        completeMap.insertEdge("Leiria", "Lisboa", "A8", 150);

        completeMap.insertEdge("Aveiro", "Viseu", "A25", 85);
        completeMap.insertEdge("Leiria", "Castelo Branco", "A23", 170);
        completeMap.insertEdge("Lisboa", "Faro", "A2", 280);

        incompleteMap = completeMap.clone();

        incompleteMap.removeEdge("Aveiro", "Viseu");
        incompleteMap.removeEdge("Leiria", "Castelo Branco");
        incompleteMap.removeEdge("Lisboa", "Faro");
    }

 

    /**
     * Test of BreadthFirstSearch method, of class GraphAlgorithms.
     */
    @Test
    public void testBreadthFirstSearch() {
        System.out.println("Test BreadthFirstSearch");

        GraphAlgorithms.breadthFirstSearch(completeMap, "LX");

        assertTrue( GraphAlgorithms.breadthFirstSearch(completeMap, "LX").isEmpty() );

        LinkedList<String> path = ( LinkedList<String> ) GraphAlgorithms.breadthFirstSearch(incompleteMap, "Faro");

        assert !path.isEmpty();
        assertEquals(path.size(), 1);

        Iterator<String> it = path.iterator();
        assertEquals(it.next().compareTo("Faro"), 0);

        path = ( LinkedList<String> ) GraphAlgorithms.breadthFirstSearch(incompleteMap, "Porto");
        assert !path.isEmpty();
        assertEquals(path.size(), 7);

        path = ( LinkedList<String> ) GraphAlgorithms.breadthFirstSearch(incompleteMap, "Viseu");
        assert !path.isEmpty();
        assertEquals(path.size(), 3);
    }

    /**
     * Test of DepthFirstSearch method, of class GraphAlgorithms.
     */
    @Test
    public void testDepthFirstSearch() {
        System.out.println("Test of DepthFirstSearch");

        LinkedList<String> path;

        assertTrue( GraphAlgorithms.depthFirstSearch(completeMap, "LX").isEmpty() );

        path = ( LinkedList<String> ) GraphAlgorithms.depthFirstSearch(incompleteMap, "Faro");
        assert !path.isEmpty();
        assertEquals(path.size(), 1);

        Iterator<String> it = path.iterator();
        assertEquals(it.next().compareTo("Faro"), 0);

        path = (LinkedList<String>) GraphAlgorithms.depthFirstSearch(incompleteMap, "Porto");
        assert !path.isEmpty();
        assertEquals(path.size(), 7);

        path = (LinkedList<String>) GraphAlgorithms.depthFirstSearch(incompleteMap, "Viseu");
        assert !path.isEmpty();
        assertEquals(path.size(), 3);

        it = path.iterator();
        assertEquals(it.next().compareTo("Viseu"), 0);
        assertEquals(it.next().compareTo("Guarda"), 0);
        assertEquals(it.next().compareTo("Castelo Branco"), 0);
    }

    /**
     * Test of allPaths method, of class GraphAlgorithms.
     */
    @Test
    public void testAllPaths() {
        System.out.println("Test of all paths");

        ArrayList<LinkedList<String>> paths;

        paths = GraphAlgorithms.allPaths(completeMap, "Porto", "LX");
        assertNull(paths);

        paths = GraphAlgorithms.allPaths(incompleteMap, "Porto", "Lisboa");
        assert paths != null;
        assertEquals(paths.size(), 4);

        paths = GraphAlgorithms.allPaths(incompleteMap, "Porto", "Faro");
        assert paths != null;
        assertEquals(paths.size(), 0);
    }

    /**
     * Test of shortestPath method, of class GraphAlgorithms.
     */
    @Test
    public void testShortestPath() {
        System.out.println("Test of shortest path");

        LinkedList<String> shortPath = new LinkedList<>();
        double lenpath;
        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "LX", shortPath);
        assertEquals(lenpath, 0);

        lenpath = GraphAlgorithms.shortestPath(incompleteMap, "Porto", "Faro", shortPath);
        assertEquals(lenpath, 0);

        //lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "Porto", shortPath);
        //assertEquals(shortPath.size(), 1);

        lenpath = GraphAlgorithms.shortestPath(incompleteMap, "Porto", "Lisboa", shortPath);
        assertEquals(lenpath, 335);

        Iterator<String> it = shortPath.iterator();

        assertEquals(it.next().compareTo("Porto"), 0);
        assertEquals(it.next().compareTo("Aveiro"), 0);
        assertEquals(it.next().compareTo("Coimbra"), 0);
        assertEquals(it.next().compareTo("Lisboa"), 0);

        lenpath = GraphAlgorithms.shortestPath(incompleteMap, "Braga", "Leiria", shortPath);
        assertEquals(lenpath, 255);

        it = shortPath.iterator();

        assertEquals(it.next().compareTo("Braga"), 0);
        assertEquals(it.next().compareTo("Porto"), 0);
        assertEquals(it.next().compareTo("Aveiro"), 0);
        assertEquals(it.next().compareTo("Leiria"), 0);

        shortPath.clear();
        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "Castelo Branco", shortPath);
        assertEquals(lenpath, 335);
        assertEquals(shortPath.size(), 5);

        it = shortPath.iterator();

        assertEquals(it.next().compareTo("Porto"), 0);
        assertEquals(it.next().compareTo("Aveiro"), 0);
        assertEquals(it.next().compareTo("Viseu"), 0);
        assertEquals(it.next().compareTo("Guarda"), 0);
        assertEquals(it.next().compareTo("Castelo Branco"), 0);

        //Changing Edge: Aveiro-Viseu with Edge: Leiria-C.Branco 
        //should change shortest path between Porto and Castelo Branco

        completeMap.removeEdge("Aveiro", "Viseu");
        completeMap.insertEdge("Leiria", "Castelo Branco", "A23", 170);
        shortPath.clear();
        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "Castelo Branco", shortPath);
        assertEquals(lenpath, 365);
        assertEquals(shortPath.size(), 4);

        it = shortPath.iterator();

        assertEquals(it.next().compareTo("Porto"), 0);
        assertEquals(it.next().compareTo("Aveiro"), 0);
        assertEquals(it.next().compareTo("Leiria"), 0);
        assertEquals(it.next().compareTo("Castelo Branco"), 0);

    }

    /**
     * Test of shortestPaths method, of class GraphAlgorithms.
     */
    @Test
    public void testShortestPaths() {
        System.out.println("Test of shortest path");

        ArrayList<LinkedList<String>> paths = new ArrayList<>();
        ArrayList<Double> dists = new ArrayList<>();

        GraphAlgorithms.shortestPaths(completeMap, "Porto", paths, dists);
        assertTrue(completeMap.validVertex( "Porto" ) );
        assertFalse( paths.isEmpty() );
        assertFalse( dists.isEmpty() );

        assertEquals(paths.size(), dists.size());
        assertEquals(completeMap.numVertices(), paths.size());
        assertEquals(1, paths.get(completeMap.getKey("Porto")).size());
        assertEquals(Arrays.asList("Porto", "Aveiro", "Coimbra", "Lisboa"), paths.get(completeMap.getKey("Lisboa")));
        assertEquals(Arrays.asList("Porto", "Aveiro", "Viseu", "Guarda", "Castelo Branco"), paths.get(completeMap.getKey("Castelo Branco")));
        assertEquals(335, dists.get(completeMap.getKey("Castelo Branco")), 0.01);

        //Changing Edge: Aveiro-Viseu with Edge: Leiria-C.Branco 
        //should change shortest path between Porto and Castelo Branco        
        completeMap.removeEdge("Aveiro", "Viseu");
        completeMap.insertEdge("Leiria", "Castelo Branco", "A23", 170);
        GraphAlgorithms.shortestPaths(completeMap, "Porto", paths, dists);
        assertEquals(365, dists.get(completeMap.getKey("Castelo Branco")), 0.01);
        assertEquals(Arrays.asList("Porto", "Aveiro", "Leiria", "Castelo Branco"), paths.get(completeMap.getKey("Castelo Branco")));


        GraphAlgorithms.shortestPaths(incompleteMap, "Porto", paths, dists);
        assertEquals(Double.MAX_VALUE, dists.get(completeMap.getKey("Faro")), 0.01);
        assertEquals(335, dists.get(completeMap.getKey("Lisboa")), 0.01);
        assertEquals(Arrays.asList("Porto", "Aveiro", "Coimbra", "Lisboa"), paths.get(completeMap.getKey("Lisboa")));
        assertEquals(335, dists.get(completeMap.getKey("Lisboa")), 0.01);

        GraphAlgorithms.shortestPaths(incompleteMap, "Braga", paths, dists);
        assertEquals(255, dists.get(completeMap.getKey("Leiria")), 0.01);
        assertEquals(Arrays.asList("Braga", "Porto", "Aveiro", "Leiria"), paths.get(completeMap.getKey("Leiria")));
    }

    @Test
    public void testcalcularMenorDistanciaWarshallMethod() {
        Double[][] expResult = { {0D, 0D, 0D, 0D}, {0D, 1D, 0D, 0D}, {0D, 0D, 4D, 0D}, {0D, 0D, 0D, 9D} };
        Double[][] result = { {0D, 0D, 0D, 0D}, {0D, 1D, 2D, 3D}, {0D, 2D, 4D, 6D}, {0D, 3D, 6D, 9D} };
        GraphAlgorithms.calcularMenorDistanciaWarshallMethod(result);
        assertEquals(result[1][1], expResult[1][1]);
        assertEquals(result[0][0], expResult[0][0]);
    }

}

