package nl.han.oose.domain;

import nl.han.oose.domain.tracks.Track;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlaylistTest {
    private Playlist playlist;

    @Before
    public void setUp() {
        playlist = new Playlist();
    }

    @After
    public void tearDown() {
        playlist = null;
    }

    @Test
    public void getIdTest() {
        int id = 1;

        playlist.setId(id);

        assertEquals(id, playlist.getId());
    }

    @Test
    public void getNameTest() {
        String name = "test";

        playlist.setName(name);

        assertEquals(name, playlist.getName());
    }

    @Test
    public void getTracksTest() {
        playlist.setTracks(new ArrayList<Track>());

        assertTrue(playlist.getTracks().isEmpty());
    }
    @Test
    public void getOwnerTest() {
        playlist.setOwner(new User());

        assertNotNull(playlist.getOwner());
    }
}