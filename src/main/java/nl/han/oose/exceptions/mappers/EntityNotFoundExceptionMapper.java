package nl.han.oose.exceptions.mappers;

import nl.han.oose.exceptions.EntityNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {

    @Override
    public Response toResponse(EntityNotFoundException e) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(e.getMessage())
                .build();
    }
}
