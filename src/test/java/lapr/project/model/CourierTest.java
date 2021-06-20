package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourierTest {

    Courier c= new Courier("Maria","maria@gmail.com",123456789,"12345678901",CourierState.UNAVAILABLE);
    Courier c2= new Courier("José","jose@gmail.com",123456781,"12345678911",CourierState.UNAVAILABLE);
    Courier c3= new Courier("Zé","jose@gmail.com",123456781,"12345678911",CourierState.AVAILABLE);
    Courier c4= new Courier("Zé","jose@gmail.com",123456781,"12345678912",CourierState.AVAILABLE);
    Courier c5= new Courier("Zé","jose@gmail.com",123456780,"12345678911",CourierState.AVAILABLE);


    @Test
    void getName() {
        System.out.println("getName Test");
        String expResult = "Maria";
        String result = c.getName();
        assertEquals(expResult, result);
    }

    @Test
    void setName() {
        System.out.println("setName Test");

        String name = "Joana";
        c.setName(name);
        assertEquals(name, c.getName());
    }

    @Test
    void getEmail() {
        System.out.println("getEmail Test");

        String expResult = "maria@gmail.com";
        String result = c.getEmail();
        assertEquals(expResult, result);
    }

    @Test
    void setEmail() {
        System.out.println("setEmail Test");
        String email = "maria2@gmail.com.com";
        c.setEmail(email);
        assertEquals(email, c.getEmail());
    }

    @Test
    void getNif() {System.out.println("getNif Test");
        int expResult = 123456789;
        int result = c.getNif();
        assertEquals(expResult, result);
    }

    @Test
    void setNif() {
        System.out.println("setNif Test");

        int nif = 123456789;
        c.setNif(nif);
        assertEquals(nif, c.getNif());
    }

    @Test
    void getNiss() {
        System.out.println("getNiss Test");

        String expResult = "12345678901";
        String result = c.getNiss();
        assertEquals(expResult, result);
    }

    @Test
    void setNiss() {
        System.out.println("setNiss Test");

        String niss = "12345678901";
        c.setNiss(niss);
        assertEquals(niss, c.getNiss());
    }

    @Test
    void getState() {
        System.out.println("getState Test");

        CourierState expResult = CourierState.UNAVAILABLE;
        CourierState result = c.getState();
        assertEquals(expResult, result);
    }

    @Test
    void setState() {
        System.out.println("setState Test");

        CourierState cs = CourierState.AVAILABLE;
        c.setState(cs);
        assertEquals(cs, c.getState());
    }

    @Test
    void testToString() {
        System.out.println("toString Test");

        String expResult = "Name: Maria | Email: maria@gmail.com | Nif: 123456789 | Niss: 12345678901 | State: Unavailable";
        String result = c.toString();
        assertEquals(expResult, result);
    }

    @Test
    void testEquals() {
        System.out.println("equals_Object Null");

        boolean result = c == null;
        assertFalse(result);
    }

    @Test
    public void testEqualsSameObject() {
        assertEquals(c, c);
    }

    @Test
    public void testEqualsDifferentClassesObjects() {
        assertNotEquals(new Product("produto1", 20, 5, 3, 1, "A2E"), c);
    }

    @Test
    public void testEqualsSameClassDiferentInformation() {
        assertNotEquals(c2, c);

    }
    @Test
    public void testEqualsSameClassDiferentInformation2() {
        assertNotEquals(c3, c2);

    }

    @Test
    public void testEqualsSameClassDiferentInformation3() {
        assertNotEquals(c4, c3);

    }

    @Test
    public void testEqualsSameClassDiferentInformation4() {
        assertNotEquals(c5, c4);

    }

    @Test
    public void testEquals5() {
        Courier o = new Courier(c);
        assertEquals(o, c);

    }

    @Test
    public void testEquals6() {
        Courier o = new Courier(c);

        assertNotEquals( o, null);
    }


    @Test
    public void testEquals7() {
        Courier o = new Courier(c);

        assertNotEquals( o, new Address("", "", "", 0));
    }


    @Test
    void testHashCode() {
        System.out.println("HashCode test");

        int result=c.hashCode();
        int expResult= 12121212;
        assertEquals(expResult, result);
    }


}