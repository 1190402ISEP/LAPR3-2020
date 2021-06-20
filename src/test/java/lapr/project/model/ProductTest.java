package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product p1 = new Product("Benuron",400,20f,0.5f,0.33f, "A23Y2");
    Product p2 = new Product("A23Y");
    Product p3 = new Product(p1);

    @Test
    void getName1() {
        System.out.println("getName Test1");
        String expResult = "Benuron";
        String result = p1.getName();
        assertEquals(expResult, result);
    }

    @Test
    void getName2() {
        System.out.println("getName Test2");
        String result = p2.getName();
        assertNull(result);
    }

    @Test
    void getName3() {
        System.out.println("getName Test3");
        String expResult = "Benuron";
        String result = p3.getName();
        assertEquals(expResult, result);
    }

    @Test
    void getStock1() {
        System.out.println("getStock Test1");
        int expResult = 400;
        int result = p1.getStock();
        assertEquals(expResult, result);
    }

    @Test
    void getStock2() {
        System.out.println("getStock Test2");
        int result = p2.getStock();
        assertEquals(result,0);
    }

    @Test
    void getStock3() {
        System.out.println("getStock Test3");
        int expResult = 400;
        int result = p3.getStock();
        assertEquals(expResult, result);
    }

    @Test
    void getPrice1() {
        System.out.println("getPrice Test1");
        float expResult = 20f;
        float result = p1.getPrice();
        assertEquals(expResult, result);
    }

    @Test
    void getPrice2() {
        System.out.println("getPrice Test2");
        float result = p2.getPrice();
        assertEquals(result,0);
    }

    @Test
    void getPrice3() {
        System.out.println("getPrice Test3");
        float expResult = 20f;
        float result = p3.getPrice();
        assertEquals(expResult, result);
    }

    @Test
    void getWeight1() {
        System.out.println("getWeight Test1");
        float expResult = 0.5f;
        float result = p1.getWeight();
        assertEquals(expResult, result);
    }

    @Test
    void getWeight2() {
        System.out.println("getWeight Test2");
        float result = p2.getWeight();
        assertEquals(result,0);
    }

    @Test
    void getWeight3() {
        System.out.println("getWeight Test3");
        float expResult = 0.5f;
        float result = p3.getWeight();
        assertEquals(expResult, result);
    }

    @Test
    void getIva1() {
        System.out.println("getIva Test1");
        float expResult = 0.33f;
        float result = p1.getIva();
        assertEquals(expResult, result);
    }

    @Test
    void getIva2() {
        System.out.println("getIva Test2");
        float result = p2.getIva();
        assertEquals(result,0);
    }

    @Test
    void getIva3() {
        System.out.println("getIva Test3");
        float expResult = 0.33f;
        float result = p3.getIva();
        assertEquals(expResult, result);
    }

    @Test
    void getBarCode1() {
        System.out.println("getBarCode Test1");
        String expResult = "A23Y2";
        String result = p1.getBarcode();
        assertEquals(expResult, result);
    }

    @Test
    void getBarCode2() {
        System.out.println("getBarCode Test2");
        String expResult = "A23Y";
        String result = p2.getBarcode();
        assertEquals(expResult, result);
    }

    @Test
    void getBarCode3() {
        System.out.println("getBarCode Test3");
        String expResult = "A23Y2";
        String result = p3.getBarcode();
        assertEquals(expResult, result);
    }

    @Test
    void setName1() {
        System.out.println("setName Test1");

        String name = "Benuron250";
        p1.setName(name);
        assertEquals(name, p1.getName());
    }

    @Test
    void setName2() {
        System.out.println("setName Test2");

        String name = "Dafalgan";
        p2.setName(name);
        assertEquals(name, p2.getName());
    }

    @Test
    void setName3() {
        System.out.println("setName Test3");

        String name = "Betadine";
        p3.setName(name);
        assertEquals(name, p3.getName());
    }

    @Test
    void setStock1() {
        System.out.println("setStock Test1");

        int stock = 300;
        p1.setStock(stock);
        assertEquals(stock, p1.getStock());
    }

    @Test
    void setStock2() {
        System.out.println("setStock Test2");

        int stock = 600;
        p2.setStock(stock);
        assertEquals(stock, p2.getStock());
    }

    @Test
    void setStock3() {
        System.out.println("setStock Test3");

        int stock = 200;
        p3.setStock(stock);
        assertEquals(stock, p3.getStock());
    }

    @Test
    void setPrice1() {
        System.out.println("setPrice Test1");

        float price = 26.5f;
        p1.setPrice(price);
        assertEquals(price, p1.getPrice());
    }

    @Test
    void setPrice2() {
        System.out.println("setPrice Test2");

        float price = 1.5f;
        p2.setPrice(price);
        assertEquals(price, p2.getPrice());
    }

    @Test
    void setPrice3() {
        System.out.println("setPrice Test3");

        float price = 15.5f;
        p3.setPrice(price);
        assertEquals(price, p3.getPrice());
    }

    @Test
    void setWeight1() {
        System.out.println("setWeight Test1");

        float weight = 0.2f;
        p1.setWeight(weight);
        assertEquals(weight, p1.getWeight());
    }

    @Test
    void setWeight2() {
        System.out.println("setWeight Test2");

        float weight = 0.4f;
        p2.setWeight(weight);
        assertEquals(weight, p2.getWeight());
    }

    @Test
    void setWeight3() {
        System.out.println("setWeight Test3");

        float weight = 0.1f;
        p3.setWeight(weight);
        assertEquals(weight, p3.getWeight());
    }

    @Test
    void setIva1() {
        System.out.println("setIva Test1");

        float iva = 0.34f;
        p1.setIva(iva);
        assertEquals(iva, p1.getIva());
    }

    @Test
    void setIva2() {
        System.out.println("setIva Test2");

        float iva = 0.31f;
        p2.setIva(iva);
        assertEquals(iva, p2.getIva());
    }

    @Test
    void setIva3() {
        System.out.println("setIva Test3");

        float iva = 0.36f;
        p3.setIva(iva);
        assertEquals(iva, p3.getIva());
    }

    @Test
    void setBarCode1() {
        System.out.println("setBarCode Test1");

        String barCode = "A23Y";
        p1.setBarcode(barCode);
        assertEquals(barCode, p1.getBarcode());
    }

    @Test
    void setBarCode2() {
        System.out.println("setBarCode Test2");

        String barCode = "A23";
        p2.setBarcode(barCode);
        assertEquals(barCode, p2.getBarcode());
    }

    @Test
    void setBarCode3() {
        System.out.println("setBarCode Test3");

        String barCode = "A23Y2";
        p3.setBarcode(barCode);
        assertEquals(barCode, p3.getBarcode());
    }

    @Test
    void testToString() {
        String result = p1.toString();
        assertNotEquals(result,"");
    }

    @Test
    public void testEquals1() {
        Product p = new Product("Benuron",400,20f,0.5f,0.33f,"A23Y2");
        assertEquals(p, p1);
    }

    @Test
    void testEqualsNull1() {
        assertNotEquals(p1, null);
    }

    @Test
    public void testEqualsSameObject1() {
        assertEquals(p1, p1);
    }

    @Test
    public void testEqualsDifferentClassesObjects1() {
        assertNotEquals(new Scooter(3, true, 20, VehicleState.AVAILABLE, 7, 20), p1);
    }

    @Test
    public void testEquals2() {
        Product p = new Product("A23Y54");
        assertNotEquals(p, p2);
    }

    @Test
    void testEqualsNull2() {
        assertNotEquals(p2, null);
    }

    @Test
    public void testEqualsSameObject2() {
        assertEquals(p2, p2);
    }

    @Test
    public void testEqualsDifferentClassesObjects2() {
        assertNotEquals(new Scooter(3, true, 20, VehicleState.AVAILABLE, 7, 20), p2);
    }

    @Test
    public void testEquals3() {
        Product p = new Product(p1);
        assertEquals(p, p3);
    }

    @Test
    void testEqualsNull3() {
        assertNotEquals(p3, null);
    }

    @Test
    public void testEqualsSameObject3() {
        assertEquals(p3, p3);
    }

    @Test
    public void testEqualsDifferentClassesObjects3() {
        assertNotEquals(new Scooter(3, true, 20, VehicleState.AVAILABLE, 7, 20), p3);
    }


    @Test
    public void testEqualsSameClassDiferentInformation2() {
        assertNotEquals(p2, p1);
    }

    @Test
    public void testEqualsSameClassDiferentInformation6() {
        assertNotEquals(p1, p2);
    }

    @Test
    public void testEqualsSameClassDiferentInformation4() {
        assertNotEquals(p3, p2);
    }

    @Test
    public void testEqualsSameClassDiferentInformation5() {
        assertNotEquals(p2, p3);
    }

    @Test
    void testHashCode1() {
        System.out.println("HashCode test1");

        int result = p1.hashCode();
        int expResult= 61570266;
        assertEquals(expResult, result);
    }

    @Test
    void testHashCode2() {
        System.out.println("HashCode test2");

        int result = p2.hashCode();
        int expResult= 1986166;
        assertEquals(expResult, result);
    }

    @Test
    void testHashCode3() {
        System.out.println("HashCode test3");

        int result = p3.hashCode();
        int expResult= 61570266;
        assertEquals(expResult, result);
    }

    @Test
    void testReturnPriceWithIva1(){
        System.out.println("ReturnPriceWithIva test1");
        float result = p1.returnPriceWithIva();
        float expResult = 20.066f;
        assertEquals(expResult,result);
    }

    @Test
    void testReturnPriceWithIva2(){
        System.out.println("ReturnPriceWithIva test2");
        float result = p2.returnPriceWithIva();
        float expResult = 0.0f;
        assertEquals(expResult,result);
    }

    @Test
    void testReturnPriceWithIva3(){
        System.out.println("ReturnPriceWithIva test1");
        float result = p3.returnPriceWithIva();
        float expResult = 20.066f;
        assertEquals(expResult,result);
    }
}