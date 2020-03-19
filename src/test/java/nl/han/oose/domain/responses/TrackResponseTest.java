package nl.han.oose.domain.responses;
import nl.han.oose.domain.responses.TrackResponse;
import nl.han.oose.domain.tracks.Track;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class TrackResponseTest {

    @Test
    public void getTracksTest() {
        TrackResponse trackResponse = new TrackResponse();

        trackResponse.setTracks(new ArrayList<Track>());

        assertTrue(trackResponse.getTracks().isEmpty());
    }
}