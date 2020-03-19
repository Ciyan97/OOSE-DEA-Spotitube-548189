package nl.han.oose.exceptions;

public class ForbiddenUserException extends Exception {
    public static final String MESSAGE = "User has no access rights.";

    public ForbiddenUserException(String text) {
        super(text);
    }
}
