package lapr.project.model.MapGraph;

import java.lang.reflect.Array;

/**
 * The type Edge.
 *
 * @param <V> the type parameter
 * @param <E> the type parameter
 * @author DEI -ESINF
 */
public class Edge<V, E> implements Comparable<Edge<V, E>> {

    private E element;           // Edge information
    private double weight;       // Edge weight
    private Vertex<V, E> vOrig;  // vertex origin
    private Vertex<V, E> vDest;  // vertex destination

    /**
     * Instantiates a new Edge.
     */
    public Edge() {
        element = null;
        weight = 0.0;
        vOrig = null;
        vDest = null;
    }

    /**
     * Instantiates a new Edge.
     *
     * @param eInf the e inf
     * @param ew   the ew
     * @param vo   the vo
     * @param vd   the vd
     */
    public Edge(E eInf, double ew, Vertex<V, E> vo, Vertex<V, E> vd) {
        element = eInf;
        weight = ew;
        vOrig = vo;
        vDest = vd;
    }

    /**
     * Gets element.
     *
     * @return the element
     */
    public E getElement() {
        return element;
    }

    /**
     * Sets element.
     *
     * @param eInf the e inf
     */
    public void setElement(E eInf) {
        element = eInf;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param ew the ew
     */
    public void setWeight(double ew) {
        weight = ew;
    }

    /**
     * Gets v orig.
     *
     * @return the v orig
     */
    public V getVOrig() {
        if (this.vOrig != null)
            return vOrig.getElement();
        return null;
    }

    /**
     * Sets v orig.
     *
     * @param vo the vo
     */
    public void setVOrig(Vertex<V, E> vo) {
        vOrig = vo;
    }

    /**
     * Gets v dest.
     *
     * @return the v dest
     */
    public V getVDest() {
        if (this.vDest != null)
            return vDest.getElement();
        return null;
    }

    /**
     * Sets v dest.
     *
     * @param vd the vd
     */
    public void setVDest(Vertex<V, E> vd) {
        vDest = vd;
    }

    /**
     * Get endpoints v [ ].
     *
     * @return the v [ ]
     */
    public V[] getEndpoints() {

        V oElem = null, dElem = null, typeElem = null;

        if (this.vOrig != null)
            oElem = vOrig.getElement();

        if (this.vDest != null)
            dElem = vDest.getElement();

        if (oElem == null && dElem == null)
            return null;

        if (oElem != null)          // To get type
            typeElem = oElem;

        if (dElem != null)
            typeElem = dElem;

        @SuppressWarnings("unchecked")
        V[] endverts = (V[]) Array.newInstance(typeElem.getClass(), 2);

        endverts[0] = oElem;
        endverts[1] = dElem;

        return endverts;
    }

    @Override
    public boolean equals(Object otherObj) {

        if (this == otherObj) {
            return true;
        }

        if (otherObj == null || this.getClass() != otherObj.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Edge<V, E> otherEdge = (Edge<V, E>) otherObj;

        // if endpoints vertices are not equal
        if ((this.vOrig == null && otherEdge.vOrig != null) ||
                (this.vOrig != null && otherEdge.vOrig == null))
            return false;

        if ((this.vDest == null && otherEdge.vDest != null) ||
                (this.vDest != null && otherEdge.vDest == null))
            return false;

        if (this.vOrig != null && otherEdge.vOrig != null &&
                !this.vOrig.equals(otherEdge.vOrig))
            return false;

        if (this.vDest != null && otherEdge.vDest != null &&
                !this.vDest.equals(otherEdge.vDest))
            return false;


        return true;
    }

    @Override
    public int compareTo(Edge<V, E> otherObject) {


        if (this.weight < otherObject.weight) return -1;
        if (this.weight == otherObject.weight) return 0;
        return 1;
    }

    @Override
    public Edge<V, E> clone() {

        Edge<V, E> newEdge = new Edge<>();

        newEdge.element = element;
        newEdge.weight = weight;
        newEdge.vOrig = vOrig;
        newEdge.vDest = vDest;

        return newEdge;
    }

    @Override
    public String toString() {
        String st = "";
        if (element != null)
            st = "      (" + element + ") - ";
        else
            st = "\t ";

        if (weight != 0)
            st += weight + " - " + vDest.getElement() + "\n";
        else
            st += vDest.getElement() + "\n";

        return st;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = element != null ? element.hashCode() : 0;
        temp = Double.doubleToLongBits(weight);
        result = 31 * result;
        return result;
    }
}
