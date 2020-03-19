package nl.han.oose.services.rest;

import nl.han.oose.controllers.track.TrackController;
import nl.han.oose.controllers.utils.UserMap;
import nl.han.oose.domain.User;

import nl.han.oose.domain.requests.TrackRequest;
import nl.han.oose.domain.responses.TrackResponse;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;
import nl.han.oose.exceptions.ForbiddenUserException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;

public class TrackServiceTest {

    @Mock
    private TrackController trackController;

    private String validToken = "1234";
    private int responseStatus = 200;
    private int playlistId = 1;

    private TrackService service;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new TrackService(trackController);
        UserMap.getInstance().put(validToken, new User());
        User user = UserMap.getInstance().get(validToken);
    }

    @Test(expected = ForbiddenUserException.class)
    public void getAllTracksThrowsExceptionTest()
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {
        String invalidToken = "0";

        service.getAllTracks(playlistId, invalidToken);
    }

    @Test
    public void getAllTracksWithIdTest()
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        Mockito.doReturn(new TrackResponse()).when(trackController).getAllTracksNotInPlaylist(playlistId);

        Response responseFromService = service.getAllTracks(playlistId, validToken);

        Assert.assertEquals(responseStatus, responseFromService.getStatus());
    }

    @Test
    public void getAllTracksWithoutIdTest()
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        Mockito.doReturn(new TrackResponse()).when(trackController).getAllTracks();

        Response responseFromService = service.getAllTracks(0, validToken);

        Assert.assertEquals(responseStatus, responseFromService.getStatus());
    }

    @Test
    public void emptyConstructorTest() {
        service = new TrackService();

        Assert.assertNotNull(service);
    }
}