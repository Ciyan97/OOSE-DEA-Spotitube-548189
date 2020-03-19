package nl.han.oose.persistence.track;

import nl.han.oose.domain.tracks.Track;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;
import nl.han.oose.persistence.BasePersistenceIntegrationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TrackDAOImplTest extends BasePersistenceIntegrationTest {

    private TrackDAO trackDAO;
    private int playlistId = 8;
    private int trackId = 2;

    @Before
    public void setUp() throws Exception {
        trackDAO = new TrackDAOImpl(newSessionFactory());
        fillDatabase();
    }

    @Test
    public void getAllTest() throws DatabaseException, EntityNotFoundException {
        int expected = 6;

        List<Track> tracks = trackDAO.getAll();

        Assert.assertFalse(tracks.isEmpty());
        Assert.assertEquals(expected, tracks.size());
    }

    @Test
    public void getAllInPlaylistTest() throws DatabaseException, EntityNotFoundException {
        List<Track> tracks = trackDAO.getAllInPlaylist(playlistId);

        Assert.assertFalse(tracks.isEmpty());
        Assert.assertNotNull(tracks.get(0));
    }

    @Test
    public void getAllExcludingPlaylistTest() throws DatabaseException, EntityNotFoundException {
        List<Track> tracks = trackDAO.getAllExcludingPlaylist(playlistId);

        Assert.assertTrue(tracks.isEmpty());
    }

    @Test
    public void getTrackTest() throws DatabaseException, EntityNotFoundException {
        Track track = trackDAO.getTrack(trackId);

        Assert.assertNotNull(track);
    }

    @Test
    public void updateTrackIsOfflineAvailableTest() throws DatabaseException, EntityNotFoundException {
        trackDAO.updateTrackIsOfflineAvailable(trackId, true);

        Track track = trackDAO.getTrack(trackId);

        Assert.assertTrue(track.isOfflineAvailable());
    }
}