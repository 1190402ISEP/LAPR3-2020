package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapPointTest {

    private MapPoint validInstance;
    private MapPoint invalidInstance;
    private MapPoint emptyInstance;

    @BeforeEach
    void setUp() {
        validInstance = new MapPoint(10.0f, 230.0f, 30.3f, "1");
        invalidInstance = new MapPoint(-1, -1, -1, "2");
        emptyInstance = new MapPoint(0, 0, 0, "3");
    }

    @Test
    void getValidLatitudeTest() {
        float actual = validInstance.getLatitude();
        float expected = 10.0f;

        assertEquals(expected, actual);
    }

    @Test
    void getInvalidLatitudeTest() {
        float actual = invalidInstance.getLatitude();

        assertFalse(actual > 0);
    }

    @Test
    void setValidLatitudeTest() {
        validInstance.setLatitude(11.0f);
        float actual = validInstance.getLatitude();
        float expected = 11.0f;

        assertEquals(expected, actual);
    }

    @Test
    void setInvalidLatitudeTest() {
        invalidInstance.setLatitude(-1);
        float actual = invalidInstance.getLatitude();

        assertFalse(actual > 0);
    }

    @Test
    void getValidLongitudeTest() {
        float actual = validInstance.getLongitude();
        float expected = 230.0f;

        assertEquals(expected, actual);
    }

    @Test
    void getInvalidLongitudeTest() {
        float actual = invalidInstance.getLongitude();

        assertFalse(actual > 0);
    }

    @Test
    void setValidLongitudeTest() {
        validInstance.setLongitude(11.0f);
        float actual = validInstance.getLongitude();
        float expected = 11.0f;

        assertEquals(expected, actual);
    }

    @Test
    void setInvalidLongitudeTest() {
        invalidInstance.setLongitude(-1);
        float actual = invalidInstance.getLongitude();

        assertFalse(actual > 0);
    }

    @Test
    void getValidAltitudeTest() {
        float actual = validInstance.getAltitude();
        float expected = 30.3f;

        assertEquals(expected, actual);
    }

    @Test
    void getInvalidAltitudeTest() {
        float actual = invalidInstance.getAltitude();

        assertFalse(actual > 0);
    }

    @Test
    void setValidAltitudeTest() {
        validInstance.setAltitude(11.0f);
        float actual = validInstance.getAltitude();
        float expected = 11.0f;

        assertEquals(expected, actual);
    }

    @Test
    void setInvalidAltitudeTest() {
        invalidInstance.setAltitude(-1);
        float actual = invalidInstance.getAltitude();

        assertFalse(actual > 0);
    }

    @Test
    void validGetAsStringTest() {
        String actual = validInstance.getAsString();
        String expected = "10.0, 230.0";

        assertEquals(expected, actual);
    }

    @Test
    void emptyGetAsStringTest() {
        String actual = emptyInstance.getAsString();
        String expected = "0.0, 0.0";

        assertEquals(expected, actual);
    }

    @Test
    void equalsTest() {
        assertEquals(validInstance, validInstance);
    }

    @Test
    void notEqualsTest() {
        assertNotEquals(validInstance, invalidInstance);
    }

    @Test
    void notEqualsTest2() {
        assertNotEquals(validInstance, null);
    }

    @Test
    void notEqualsTest3() {
        assertNotEquals(validInstance, new Address("", "", "", 1));
    }

    @Test
    void notEqualsTest4() {
        MapPoint p1  = new MapPoint(11.0f, 230.0f, 30.3f,"1");
        assertNotEquals(p1, validInstance);
        MapPoint p2  = new MapPoint(10.0f, 231.0f, 30.3f,"1");
        assertNotEquals(p2, validInstance);
        MapPoint p3  = new MapPoint(10.0f, 230.0f, 31.3f,"1");
        assertNotEquals(p3, validInstance);
        MapPoint p4  = new MapPoint(11.0f, 231.0f, 31.3f,"1");
        assertNotEquals(p4, validInstance);
    }

    @Test
    void validHashCodeTest() {
        assertEquals(-462628155, validInstance.hashCode());
    }

    @Test
    void invalidHashCodeTest() {
        assertEquals(-813665185, invalidInstance.hashCode());
    }

    @Test
    void distanceBetween() {

        MapPoint p1 = new MapPoint(41.154389f, -8.638143f, 120,"1");
        MapPoint p2 = new MapPoint(41.154740f, -8.639862f, 120,"1");

        float distance = p1.distanceBetween(p2);


        assertEquals(0.14915472, distance, 0.000001);


    }

    @Test
    void testToString() {
        MapPoint p1 = new MapPoint(41.154389f, -8.638143f, 120,"1");
        String re = p1.toString();
    }

    @Test
    void testToString2 () {
        String expected = "MapPoint{latitude=0.0, longitude=0.0, altitude=0.0}";
        String result = emptyInstance.toString();

        assertEquals( expected, result );
    }

    @Test
    void testToString3 () {
        String expected = "MapPoint{latitude=10.0, longitude=230.0, altitude=30.3}";
        String result = validInstance.toString();

        assertEquals( expected, result );
    }
}
