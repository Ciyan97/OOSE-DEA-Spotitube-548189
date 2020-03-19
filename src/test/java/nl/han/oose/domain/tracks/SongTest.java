package nl.han.oose.domain.tracks;

import nl.han.oose.domain.requests.TrackRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SongTest {

    private Song song;

    @Before
    public void setUp() {
        song = new Song();
    }

    @After
    public void tearDown() {
        song = null;
    }

    @Test
    public void createFromRequestBodyTest() {
        Track song = Song.createFromRequestBody(new TrackRequest());

        assertNotNull(song);
    }

    @Test
    public void getAlbumTest() {
        String name = "test";

        song.setAlbum(name);

        assertEquals(name, song.getAlbum());
    }

    @Test
    public void getPlaycountTest() {
        int playcount = 1;

        song.setPlaycount(playcount);

        assertEquals(playcount, song.getPlaycount());
    }
}