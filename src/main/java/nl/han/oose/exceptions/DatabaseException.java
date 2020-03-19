package nl.han.oose.exceptions;

public class DatabaseException extends Exception {
    public static final String MESSAGE = "An error occurred in the database call.";

    public DatabaseException(String message){
        super(message);
    }
}
