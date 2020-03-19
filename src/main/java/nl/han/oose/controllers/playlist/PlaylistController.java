package nl.han.oose.controllers.playlist;
import nl.han.oose.domain.Playlist;
import nl.han.oose.domain.User;
import nl.han.oose.domain.requests.PlaylistRequest;
import nl.han.oose.domain.responses.PlaylistResponse;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;

public interface PlaylistController {
    PlaylistResponse getAllPlaylists(User user) throws DatabaseException, EntityNotFoundException;
    PlaylistResponse deletePlaylist(int id, User user) throws DatabaseException, EntityNotFoundException;
    PlaylistResponse addPlaylist(PlaylistRequest requestBody, User user) throws DatabaseException, EntityNotFoundException;
    PlaylistResponse updatePlaylist(Playlist requestBody, User user, int id) throws DatabaseException, EntityNotFoundException;
}
