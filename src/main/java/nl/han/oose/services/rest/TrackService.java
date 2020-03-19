package nl.han.oose.services.rest;

import com.google.gson.Gson;
import nl.han.oose.controllers.track.TrackController;
import nl.han.oose.domain.User;
import nl.han.oose.controllers.utils.UserMap;
import nl.han.oose.domain.responses.TrackResponse;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;
import nl.han.oose.exceptions.ForbiddenUserException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackService{
    private TrackController trackController;
    private Gson gson = new Gson();

    public TrackService() {}

    @Inject
    public TrackService(TrackController trackController) {
        this.trackController = trackController;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracks(@QueryParam("forPlaylist") int playlistId, @QueryParam("token") String token)
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        User user = UserMap.getUser(token);
        TrackResponse response = getTrackResponse(playlistId);
        String jsonResponse = gson.toJson(response);
        return Response.ok(jsonResponse).build();
    }

    private TrackResponse getTrackResponse(int playlistId) throws DatabaseException, EntityNotFoundException {
        if (playlistId != 0) {
            return trackController.getAllTracksNotInPlaylist(playlistId);
        } else {
            return trackController.getAllTracks();
        }
    }
}
