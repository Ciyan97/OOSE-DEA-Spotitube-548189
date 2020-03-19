package nl.han.oose.exceptions;

public class EntityNotFoundException extends Exception {
    public static final String MESSAGE = "Entity not found in the database.";

    public EntityNotFoundException(String message) {
        super(message);
    }
}
