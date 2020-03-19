package nl.han.oose.exceptions.mappers;

import nl.han.oose.exceptions.UserDoesNotExistException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class UserDoesNotExistExceptionMapperTest {
    private UserDoesNotExistExceptionMapper exceptionMapper;

    @Before
    public void setUp() throws Exception {
        exceptionMapper = new UserDoesNotExistExceptionMapper();
    }

    @Test
    public void toResponseTest() {
        UserDoesNotExistException exception = new UserDoesNotExistException(UserDoesNotExistException.MESSAGE);

        Response response = exceptionMapper.toResponse(exception);

        Assert.assertEquals(404, response.getStatus());
    }
}