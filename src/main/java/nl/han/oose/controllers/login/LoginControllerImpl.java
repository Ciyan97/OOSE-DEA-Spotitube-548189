package nl.han.oose.controllers.login;

import nl.han.oose.controllers.utils.TokenGenerator;
import nl.han.oose.controllers.utils.UserMap;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.persistence.user.UserDAO;
import nl.han.oose.domain.User;
import nl.han.oose.domain.responses.LoginResponse;
import nl.han.oose.exceptions.UserDoesNotExistException;

import javax.inject.Inject;

public class LoginControllerImpl implements LoginController {
    private UserDAO userDAO;

    @Inject
    public LoginControllerImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public LoginResponse getUser(User user) throws UserDoesNotExistException, DatabaseException {
        user = userDAO.get(user.getUser(), user.getPassword());
        LoginResponse response = new LoginResponse(TokenGenerator.generateToken(), user.getUser());
        setUserMap(response.getToken(), user);
        return response;
    }

    private void setUserMap(String token, User user) {
        UserMap.getInstance().put(token, user);
    }
}
