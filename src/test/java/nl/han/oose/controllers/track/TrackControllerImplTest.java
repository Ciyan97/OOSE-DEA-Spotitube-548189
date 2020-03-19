package nl.han.oose.controllers.track;
import nl.han.oose.domain.Playlist;
import nl.han.oose.domain.User;
import nl.han.oose.domain.responses.TrackResponse;
import nl.han.oose.domain.tracks.Song;
import nl.han.oose.domain.tracks.Track;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;
import nl.han.oose.persistence.playlist.PlaylistDAO;
import nl.han.oose.persistence.track.TrackDAO;
import nl.han.oose.domain.requests.TrackRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TrackControllerImplTest {

    @Mock
    private PlaylistDAO playlistDAO;
    @Mock
    private TrackDAO trackDAO;

    private TrackControllerImpl trackController;

    private User user = new User("user", "password");
    private Track firstSong = new Song("Despacito", "Jason Derulo", 300,
            "https://www.youtube.com/watch?v=kJQP7kiw5Fk", false,
            "Best Meme Songs", 0);
    private Track secondSong = new Song("Thriller", "Michael Jackson", 275,
            "https://www.youtube.com/watch?v=sOnqjkJTMaA", false,
            "Best Meme Songs", 0);
    private ArrayList<Track> tracks = new ArrayList<Track>();
    private Playlist playlist;
    private int playlistId = 1;
    private int trackId = 1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        trackController = new TrackControllerImpl(playlistDAO, trackDAO);

        firstSong.setId(trackId);
        tracks.add(firstSong);
        tracks.add(secondSong);
        playlist = new Playlist("Mijn Coolste Playlist", user, tracks);
        playlist.setId(playlistId);
    }

    @Test
    public void getAllTrackByPlaylistIdTest() throws DatabaseException, EntityNotFoundException {
        int id = 1;

        trackController.getAllTrackByPlaylistId(id);

        verify(trackDAO).getAllInPlaylist(id);
    }

    @Test
    public void deleteTrackInPlaylistTest() throws DatabaseException, EntityNotFoundException {
        Mockito.doReturn(playlist).when(playlistDAO).getPlaylist(playlistId);
        Mockito.doReturn(tracks).when(trackDAO).getAllInPlaylist(playlistId);

        TrackResponse response = trackController.deleteTrackInPlaylist(playlistId, trackId);

        verify(playlistDAO).getPlaylist(playlistId);
        Assert.assertNotNull(response);
    }

    @Test
    public void addTrackToPlaylistTest() throws DatabaseException, EntityNotFoundException {
        TrackRequest request = new TrackRequest();
        request.setId(1);
        Mockito.doReturn(firstSong).when(trackDAO).getTrack(request.getId());
        Mockito.doReturn(playlist).when(playlistDAO).getPlaylist(playlistId);
        Mockito.doReturn(tracks).when(trackDAO).getAllInPlaylist(playlistId);

        trackController.addTrackToPlaylist(request, playlistId);

        verify(playlistDAO).getPlaylist(playlistId);
    }

    @Test
    public void getAllTracksTest() throws DatabaseException, EntityNotFoundException {
        trackController.getAllTracks();

        verify(trackDAO).getAll();
    }

    @Test
    public void getAllTracksNotInPlaylistTest() throws DatabaseException, EntityNotFoundException {
        Mockito.doReturn(tracks).when(trackDAO).getAll();
        Mockito.doReturn(tracks).when(trackDAO).getAllInPlaylist(playlistId);

        TrackResponse response = trackController.getAllTracksNotInPlaylist(playlistId);

        Assert.assertNotNull(response);
    }
}