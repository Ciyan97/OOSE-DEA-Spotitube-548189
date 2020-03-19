package nl.han.oose.controllers.utils;
import nl.han.oose.controllers.utils.TokenGenerator;
import org.junit.Test;

import static org.junit.Assert.*;
public class TokenGeneratorTest {

    @Test
    public void shouldGenerateTokenTest() {
        String token = TokenGenerator.generateToken();

        assertNotNull(token);
        assertNotEquals(token, TokenGenerator.generateToken());
    }
}