package lapr.project.model.MapGraph;

import java.util.*;

/**
 * The type Graph.
 *
 * @param <V> the type parameter
 * @param <E> the type parameter
 * @author DEI -ESINF
 */
public class Graph<V, E> implements GraphInterface<V, E> {

    private final boolean isDirected;
    private final Map<V, Vertex<V, E>> vertices;  //all Vertices of the MapGraph
    private int numVert;
    private int numEdge;

    /**
     * Instantiates a new Graph.
     *
     * @param directed the directed
     */
// Constructs an empty MapGraph (either undirected or directed)
    public Graph(boolean directed) {
        numVert = 0;
        numEdge = 0;
        isDirected = directed;
        vertices = new LinkedHashMap<>();
    }

    /**
     * Allkey verts v [ ].
     *
     * @return the v [ ]
     */
    public V[] allkeyVerts() {
        List<V> v = new LinkedList<>();
        for (V ver : vertices()) {
            v.add(ver);
        }
        @SuppressWarnings("unchecked")
        V[] ve = (V[]) v.toArray(new Object[]{});
        return ve;
    }

    public int numVertices() {
        return numVert;
    }

    public Iterable<V> vertices() {
        return vertices.keySet();
    }

    /**
     * Valid vertex boolean.
     *
     * @param vert the vert
     * @return the boolean
     */
    public boolean validVertex(V vert) {

        return vertices.get(vert) != null;
    }

    /**
     * Gets key.
     *
     * @param vert the vert
     * @return the key
     */
    public int getKey(V vert) {
        return vertices.get(vert).getKey();
    }

    /**
     * Adj vertices iterable.
     *
     * @param vert the vert
     * @return the iterable
     */
    public Iterable<V> adjVertices(V vert) {

        if (!validVertex(vert))
            return null;

        Vertex<V, E> vertex = vertices.get(vert);

        return vertex.getAllAdjVerts();
    }


    public int numEdges() {
        return numEdge;
    }


    public Iterable<Edge<V, E>> edges() {
        return new Iterable<Edge<V, E>>() {
            final LinkedList<Edge<V, E>> list = new LinkedList<>();

            @Override
            public Iterator<Edge<V, E>> iterator() {
                Iterator<Vertex<V, E>> itr = vertices.values().iterator();
                while (itr.hasNext()) {
                    Vertex<V, E> vertex = itr.next();
                    Iterator<V> itrTmp = vertices.keySet().iterator();
                    while (itrTmp.hasNext()) {
                        V v = itrTmp.next();
                        Edge<V, E> e = vertex.getEdge(v);
                        if (e != null) {
                            list.add(vertex.getEdge(v));
                        }
                    }
                }
                return list.iterator();
            }
        };
    }

    public Edge<V, E> getEdge(V vOrig, V vDest) {

        if (!validVertex(vOrig) || !validVertex(vDest))
            return null;

        Vertex<V, E> vorig = vertices.get(vOrig);

        return vorig.getEdge(vDest);
    }

    public V[] endVertices(Edge<V, E> edge) {

        if (edge == null)
            return null;

        if (!validVertex(edge.getVOrig()) || !validVertex(edge.getVDest()))
            return null;

        Vertex<V, E> vorig = vertices.get(edge.getVOrig());

        if (!edge.equals(vorig.getEdge(edge.getVDest())))
            return null;

        return edge.getEndpoints();
    }

    public V opposite(V vert, Edge<V, E> edge) {

        if (!validVertex(vert))
            return null;

        Vertex<V, E> vertex = vertices.get(vert);

        return vertex.getAdjVert(edge);
    }

    public int outDegree(V vert) {

        if (!validVertex(vert))
            return -1;

        Vertex<V, E> vertex = vertices.get(vert);

        return vertex.numAdjVerts();
    }

    public int inDegree(V vert) {

        if (!validVertex(vert))
            return -1;

        int degree = 0;
        for (V otherVert : vertices.keySet())
            if (getEdge(otherVert, vert) != null)
                degree++;

        return degree;
    }

    public Iterable<Edge<V, E>> outgoingEdges(V vert) {

        if (!validVertex(vert))
            return null;

        Vertex<V, E> vertex = vertices.get(vert);

        return vertex.getAllOutEdges();
    }

    public Iterable<Edge<V, E>> incomingEdges(V vert) {
        final List<Edge<V, E>> list = new LinkedList<>();
        Iterator<Edge<V, E>> edgeIterator = edges().iterator();
        while (edgeIterator.hasNext()) {
            Edge<V, E> edge = edgeIterator.next();
            if (edge.getVDest().equals(vert)) {
                list.add(edge);
            }
        }
        return new Iterable<Edge<V, E>>() {
            @Override
            public Iterator<Edge<V, E>> iterator() {
                return list.iterator();
            }
        };

    }

    public boolean insertVertex(V vert) {

        if (validVertex(vert))
            return false;

        Vertex<V, E> vertex = new Vertex<>(numVert, vert);
        vertices.put(vert, vertex);
        numVert++;

        return true;
    }

    public boolean insertEdge(V vOrig, V vDest, E eInf, double eWeight) {

        if (getEdge(vOrig, vDest) != null)
            return false;

        if (!validVertex(vOrig))
            insertVertex(vOrig);

        if (!validVertex(vDest))
            insertVertex(vDest);

        Vertex<V, E> vorig = vertices.get(vOrig);
        Vertex<V, E> vdest = vertices.get(vDest);

        Edge<V, E> newEdge = new Edge<>(eInf, eWeight, vorig, vdest);
        vorig.addAdjVert(vDest, newEdge);
        numEdge++;

        //if MapGraph is not direct insert other edge in the opposite direction
        if (!isDirected)
            // if vDest different vOrig
            if (getEdge(vDest, vOrig) == null) {
                Edge<V, E> otherEdge = new Edge<>(eInf, eWeight, vdest, vorig);
                vdest.addAdjVert(vOrig, otherEdge);
                numEdge++;
            }

        return true;
    }

    public boolean removeVertex(V vert) {

        if (!validVertex(vert))
            return false;

        //remove all edges that point to vert
        for (Edge<V, E> edge : incomingEdges(vert)) {
            V vadj = edge.getVOrig();
            removeEdge(vadj, vert);
        }

        Vertex<V, E> vertex = vertices.get(vert);

        //update the keys of subsequent vertices in the map
        for (Vertex<V, E> v : vertices.values()) {
            int keyVert = v.getKey();
            if (keyVert > vertex.getKey()) {
                keyVert = keyVert - 1;
                v.setKey(keyVert);
            }
        }
        //The edges that live from vert are removed with the vertex    
        vertices.remove(vert);

        numVert--;

        return true;
    }

    public boolean removeEdge(V vOrig, V vDest) {

        if (!validVertex(vOrig) || !validVertex(vDest))
            return false;

        Edge<V, E> edge = getEdge(vOrig, vDest);

        if (edge == null)
            return false;

        Vertex<V, E> vorig = vertices.get(vOrig);

        vorig.remAdjVert(vDest);
        numEdge--;

        //if MapGraph is not direct
        if (!isDirected) {
            edge = getEdge(vDest, vOrig);
            if (edge != null) {
                Vertex<V, E> vdest = vertices.get(vDest);
                vdest.remAdjVert(vOrig);
                numEdge--;
            }
        }
        return true;
    }


    //Returns a clone of the MapGraph
    public Graph<V, E> clone() {

        Graph<V, E> newObject = new Graph<>(this.isDirected);

        //insert all vertices
        for (V vert : vertices.keySet())
            newObject.insertVertex(vert);

        //insert all edges
        for (V vert1 : vertices.keySet())
            for (Edge<V, E> e : this.outgoingEdges(vert1))
                if (e != null) {
                    V vert2 = this.opposite(vert1, e);
                    newObject.insertEdge(vert1, vert2, e.getElement(), e.getWeight());
                }

        return newObject;
    }

    /* equals implementation
     * @param the other MapGraph to test for equality
     * @return true if both objects represent the same MapGraph
     */
    public boolean equals(Object otherObj) {

        if (this == otherObj)
            return true;

        if (otherObj == null || this.getClass() != otherObj.getClass())
            return false;
        @SuppressWarnings("unchecked")
        Graph<V, E> otherGraph = (Graph<V, E>) otherObj;

        if (numVert != otherGraph.numVertices() || numEdge != otherGraph.numEdges())
            return false;

        //MapGraph must have same vertices
        boolean eqvertex;
        for (V v1 : this.vertices()) {
            eqvertex = false;
            for (V v2 : otherGraph.vertices())
                if (v1.equals(v2))
                    eqvertex = true;

            if (!eqvertex)
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = (isDirected ? 1 : 0);
        result = 31 * result + (vertices != null ? vertices.hashCode() : 0);
        result = 31 * result + numVert;
        result = 31 * result + numEdge;
        return result;
    }

    //string representation
    @Override
    public String toString() {
        String s = "";
        if (numVert == 0) {
            s = "\nGraph not defined!!";
        } else {
            s = "Graph: " + numVert + " vertices, " + numEdge + " edges\n";
            for (Vertex<V, E> vert : vertices.values())
                s += vert + "\n";
        }
        return s;
    }
}