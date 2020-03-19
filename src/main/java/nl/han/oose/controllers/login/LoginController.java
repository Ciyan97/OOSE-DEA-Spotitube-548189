package nl.han.oose.controllers.login;
import nl.han.oose.domain.User;
import nl.han.oose.domain.responses.LoginResponse;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.UserDoesNotExistException;

public interface LoginController {
    LoginResponse getUser(User user) throws UserDoesNotExistException, DatabaseException;
}
