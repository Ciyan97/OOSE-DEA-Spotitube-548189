package nl.han.oose.persistence.playlist;

import nl.han.oose.domain.Playlist;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;
import nl.han.oose.persistence.BasePersistenceIntegrationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PlaylistDAOImplTest extends BasePersistenceIntegrationTest {

    private PlaylistDAO playlistDAO;

    //we use 2 cause it is the second inserted item.
    private int id = 2;

    @Before
    public void setUp() throws Exception {
        playlistDAO = new PlaylistDAOImpl(newSessionFactory());
        fillDatabase();
    }

    @Test
    public void successfulGetAllTest() throws DatabaseException, EntityNotFoundException {
        int length = 1;
        int userId = 1;
        List<Playlist> playlists = playlistDAO.getAll(userId);

        Assert.assertFalse(playlists.isEmpty());
        Assert.assertEquals(length, playlists.size());
    }

    @Test(expected = DatabaseException.class)
    public void deleteTestThrowsException() throws DatabaseException, EntityNotFoundException {
        playlistDAO.delete(id);
        Playlist playlist = playlistDAO.getPlaylist(id);
    }

    @Test
    public void successfulGetPlaylistTest() throws DatabaseException, EntityNotFoundException {
        Playlist playlist = playlistDAO.getPlaylist(id);

        Assert.assertNotNull(playlist);
    }

    @Test
    public void successfulUpdateByNameTest() throws DatabaseException, EntityNotFoundException {
        String name = "test";
        Playlist playlist = playlistDAO.getPlaylist(id);
        playlist.setName(name);

        playlistDAO.updateByName(playlist);
        Playlist newPlaylist = playlistDAO.getPlaylist(id);

        Assert.assertEquals(name, newPlaylist.getName());
    }
}