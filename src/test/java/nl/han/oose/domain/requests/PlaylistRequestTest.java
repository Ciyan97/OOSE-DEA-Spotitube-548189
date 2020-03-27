package nl.han.oose.domain.requests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlaylistRequestTest {
    private PlaylistRequest request;

    @Before
    public void setUp() throws Exception {
        request = new PlaylistRequest();
    }

    @Test
    public void settersTest() {
        request.setId(1);
        request.setName("test");
        request.setOwner(true);
        request.setTracks(null);

        Assert.assertNotNull(request);
    }
}