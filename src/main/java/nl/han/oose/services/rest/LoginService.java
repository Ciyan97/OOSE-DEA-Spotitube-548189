package nl.han.oose.services.rest;

import com.google.gson.Gson;

import nl.han.oose.controllers.login.LoginController;
import nl.han.oose.domain.User;
import nl.han.oose.domain.responses.LoginResponse;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.UserDoesNotExistException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginService {
    private LoginController controller;
    private Gson gson = new Gson();

    public LoginService() {}

    @Inject
    public LoginService(LoginController controller) {
        this.controller = controller;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(User user) throws UserDoesNotExistException, DatabaseException {
        if (user == null) {
            throw new UserDoesNotExistException(UserDoesNotExistException.MESSAGE);
        }
        LoginResponse response = controller.getUser(user);
        return Response.ok(gson.toJson(response)).build();
    }
}
