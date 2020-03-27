package nl.han.oose.services.rest;

import nl.han.oose.controllers.playlist.PlaylistController;
import nl.han.oose.controllers.track.TrackController;
import nl.han.oose.controllers.utils.UserMap;
import nl.han.oose.domain.Playlist;
import nl.han.oose.domain.User;
import nl.han.oose.domain.requests.PlaylistRequest;
import nl.han.oose.domain.requests.TrackRequest;
import nl.han.oose.domain.responses.PlaylistResponse;
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

public class PlaylistServiceTest {

    @Mock
    private PlaylistController playlistController;
    @Mock
    private TrackController trackController;

    private String token = "1234";
    private String invalidToken = "0";
    private int responseStatus = 200;
    private int playlistId = 1;
    private User user;

    private PlaylistService service;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new PlaylistService(playlistController, trackController);
        UserMap.getInstance().put(token, new User());
        user = UserMap.getInstance().get(token);
    }

    @Test(expected = ForbiddenUserException.class)
    public void getAllPlaylistsThrowsExceptionTest()
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        service.getAllPlaylists(invalidToken);
    }

    @Test
    public void successfulGetAllPlaylistsTest()
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        Mockito.doReturn(new PlaylistResponse()).when(playlistController).getAllPlaylists(user);

        Response responseFromService = service.getAllPlaylists(token);

        Assert.assertEquals(responseStatus, responseFromService.getStatus());
    }

    @Test(expected = ForbiddenUserException.class)
    public void deletePlaylistByIdThrowsExceptionTest()
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        service.deletePlaylistById(1, invalidToken);
    }

    @Test
    public void successfulDeletePlaylistByIdTest()
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        Mockito.doReturn(new PlaylistResponse()).when(playlistController).deletePlaylist(playlistId, user);

        Response responseFromService = service.deletePlaylistById(playlistId, token);

        Assert.assertEquals(responseStatus, responseFromService.getStatus());
    }

    @Test(expected = ForbiddenUserException.class)
    public void addPlaylistThrowsExceptionTest()
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        service.addPlaylist(invalidToken, new PlaylistRequest());
    }

    @Test
    public void successfulAddPlaylistTest()
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        Mockito.doReturn(new PlaylistResponse()).when(playlistController).addPlaylist(new PlaylistRequest(), user);

        Response responseFromService = service.addPlaylist(token, new PlaylistRequest());

        Assert.assertEquals(responseStatus, responseFromService.getStatus());
    }

    @Test(expected = ForbiddenUserException.class)
    public void updatePlaylistByIdThrowsExceptionTest()
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        service.updatePlaylistById(1, invalidToken, new Playlist());
    }

    @Test
    public void successfulUpdatePlaylistByIdTest()
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        Mockito.doReturn(new PlaylistResponse()).when(playlistController)
                .updatePlaylist(new Playlist(), user, playlistId);

        Response responseFromService = service.updatePlaylistById(1, token, new Playlist());

        Assert.assertEquals(responseStatus, responseFromService.getStatus());
    }

    @Test
    public void emptyConstructorTest() {
        service = new PlaylistService();
        Assert.assertNotNull(service);
    }

    @Test(expected = ForbiddenUserException.class)
    public void getAllTracksByPlaylistByIdThrowsExceptionTest()
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        service.getAllTracksByPlaylistById(playlistId, invalidToken);
    }

    @Test
    public void successfulGetAllTracksByPlaylistByIdTest()
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        Mockito.doReturn(new TrackResponse()).when(trackController).getAllTrackByPlaylistId(playlistId);

        Response responseFromService = service.getAllTracksByPlaylistById(playlistId, token);

        Assert.assertEquals(responseStatus, responseFromService.getStatus());
    }

    @Test(expected = ForbiddenUserException.class)
    public void deleteTrackInPlaylistByIdThrowsExceptionTest()
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        service.deleteTrackInPlaylistById(playlistId, invalidToken, 1);
    }

    @Test
    public void successfulDeleteTrackInPlaylistByIdTest()
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        Mockito.doReturn(new TrackResponse()).when(trackController).deleteTrackInPlaylist(playlistId, 1);

        Response responseFromService = service.deleteTrackInPlaylistById(playlistId, token, 1);

        Assert.assertEquals(responseStatus, responseFromService.getStatus());
    }

    @Test(expected = ForbiddenUserException.class)
    public void addTrackToPlaylistThrowsExceptionTest()
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        service.addTrackToPlaylist(playlistId, invalidToken, new TrackRequest());
    }

    @Test
    public void successfulAddTrackToPlaylistTest()
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        Mockito.doReturn(new TrackResponse()).when(trackController).addTrackToPlaylist(new TrackRequest(), playlistId);

        Response responseFromService = service.addTrackToPlaylist(playlistId, token, new TrackRequest());

        Assert.assertEquals(responseStatus, responseFromService.getStatus());
    }


}