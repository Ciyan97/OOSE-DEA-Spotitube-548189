package nl.han.oose.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

 class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void getUserTest() {
        String name = "test";

        user.setUser(name);

        assertEquals(user.getUser(), name);
    }
    @Test
    public void getPasswordTest() {
        String password = "test";

        user.setPassword(password);

        assertEquals(user.getPassword(), password);
    }
    @Test
    public void getIdTest() {
        int id = 1;

        user.setId(id);

        assertEquals(user.getId(), id);
    }
}