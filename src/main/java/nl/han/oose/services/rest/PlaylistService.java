package nl.han.oose.services.rest;

import nl.han.oose.controllers.track.TrackController;
import nl.han.oose.domain.requests.TrackRequest;
import nl.han.oose.domain.responses.TrackResponse;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;
import nl.han.oose.exceptions.ForbiddenUserException;
import nl.han.oose.controllers.playlist.PlaylistController;
import nl.han.oose.controllers.utils.UserMap;
import nl.han.oose.domain.Playlist;
import nl.han.oose.domain.User;
import nl.han.oose.domain.requests.PlaylistRequest;
import nl.han.oose.domain.responses.PlaylistResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/playlists")
public class PlaylistService {
    private PlaylistController playlistController;
    private TrackController trackController;
    private Gson gson = new Gson();

    public PlaylistService() {}

    @Inject
    public PlaylistService(PlaylistController playlistController, TrackController trackController) {
        this.playlistController = playlistController;
        this.trackController = trackController;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token)
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        User user = UserMap.getUser(token);
        PlaylistResponse response = playlistController.getAllPlaylists(user);
        String jsonResponse = gson.toJson(response);
        return Response.ok(jsonResponse).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylistById(@PathParam("id")int id, @QueryParam("token") String token)
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        User user = UserMap.getUser(token);
        PlaylistResponse response = playlistController.deletePlaylist(id, user);
        String jsonResponse = gson.toJson(response);
        return Response.ok(jsonResponse).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlaylistRequest requestBody)
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        User user = UserMap.getUser(token);
        PlaylistResponse response = playlistController.addPlaylist(requestBody, user);
        String jsonResponse = gson.toJson(response);
        return Response.ok(jsonResponse).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePlaylistById(@PathParam("id")int id, @QueryParam("token") String token,
                                       Playlist requestBody)
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        User user = UserMap.getUser(token);
        PlaylistResponse response = playlistController.updatePlaylist(requestBody, user, id);
        String jsonResponse = gson.toJson(response);
        return Response.ok(jsonResponse).build();
    }

    @GET
    @Path("{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksByPlaylistById(@PathParam("id")int id, @QueryParam("token") String token)
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        User user = UserMap.getUser(token);
        TrackResponse response = trackController.getAllTrackByPlaylistId(id);
        String jsonResponse = gson.toJson(response);
        return Response.ok(jsonResponse).build();
    }

    @POST
    @Path("{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@PathParam("id")int id, @QueryParam("token") String token,
                                       TrackRequest requestBody)
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        User user = UserMap.getUser(token);
        TrackResponse response = trackController.addTrackToPlaylist(requestBody, id);
        String jsonResponse = gson.toJson(response);
        return Response.ok(jsonResponse).build();
    }

    @DELETE
    @Path("{id}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackInPlaylistById(@PathParam("id")int playlistId,
                                              @QueryParam("token") String token,
                                              @PathParam("trackId") int trackId)
            throws ForbiddenUserException, DatabaseException, EntityNotFoundException {

        User user = UserMap.getUser(token);
        TrackResponse response = trackController.deleteTrackInPlaylist(playlistId, trackId);
        String jsonResponse = gson.toJson(response);
        return Response.ok(jsonResponse).build();
    }
}
