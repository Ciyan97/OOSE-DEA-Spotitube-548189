package nl.han.oose.services.rest;

import nl.han.oose.controllers.login.LoginController;

import nl.han.oose.domain.User;
import nl.han.oose.domain.responses.LoginResponse;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.UserDoesNotExistException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import javax.ws.rs.core.Response;

public class LoginServiceTest {

    @Mock
    private LoginController controller;

    private LoginService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new LoginService(controller);
    }

    @Test(expected = UserDoesNotExistException.class)
    public void loginThrowsExceptionWhenUserIsNullTest() throws UserDoesNotExistException, DatabaseException {
        service.login(null);
    }

    @Test()
    public void loginTest() throws UserDoesNotExistException, DatabaseException {
        int responseStatus = 200;
        User fakeUser = new User("test", "test");
        LoginResponse fakeLoginResponse = new LoginResponse();
        Mockito.doReturn(fakeLoginResponse).when(controller).getUser(fakeUser);

        Response responseFromService = service.login(fakeUser);

        Assert.assertEquals(responseStatus, responseFromService.getStatus());
    }

    @Test
    public void emptyConstructorTest() {
        service = new LoginService();

        Assert.assertNotNull(service);
    }
}