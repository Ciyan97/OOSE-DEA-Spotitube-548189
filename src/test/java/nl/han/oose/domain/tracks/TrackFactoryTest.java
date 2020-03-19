package nl.han.oose.domain.tracks;

import nl.han.oose.domain.requests.TrackRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrackFactoryTest {

    @Test
    public void getInstanceTest() {
        TrackFactory factory = TrackFactory.getInstance();

        assertNotNull(factory);
    }

    @Test
    public void createSongTest() throws IllegalArgumentException {
        TrackFactory factory = TrackFactory.getInstance();

        Track song = factory.create("SONG", new TrackRequest());

        assertNotNull(song);
    }

    @Test
    public void createVideoTest() throws IllegalArgumentException {
        TrackFactory factory = TrackFactory.getInstance();

        Track video = factory.create("VIDEO", new TrackRequest());

        assertNotNull(video);
    }

    @Test(expected = IllegalArgumentException.class)
    public void factoryThrowsExceptionTest(){
        TrackFactory factory = TrackFactory.getInstance();

        Track video = factory.create("NULL", new TrackRequest());
    }
}