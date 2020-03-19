package nl.han.oose.persistence.user;

import nl.han.oose.domain.User;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.UserDoesNotExistException;
import nl.han.oose.persistence.BasePersistenceIntegrationTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDAOImplTest extends BasePersistenceIntegrationTest {

    private UserDAO userDAO;
    private User user;

    @Before
    public void before() {
        userDAO = new UserDAOImpl(newSessionFactory());

        user = new User("test", "test");
    }

    @Test(expected = UserDoesNotExistException.class)
    public void getUserThrowsExceptionTest() throws UserDoesNotExistException, DatabaseException {
        userDAO.get(user.getUser(), user.getPassword());
    }

    @Test
    public void getUserTest() throws UserDoesNotExistException, DatabaseException {
        userDAO.save(user);

        User userFromDAO = userDAO.get(user.getUser(), user.getPassword());

        Assert.assertEquals(user.getUser(), userFromDAO.getUser());
    }
}