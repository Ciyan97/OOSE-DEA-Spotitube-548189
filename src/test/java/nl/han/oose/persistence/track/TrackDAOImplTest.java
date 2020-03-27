package nl.han.oose.persistence.track;

import nl.han.oose.domain.tracks.Track;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;
import nl.han.oose.persistence.BasePersistenceIntegrationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class TrackDAOImplTest extends BasePersistenceIntegrationTest {

    private TrackDAO trackDAO;
    private int playlistId = 2;
    private int trackId = 3;

    @Before
    public void setUp() throws Exception {
        trackDAO = new TrackDAOImpl(newSessionFactory());
        fillDatabase();
    }

    @Test
    public void successfulGetAllTest() throws DatabaseException, EntityNotFoundException {
        int expected = 5;

        Set<Track> tracks = trackDAO.getAll();

        Assert.assertFalse(tracks.isEmpty());
        Assert.assertEquals(expected, tracks.size());
    }

    @Test
    public void successfulGetAllInPlaylistTest() throws DatabaseException, EntityNotFoundException {
        Set<Track> tracks = trackDAO.getAllInPlaylist(playlistId);

        Assert.assertFalse(tracks.isEmpty());
    }

    @Test
    public void successfulGetTrackTest() throws DatabaseException, EntityNotFoundException {
        Track track = trackDAO.getTrack(trackId);

        Assert.assertNotNull(track);
    }

    @Test
    public void successfulGetTrackNamesInPlaylistTest() throws DatabaseException, EntityNotFoundException {
        List<String> names = trackDAO.getTrackNamesInPlaylist(playlistId);

        Assert.assertNotNull(names.get(0));
    }

    @Test(expected = DatabaseException.class)
    public void deleteTestThrowsException() throws DatabaseException, EntityNotFoundException {
        trackDAO.delete(trackId);

        Track track = trackDAO.getTrack(trackId);
    }
}