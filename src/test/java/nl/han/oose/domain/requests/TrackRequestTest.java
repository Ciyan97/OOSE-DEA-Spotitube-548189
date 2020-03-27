package nl.han.oose.domain.requests;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrackRequestTest {
    private TrackRequest request = new TrackRequest();

    @Test
    public void settersTest() {
        request.setTitle("test");
        request.setPerformer("test");
        request.setDuration(0);
        request.setOfflineAvailable(true);

        Assert.assertNotNull(request);
    }
}