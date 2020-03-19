package nl.han.oose.controllers.utils;

import nl.han.oose.domain.User;
import nl.han.oose.exceptions.ForbiddenUserException;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class UserMap {

    private static HashMap<String, User> userMap;

    public synchronized static HashMap<String, User> getInstance() {
        if (UserMap.userMap == null) {
            UserMap.userMap = new HashMap<String, User>();
        }
        return UserMap.userMap;
    }

    public static User getUser(String token) throws ForbiddenUserException {
        User user = UserMap.getInstance().get(token);
        if (user == null) {
            throw new ForbiddenUserException(ForbiddenUserException.MESSAGE);
        }
        return user;
    }
}
