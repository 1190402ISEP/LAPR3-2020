package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User u1 = new User("User@gmail.com", "password");
    User u2 = new User("User@gmail.com", "password", "role");

    @Test
    void getEmail1() {
        System.out.println("getEmail Test1");
        String expResult = "User@gmail.com";
        String result = u1.getEmail();
        assertEquals(expResult, result);
    }

    @Test
    void getEmail2() {
        System.out.println("getEmail Test2");
        String expResult = "User@gmail.com";
        String result = u2.getEmail();
        assertEquals(expResult, result);
    }

    @Test
    void getPassword1() {
        System.out.println("getPassword Test1");
        String expResult = "password";
        String result = u1.getPassword();
        assertEquals(expResult, result);
    }

    @Test
    void getPassword2() {
        System.out.println("getPassword Test2");
        String expResult = "password";
        String result = u2.getPassword();
        assertEquals(expResult, result);
    }

    @Test
    void getRole1() {
        System.out.println("getRole Test1");
        String result = u1.getRole();
        assertNull(result);
    }

    @Test
    void getRole2() {
        System.out.println("getRole Test2");
        String expResult = "role";
        String result = u2.getRole();
        assertEquals(expResult, result);
    }

    @Test
    void setEmail1() {
        System.out.println("setEmail Test1");

        String email = "ola@gmail.com";
        u1.setEmail(email);
        assertEquals(email, u1.getEmail());
    }

    @Test
    void setEmail2() {
        System.out.println("setEmail Test2");

        String email = "ola2@gmail.com";
        u2.setEmail(email);
        assertEquals(email, u2.getEmail());
    }

    @Test
    void setPassword1() {
        System.out.println("setPassword Test1");

        String pass = "password1";
        u1.setPassword(pass);
        assertEquals(pass, u1.getPassword());
    }

    @Test
    void setPassword2() {
        System.out.println("setPassword Test2");

        String pass = "password2";
        u2.setPassword(pass);
        assertEquals(pass, u2.getPassword());
    }

    @Test
    void setRole1() {
        System.out.println("setRole Test1");

        String role = "role1";
        u1.setRole(role);
        assertEquals(role, u1.getRole());
    }

    @Test
    void setRole2() {
        System.out.println("setRole Test2");

        String role = "role2";
        u2.setRole(role);
        assertEquals(role, u2.getRole());
    }
}