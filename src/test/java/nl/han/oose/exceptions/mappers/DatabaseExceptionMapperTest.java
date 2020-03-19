package nl.han.oose.exceptions.mappers;

import nl.han.oose.exceptions.DatabaseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class DatabaseExceptionMapperTest {
    private DatabaseExceptionMapper databaseExceptionMapper;

    @Before
    public void setUp() throws Exception {
        databaseExceptionMapper = new DatabaseExceptionMapper();
    }

    @Test
    public void toResponseTest() {
        DatabaseException exception = new DatabaseException(DatabaseException.MESSAGE);

        Response response = databaseExceptionMapper.toResponse(exception);

        Assert.assertEquals(500, response.getStatus());
    }
}