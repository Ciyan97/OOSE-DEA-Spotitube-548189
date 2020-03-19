package nl.han.oose.exceptions.mappers;

import nl.han.oose.exceptions.ForbiddenUserException;
import nl.han.oose.exceptions.mappers.ForbiddenUserExceptionMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ForbiddenUserExceptionMapperTest {
    private ForbiddenUserExceptionMapper exceptionMapper;

    @Before
    public void setUp() throws Exception {
        exceptionMapper = new ForbiddenUserExceptionMapper();
    }

    @Test
    public void toResponseTest() {
        ForbiddenUserException exception = new ForbiddenUserException(ForbiddenUserException.MESSAGE);

        Response response = exceptionMapper.toResponse(exception);

        Assert.assertEquals(403, response.getStatus());
    }
}