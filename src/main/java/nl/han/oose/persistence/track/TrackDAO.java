package nl.han.oose.persistence.track;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;
import nl.han.oose.persistence.DAO;
import nl.han.oose.domain.tracks.Track;

import java.util.List;

public interface TrackDAO extends DAO<Track> {
    List<Track> getAll() throws DatabaseException, EntityNotFoundException;
    List<Track> getAllInPlaylist(int playlistId) throws DatabaseException, EntityNotFoundException;
    List<Track> getAllExcludingPlaylist(int playlistId) throws DatabaseException, EntityNotFoundException;
    Track getTrack(int id) throws DatabaseException, EntityNotFoundException;
    void updateTrackIsOfflineAvailable(int id, boolean isOfflineAvailable) throws DatabaseException;
}
