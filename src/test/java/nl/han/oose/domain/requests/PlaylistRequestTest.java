package nl.han.oose.domain.requests;

import nl.han.oose.domain.tracks.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlaylistRequestTest {
    private PlaylistRequest playlistRequest;

    @Before
    public void setUp() {
        playlistRequest = new PlaylistRequest();
    }

    @After
    public void tearDown() {
        playlistRequest = null;
    }

    @Test
    public void getIdTest() {
        int id = 1;

        playlistRequest.setId(id);

        assertEquals(id, playlistRequest.getId());
    }

    @Test
    public void getNameTest() {
        String name = "test";

        playlistRequest.setName(name);

        assertEquals(name, playlistRequest.getName());
    }

    @Test
    public void getTracksTest() {
        ArrayList<Track> tracks = new ArrayList<Track>();

        playlistRequest.setTracks(tracks);

        assertTrue(playlistRequest.getTracks().isEmpty());
    }
}