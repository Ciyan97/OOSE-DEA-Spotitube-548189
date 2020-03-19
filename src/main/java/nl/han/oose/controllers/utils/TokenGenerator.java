package nl.han.oose.controllers.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class TokenGenerator {

    public static String generateToken() {
        int length = 16;
        int value = 4;
        String generatedString = RandomStringUtils.random(length, false, true);
        return generatedString
                .replaceAll("(.{" + value + "})", "$0-")
                .trim()
                .substring(0, 19);
    }
}
