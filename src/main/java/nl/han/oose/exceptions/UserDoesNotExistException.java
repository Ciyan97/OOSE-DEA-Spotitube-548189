package nl.han.oose.exceptions;

public class UserDoesNotExistException extends Exception {
    public static final String MESSAGE = "User can't be found.";

    public UserDoesNotExistException(String text) {
        super(text);
    }
}
