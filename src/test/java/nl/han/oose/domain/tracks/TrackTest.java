package nl.han.oose.domain.tracks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class TrackTest {

    Track track;

    @Before
    public void setUp() {
        track = new Song();
    }

    @After
    public void tearDown() {
        track = null;
    }

    @Test
    public void getIdTest() {
        int id = 1;

        track.setId(id);

        assertEquals(id, track.getId());
    }

    @Test
    public void getTitleTest() {
        String title = "test";

        track.setTitle(title);

        assertEquals(title, track.getTitle());
    }

    @Test
    public void getPerformerTest() {
        String performer = "test";

        track.setPerformer(performer);

        assertEquals(performer, track.getPerformer());
    }

    @Test
    public void getDurationTest() {
        int duration = 1;

        track.setDuration(duration);

        assertEquals(duration, track.getDuration());
    }

    @Test
    public void getUrlTest() {
        String url = "test";

        track.setUrl(url);

        assertEquals(url, track.getUrl());
    }

    @Test
    public void isOfflineAvailableTest() {
        track.setOfflineAvailable(false);

        assertFalse(track.isOfflineAvailable());
    }
}