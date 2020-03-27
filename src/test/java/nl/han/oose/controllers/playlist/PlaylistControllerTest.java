package nl.han.oose.controllers.playlist;

import nl.han.oose.domain.responses.PlaylistResponse;
import nl.han.oose.domain.tracks.Song;
import nl.han.oose.domain.tracks.Track;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;
import nl.han.oose.persistence.playlist.PlaylistDAO;
import nl.han.oose.persistence.track.TrackDAO;
import nl.han.oose.domain.Playlist;
import nl.han.oose.domain.User;
import nl.han.oose.domain.requests.PlaylistRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistControllerTest {

    @Mock
    private PlaylistDAO playlistDAO;
    @Mock
    private TrackDAO trackDAO;

    private User user;
    private Playlist playlist;
    private Set<Track> tracks;
    private List<Playlist> playlists;
    private int playlistId;

    private PlaylistControllerImpl playlistController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        playlistController = new PlaylistControllerImpl(playlistDAO, trackDAO);

        user = new User("test", "test");
        user.setId(1);
        tracks = new HashSet<Track>();
        Track firstSong = new Song("Despacito", "Jason Derulo", 300,
                "https://www.youtube.com/watch?v=kJQP7kiw5Fk", false, playlist,
                "Best Meme Songs", 0);
        tracks.add(firstSong);
        playlist = new Playlist("test", user, tracks);
        playlist.setId(1);
        playlists = new ArrayList<Playlist>();
        playlists.add(playlist);
        playlistId = 1;
    }

    @Test
    public void successfulGetAllPlaylistsTest() throws DatabaseException, EntityNotFoundException {
        Mockito.doReturn(playlists).when(playlistDAO).getAll(user.getId());
        Mockito.doReturn(tracks).when(trackDAO).getAllInPlaylist(playlist.getId());
        Set<Track> tracksInPlaylist = trackDAO.getAllInPlaylist(playlist.getId());

        PlaylistResponse responseFromController = playlistController.getAllPlaylists(user);

        verify(playlistDAO).getAll(user.getId());
        Assert.assertEquals(playlists, responseFromController.getPlaylists());
    }

    @Test
    public void successfulDeletePlaylistTest() throws DatabaseException, EntityNotFoundException {
        Mockito.doReturn(tracks).when(trackDAO).getAllInPlaylist(playlistId);

        playlistController.deletePlaylist(playlistId, user);

        verify(playlistDAO).delete(playlistId);
        verify(playlistDAO).getAll(user.getId());
    }

    @Test
    public void successfulAddPlaylistTest() throws DatabaseException, EntityNotFoundException {
        PlaylistRequest request = new PlaylistRequest();

        playlistController.addPlaylist(request, user);

        verify(playlistDAO).getAll(user.getId());
    }

    @Test
    public void successfulUpdatePlaylistTest() throws DatabaseException, EntityNotFoundException {
        playlistController.updatePlaylist(playlist, user, playlistId);

        verify(playlistDAO).updateByName(playlist);
        verify(playlistDAO).getAll(user.getId());
    }
}