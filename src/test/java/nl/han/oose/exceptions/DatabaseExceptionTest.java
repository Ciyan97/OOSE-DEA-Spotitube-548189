package nl.han.oose.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseExceptionTest {

    @Test(expected = DatabaseException.class)
    public void databaseExceptionTest() throws DatabaseException {
        throw new DatabaseException(DatabaseException.MESSAGE);
    }
}