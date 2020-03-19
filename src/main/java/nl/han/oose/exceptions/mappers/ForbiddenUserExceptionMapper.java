package nl.han.oose.exceptions.mappers;

import nl.han.oose.exceptions.ForbiddenUserException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ForbiddenUserExceptionMapper implements ExceptionMapper<ForbiddenUserException> {

    @Override
    public Response toResponse(ForbiddenUserException e) {
        return Response
                .status(Response.Status.FORBIDDEN)
                .entity(e.getMessage())
                .build();
    }
}
