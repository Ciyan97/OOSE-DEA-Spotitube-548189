package nl.han.oose.persistence.track;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;
import nl.han.oose.persistence.DAO;
import nl.han.oose.domain.tracks.Track;

import java.util.List;
import java.util.Set;

public interface TrackDAO extends DAO<Track> {
    Set<Track> getAll() throws DatabaseException, EntityNotFoundException;
    Set<Track> getAllInPlaylist(int playlistId) throws DatabaseException, EntityNotFoundException;
    List<String> getTrackNamesInPlaylist(int playlistId) throws DatabaseException, EntityNotFoundException;
    Track getTrack(int id) throws DatabaseException, EntityNotFoundException;
    void delete(int id) throws DatabaseException;
}
