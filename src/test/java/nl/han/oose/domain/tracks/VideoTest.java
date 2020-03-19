package nl.han.oose.domain.tracks;

import nl.han.oose.domain.requests.TrackRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VideoTest {

    private Video video;

    @Before
    public void setUp() {
        video = new Video();
    }

    @After
    public void tearDown() {
        video = null;
    }

    @Test
    public void createFromRequestBodyTest() {
        Track video = Video.createFromRequestBody(new TrackRequest());

        assertNotNull(video);
    }

    @Test
    public void getPublicationDataTest() {
        String date = "yymmdd";

        video.setPublicationData(date);

        assertEquals(date, video.getPublicationData());
    }
    @Test
    public void getDescriptionTest() {
        String description = "test";

        video.setDescription(description);

        assertEquals(description, video.getDescription());
    }
}