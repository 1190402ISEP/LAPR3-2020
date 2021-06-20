package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class BasketProductTest {

    BasketProduct p;

    @BeforeEach
    void setUp() {
        p = new BasketProduct("1", 1);
    }

    @Test
    void getProductId() {

        assertEquals("1", p.getBarcode());
    }

    @Test
    void setBarCode1() {
        p.setBarcode("2");
        assertEquals("2", p.getBarcode());
    }

    @Test
    void getQuantity() {
        assertEquals(1, p.getQuantity());
    }

    @Test
    void setQuantity() {
        p.setQuantity(2);
        assertEquals(2, p.getQuantity());
    }

    @Test
    void testEquals() {
        BasketProduct test = new BasketProduct("1", 1);
        assertEquals(test, p);
    }

    @Test
    void testEquals2() {
        BasketProduct test = new BasketProduct("2", 1);
        assertNotEquals(test, p);
    }

    @Test
    void testEquals3() {
        BasketProduct test = new BasketProduct("1", 2);
        assertNotEquals(test, p);
    }

    @Test
    void testEquals4() {
        BasketProduct test = new BasketProduct("2", 2);
        assertNotEquals(test, p);
    }

    @Test
    void testEquals5() {
        Product pro = new Product("A23Y42");
        assertNotEquals(pro, p);
    }

    @Test
    void testEquals6() {

        assertEquals(p, p);
    }

    @Test
    void testEquals7() {

        p.setBarcode(null);
        BasketProduct pp = new BasketProduct(null, 1);
        assertEquals(pp, p);

    }

    @Test
    void testEquals8() {
        assertNotEquals( p, null);
    }

    @Test
    void testEquals9() {
        assertFalse( p.equals( new Address("", "", "", 0)));
    }


    @Test
    void testHashCode() {
        assertEquals(1520, p.hashCode());
    }

    @Test
    void testHashCode2() {

        BasketProduct bp = new BasketProduct("", 0 );
        assertEquals(0, bp.hashCode());
    }
}