package lapr.project.model.MapGraph;

import java.util.Iterator;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author DEI-ISEP
 */
public class GraphTest {

    Graph<String, String> instance = new Graph<>(true);

    public GraphTest() {
    }

    @Before
    public void setUp() {

    }

    /**
     * Test of numVertices method, of class Graph.
     */
    @Test
    public void testNumVertices() {
        System.out.println("Test numVertices");

        assertTrue((instance.numVertices() == 0), "result should be zero");

        instance.insertVertex("A");
        assertTrue((instance.numVertices() == 1), "result should be one");

        instance.insertVertex("B");
        assertTrue((instance.numVertices() == 2), "result should be two");

        instance.removeVertex("A");
        assertTrue((instance.numVertices() == 1), "result should be one");

        instance.removeVertex("B");
        assertTrue((instance.numVertices() == 0), "result should be zero");
    }

    /**
     * Test of vertices method, of class Graph.
     */
    @Test
    public void testVertices() {
        System.out.println("Test vertices");

        Iterator<String> itVerts = instance.vertices().iterator();

        assertFalse(itVerts.hasNext());

        instance.insertVertex("A");
        instance.insertVertex("B");

        itVerts = instance.vertices().iterator();

        assertTrue((itVerts.next().compareTo("A") == 0), "first vertice should be A");
        assertTrue((itVerts.next().compareTo("B") == 0), "second vertice should be B");

        instance.removeVertex("A");

        itVerts = instance.vertices().iterator();
        assertEquals((itVerts.next().compareTo("B")), 0, "first vertice should now be B");

        instance.removeVertex("B");

        itVerts = instance.vertices().iterator();
        assertFalse(itVerts.hasNext(), "vertices should now be empty");
    }

    /**
     * Test of numEdges method, of class Graph.
     */
    @Test
    public void testNumEdges() {
        System.out.println("Test numEdges");

        assertTrue((instance.numEdges() == 0), "result should be zero");

        instance.insertEdge("A", "B", "Edge1", 6);
        assertTrue((instance.numEdges() == 1), "result should be one");

        instance.insertEdge("A", "C", "Edge2", 1);
        assertTrue((instance.numEdges() == 2), "result should be two");

        instance.removeEdge("A", "B");
        assertTrue((instance.numEdges() == 1), "result should be one");

        instance.removeEdge("A", "C");
        assertTrue((instance.numEdges() == 0), "result should be zero");
    }

    /**
     * Test of edges method, of class Graph.
     */
    @Test
    public void testEdges() {
        System.out.println("Test Edges");

        Iterator<Edge<String, String>> itEdge = instance.edges().iterator();

        assertFalse(itEdge.hasNext());

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        itEdge = instance.edges().iterator();

        itEdge.next();
        itEdge.next();
        assertEquals(itEdge.next().getElement(), "Edge3", "third edge should be B-D");

        itEdge.next();
        itEdge.next();
        assertEquals(itEdge.next().getElement(), "Edge6", "sixth edge should be D-A");

        instance.removeEdge("A", "B");

        itEdge = instance.edges().iterator();
        assertEquals(itEdge.next().getElement(), "Edge2", "first edge should now be A-C");

        instance.removeEdge("A", "C");
        instance.removeEdge("B", "D");
        instance.removeEdge("C", "D");
        instance.removeEdge("C", "E");
        instance.removeEdge("D", "A");
        instance.removeEdge("E", "D");
        instance.removeEdge("E", "E");
        itEdge = instance.edges().iterator();
        assertFalse((itEdge.hasNext()), "edges should now be empty");
    }

    /**
     * Test of getEdge method, of class Graph.
     */
    @Test
    public void testGetEdge() {
        System.out.println("Test getEdge");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        assertNull(instance.getEdge("A", "E"));

        assertEquals(instance.getEdge("B", "D").getElement(), "Edge3", "edge between B-D");
        assertNull(instance.getEdge("D", "B"), "edge should be null");

        instance.removeEdge("D", "A");
        assertNull(instance.getEdge("D", "A"), "edge should be null");

        assertEquals(instance.getEdge("E", "E").getElement(), "Edge8", "edge should be edge8");
    }

    /**
     * Test of endVertices method, of class Graph.
     */
    @Test
    public void testEndVertices() {
        System.out.println("Test endVertices");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);
        instance.insertEdge(null, null, "Edge9", 1);

        Edge<String, String> edge0 = new Edge<>();


        assertNull(instance.endVertices(edge0));

        Edge<String, String> edge1 = instance.getEdge("A", "B");
        //String[] vertices = instance.endVertices(edge1);
        assertEquals(instance.endVertices(edge1)[0], "A");
        assertEquals(instance.endVertices(edge1)[1], "B");

        Edge<String, String> edge2 = instance.getEdge(null, null);
        assertNull(instance.endVertices(edge2));

        assertNull(instance.endVertices(null));
    }

    /**
     * Test of opposite method, of class Graph.
     */
    @Test
    public void testOpposite() {
        System.out.println("Test opposite");

        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        Edge<String, String> edge5 = instance.getEdge("C", "E");
        String vert = instance.opposite("A", edge5);
        assertNull(vert, "opposite should be null");

        Edge<String, String> edge1 = instance.getEdge("A", "B");
        vert = instance.opposite("A", edge1);
        assertEquals(vert, "B", "opposite should be B");

        Edge<String, String> edge8 = instance.getEdge("E", "E");
        vert = instance.opposite("E", edge8);
        assertEquals(vert, "E", "opposite should be E");
    }

    /**
     * Test of outDegree method, of class Graph.
     */
    @Test
    public void testOutDegree() {
        System.out.println("Test outDegree");

        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        int outdeg = instance.outDegree("G");
        assertEquals(-1, outdeg, "degree should be -1");

        outdeg = instance.outDegree("A");
        assertEquals(outdeg, 2, "degree should be 2");

        outdeg = instance.outDegree("B");
        assertEquals(outdeg, 1, "degree should be 1");

        outdeg = instance.outDegree("E");
        assertEquals(outdeg, 2, "degree should be 2");
    }

    /**
     * Test of inDegree method, of class Graph.
     */
    @Test
    public void testInDegree() {
        System.out.println("Test inDegree");

        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        int indeg = instance.inDegree("G");
        assertEquals(-1, indeg, "in degree should be -1");

        indeg = instance.inDegree("A");
        assertEquals(indeg, 1, "in degree should be 1");

        indeg = instance.inDegree("D");
        assertEquals(indeg, 3, "in degree should be 3");

        indeg = instance.inDegree("E");
        assertEquals(indeg, 2, "in degree should be 2");
    }

    /**
     * Test of outgoingEdges method, of class Graph.
     */
    @Test
    public void testOutgoingEdges() {
        System.out.println(" Test outgoingEdges");

        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        Iterator<Edge<String, String>> itEdge = instance.outgoingEdges("C").iterator();
        Edge<String, String> first = itEdge.next();
        Edge<String, String> second = itEdge.next();
        assertTrue(((first.getElement().equals("Edge4") && second.getElement().equals("Edge5")) ||
                        (first.getElement().equals("Edge5") && second.getElement().equals("Edge4"))),
                "Outgoing Edges of vert C should be Edge4 and Edge5");

        instance.removeEdge("E", "E");

        itEdge = instance.outgoingEdges("E").iterator();
        assertEquals(itEdge.next().getElement(), "Edge7", "first edge should be Edge7");

        instance.removeEdge("E", "D");

        itEdge = instance.outgoingEdges("E").iterator();
        assertTrue((!itEdge.hasNext()), "edges should be empty");
    }

    /**
     * Test of incomingEdges method, of class Graph.
     */
    @Test
    public void testIncomingEdges() {

        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        Iterator<Edge<String, String>> itEdge = instance.incomingEdges("D").iterator();

        assertEquals(itEdge.next().getElement(), "Edge3", "first edge should be edge3");
        assertEquals(itEdge.next().getElement(), "Edge4", "second edge should be edge4");
        assertEquals(itEdge.next().getElement(), "Edge7", "third edge should be edge7");

        itEdge = instance.incomingEdges("E").iterator();

        assertEquals(itEdge.next().getElement(), "Edge5", "first edge should be Edge5");
        assertEquals(itEdge.next().getElement(), "Edge8", "second edge should be Edge8");

        instance.removeEdge("E", "E");

        itEdge = instance.incomingEdges("E").iterator();

        assertEquals(itEdge.next().getElement(), "Edge5", "first edge should be Edge5");

        instance.removeEdge("C", "E");

        itEdge = instance.incomingEdges("E").iterator();
        assertTrue((!itEdge.hasNext()), "edges should be empty");
    }

    /**
     * Test of insertVertex method, of class Graph.
     */
    @Test
    public void testInsertVertex() {
        System.out.println("Test insertVertex");

        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");

        Iterator<String> itVert = instance.vertices().iterator();

        assertEquals(itVert.next(), "A");
        assertEquals(itVert.next(), "B");
        assertEquals(itVert.next(), "C");
        assertEquals(itVert.next(), "D");
        assertEquals(itVert.next(), "E");

        assertFalse(instance.insertVertex("A"));
    }

    /**
     * Test of insertEdge method, of class Graph.
     */
    @Test
    public void testInsertEdge() {
        System.out.println("Test insertEdge");

        assertTrue((instance.numEdges() == 0), "num. edges should be zero");

        instance.insertEdge("A", "B", "Edge1", 6);
        assertTrue((instance.numEdges() == 1), "num. edges should be 1");

        assertFalse(instance.insertEdge("A", "B", "Edge1", 6));

        instance.insertEdge("A", "C", "Edge2", 1);
        assertTrue((instance.numEdges() == 2), "num. edges should be 2");

        instance.insertEdge("B", "D", "Edge3", 3);
        assertTrue((instance.numEdges() == 3), "num. edges should be 3");

        instance.insertEdge("C", "D", "Edge4", 4);
        assertTrue((instance.numEdges() == 4), "num. edges should be 4");

        instance.insertEdge("C", "E", "Edge5", 1);
        assertTrue((instance.numEdges() == 5), "num. edges should be 5");

        instance.insertEdge("D", "A", "Edge6", 2);
        assertTrue((instance.numEdges() == 6), "num. edges should be 6");

        instance.insertEdge("E", "D", "Edge7", 1);
        assertTrue((instance.numEdges() == 7), "num. edges should be 7");

        instance.insertEdge("E", "E", "Edge8", 1);
        assertTrue((instance.numEdges() == 8), "num. edges should be 8");

        Iterator<Edge<String, String>> itEd = instance.edges().iterator();

        itEd.next();
        itEd.next();
        assertEquals(itEd.next().getElement(), "Edge3", "third edge should be Edge3");
        itEd.next();
        itEd.next();
        assertEquals(itEd.next().getElement(), "Edge6", "sixth edge should be Edge6");
    }

    /**
     * Test of removeVertex method, of class Graph.
     */
    @Test
    public void testRemoveVertex() {
        System.out.println("Test removeVertex");

        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");

        instance.removeVertex("C");
        assertTrue((instance.numVertices() == 4), "Num vertices should be 4");

        assertFalse(instance.removeVertex("F"));

        Iterator<String> itVert = instance.vertices().iterator();
        assertEquals(itVert.next(), "A", "first vertex should be A");
        assertEquals(itVert.next(), "B", "second vertex should be B");
        assertEquals(itVert.next(), "D", "third vertex should be D");
        assertEquals(itVert.next(), "E", "fourth vertex should be E");

        instance.removeVertex("A");
        assertTrue((instance.numVertices() == 3), "Num vertices should be 3");

        itVert = instance.vertices().iterator();
        assertEquals(itVert.next(), "B", "first vertex should be B");
        assertEquals(itVert.next(), "D", "second vertex should be D");
        assertEquals(itVert.next(), "E", "third vertex should be E");

        instance.removeVertex("E");
        assertTrue((instance.numVertices() == 2), "Num vertices should be 2");

        itVert = instance.vertices().iterator();

        assertEquals(itVert.next(), "B", "first vertex should be B");
        assertEquals(itVert.next(), "D", "second vertex should be D");

        instance.removeVertex("B");
        instance.removeVertex("D");
        assertTrue((instance.numVertices() == 0), "Num vertices should be 4");
    }

    /**
     * Test of removeEdge method, of class Graph.
     */
    @Test
    public void testRemoveEdge() {
        System.out.println("Test removeEdge");

        assertTrue((instance.numEdges() == 0), "num. edges should be zero");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        assertTrue((instance.numEdges() == 8), "Num. edges should be 8");

        assertFalse(instance.removeEdge("E", "F"));

        instance.removeEdge("E", "E");
        assertTrue((instance.numEdges() == 7), "Num. edges should be 7");

        Iterator<Edge<String, String>> itEd = instance.edges().iterator();

        itEd.next();
        itEd.next();
        assertEquals(itEd.next().getElement(), "Edge3", "third edge should be Edge3");
        itEd.next();
        itEd.next();
        assertEquals(itEd.next().getElement(), "Edge6", "sixth edge should be Edge6");

        instance.removeEdge("C", "D");
        assertTrue((instance.numEdges() == 6), "Num. edges should be 6");

        itEd = instance.edges().iterator();
        itEd.next();
        itEd.next();
        assertEquals(itEd.next().getElement(), "Edge3", "third edge should be Edge3");
        assertEquals(itEd.next().getElement(), "Edge5", "fourth edge should be Edge5");
        assertEquals(itEd.next().getElement(), "Edge6", "fifth edge should be Edge6");
        assertEquals(itEd.next().getElement(), "Edge7", "...last edge should be Edge7");
    }

    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testClone() {
        System.out.println("Test Clone");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        Graph<String, String> instClone = instance.clone();

        assertEquals(instClone.numVertices(), instance.numVertices(), "number of vertices should be equal");
        assertEquals(instClone.numEdges(), instance.numEdges(), "number of edges should be equal");

        //vertices should be equal
        Iterator<String> itvertClone = instClone.vertices().iterator();
        for (String s : instance.vertices()) assertEquals(itvertClone.next(), s, "vertices should be equal ");
    }

    @Test
    public void testEquals() {
        System.out.println("Test Equals");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        assertNotEquals(instance, null, "should not be equal to null");

        assertEquals(instance, instance, "should be equal to itself");

        assertEquals(instance.clone(), instance, "should be equal to a clone");

        Graph<String, String> other = instance.clone();

        other.removeEdge("E", "E");
        assertNotEquals(other, instance, "instance should not be equal to other");

        other.insertEdge("E", "E", "Edge8", 1);
        assertEquals(other, instance, "instance should be equal to other");

        other.removeVertex("D");
        assertNotEquals(other, instance, "instance should not be equal to other");

    }


    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testToString() {

        System.out.println(instance);
    }

    @Test
    public void testRemoveEdge2 () {

        Vertex<Integer, String> first = new Vertex<>(1, 1);
        Vertex<Integer, String> second = new Vertex<>(1, 2);

        Edge<Integer, String> validEdge = first.getEdge( 2 );

        assertNull( validEdge );
    }

    @Test
    public void testRemoveEdge3 () {

        instance.insertVertex( "First" );
        instance.insertVertex( "Second" );

        assertFalse( instance.removeEdge("First", "Second" ) );
    }

    @Test
    public void testRemoveEdge4 () {

        instance.insertVertex( "First" );
        instance.insertVertex( "Second" );

        instance.insertEdge("First", "Second", "", 1);

        assertTrue( instance.removeEdge("First", "Second" ) );
    }
}

