package nl.han.oose.controllers.login;

import nl.han.oose.domain.responses.LoginResponse;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.persistence.user.UserDAO;
import nl.han.oose.domain.User;
import nl.han.oose.exceptions.UserDoesNotExistException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerImplTest {

    @Mock
    private UserDAO userDAO;

    private LoginControllerImpl loginController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginController = new LoginControllerImpl(userDAO);
    }

    @Test(expected = UserDoesNotExistException.class)
    public void getUserThrowsExceptionTest() throws UserDoesNotExistException, DatabaseException {
        User user = new User("test", "test");
        Mockito.doThrow(new UserDoesNotExistException(UserDoesNotExistException.MESSAGE))
                .when(userDAO).get(user.getUser(), user.getPassword());

        LoginResponse response = loginController.getUser(user);
    }

    @Test
    public void successfulGetUserCallsDaoTest() throws UserDoesNotExistException, DatabaseException {
        User user = new User("test", "test");
        Mockito.doReturn(user).when(userDAO).get(user.getUser(),user.getPassword());

        LoginResponse response = loginController.getUser(user);

        verify(userDAO).get(user.getUser(), user.getPassword());
    }
}