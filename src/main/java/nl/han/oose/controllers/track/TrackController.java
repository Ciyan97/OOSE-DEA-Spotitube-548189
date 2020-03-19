package nl.han.oose.controllers.track;
import nl.han.oose.domain.requests.TrackRequest;
import nl.han.oose.domain.responses.TrackResponse;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;

public interface TrackController {
    TrackResponse getAllTrackByPlaylistId(int id) throws DatabaseException, EntityNotFoundException;
    TrackResponse deleteTrackInPlaylist(int playlistId, int trackId) throws DatabaseException, EntityNotFoundException;
    TrackResponse addTrackToPlaylist(TrackRequest requestBody, int id) throws DatabaseException, EntityNotFoundException;
    TrackResponse getAllTracks() throws DatabaseException, EntityNotFoundException;
    TrackResponse getAllTracksNotInPlaylist(int id) throws DatabaseException, EntityNotFoundException;
}
