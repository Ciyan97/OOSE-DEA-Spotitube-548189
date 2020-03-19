package nl.han.oose.controllers.utils;
import nl.han.oose.controllers.utils.UserMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class UserMapTest {
    @Before
    public void setUp() throws Exception {
        UserMap.getInstance().clear();
    }

    @Test
    public void shouldGetEmptyInstanceTest() {
        assertTrue(UserMap.getInstance().isEmpty());
    }
}