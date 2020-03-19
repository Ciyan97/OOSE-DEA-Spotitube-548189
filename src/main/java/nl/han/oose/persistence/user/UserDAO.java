package nl.han.oose.persistence.user;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.UserDoesNotExistException;
import nl.han.oose.persistence.DAO;
import nl.han.oose.domain.User;

public interface UserDAO extends DAO<User> {
    User get(String name, String password) throws UserDoesNotExistException, DatabaseException;
}
