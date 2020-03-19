package nl.han.oose.domain.requests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrackRequestTest {

    private TrackRequest trackRequest;

    @Before
    public void setUp() {
        trackRequest = new TrackRequest();
    }

    @After
    public void tearDown() {
        trackRequest = null;
    }

    @Test
    public void getTypeWithAlbumTest() {
        trackRequest.setAlbum("test");

        String type = trackRequest.getType();

        Assert.assertEquals(type, "SONG");
    }

    @Test
    public void getTypeWithVideoTest() {
        trackRequest.setAlbum(null);

        String type = trackRequest.getType();

        Assert.assertEquals(type, "VIDEO");
    }


    @Test
    public void getIdTest() {
        int id = 1;

        trackRequest.setId(id);

        assertEquals(id, trackRequest.getId());
    }

    @Test
    public void getTitleTest() {
        String title = "test";

        trackRequest.setTitle(title);

        assertEquals(title, trackRequest.getTitle());
    }

    @Test
    public void getPerformerTest() {
        String performer = "test";

        trackRequest.setPerformer(performer);

        assertEquals(performer, trackRequest.getPerformer());
    }

    @Test
    public void getDurationTest() {
        int duration = 1;

        trackRequest.setDuration(duration);

        assertEquals(duration, trackRequest.getDuration());
    }

    @Test
    public void getAlbumTest() {
        String album = "test";

        trackRequest.setAlbum(album);

        assertEquals(album, trackRequest.getAlbum());
    }

    @Test
    public void getPlaycountTest() {
        int playcount = 1;

        trackRequest.setPlaycount(playcount);

        assertEquals(playcount, trackRequest.getPlaycount());
    }

    @Test
    public void getPublicationDateTest() {
        String date = "date";

        trackRequest.setPublicationDate(date);

        assertEquals(date, trackRequest.getPublicationDate());
    }

    @Test
    public void getDescriptionTest() {
        String description = "test";

        trackRequest.setDescription(description);

        assertEquals(trackRequest.getDescription(), description);
    }

    @Test
    public void isOfflineAvailableTest() {
        trackRequest.setOfflineAvailable(false);

        assertFalse(trackRequest.isOfflineAvailable());
    }

}