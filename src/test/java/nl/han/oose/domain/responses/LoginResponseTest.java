package nl.han.oose.domain.responses;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginResponseTest {
    private LoginResponse loginResponse;

    @Before
    public void setUp() {
        loginResponse = new LoginResponse();
    }

    @After
    public void tearDown() {
        loginResponse = null;
    }

    @Test
    public void getTokenTest() {
        String token = "test";

        loginResponse.setToken(token);

        assertEquals(token, loginResponse.getToken());
    }

    @Test
    public void getUserTest() {
        String name = "test";

        loginResponse.setUser(name);

        assertEquals(loginResponse.getUser(), name);
    }
}