package nl.han.oose.exceptions.mappers;

import nl.han.oose.exceptions.EntityNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class EntityNotFoundExceptionMapperTest {
    private EntityNotFoundExceptionMapper exceptionMapper;

    @Before
    public void setUp() throws Exception {
        exceptionMapper = new EntityNotFoundExceptionMapper();
    }

    @Test
    public void toResponseTest() {
        EntityNotFoundException exception = new EntityNotFoundException(EntityNotFoundException.MESSAGE);

        Response response = exceptionMapper.toResponse(exception);

        Assert.assertEquals(404, response.getStatus());
    }
}