package nl.han.oose.services.rest;

import nl.han.oose.domain.Playlist;
import nl.han.oose.domain.User;
import nl.han.oose.domain.tracks.Track;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.persistence.DAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class UtilServiceTest {

    @Mock
    private DAO<User> userDAO;
    @Mock
    private DAO<Playlist> playlistDAO;
    @Mock
    private DAO<Track> trackDAO;

    private UtilService utilService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        utilService = new UtilService(userDAO, playlistDAO, trackDAO);
    }

    @Test
    public void insertDatabaseTestDataTest() throws DatabaseException {
        int statusCode = 200;

        Response response = utilService.insertDatabaseTestData();

        Assert.assertEquals(statusCode, response.getStatus());
    }

    @Test
    public void emptyConstructorTest() {
        utilService = new UtilService();

        Assert.assertNotNull(utilService);
    }
}