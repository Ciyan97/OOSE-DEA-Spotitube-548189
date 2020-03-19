package nl.han.oose.persistence.playlist;
import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;
import nl.han.oose.persistence.DAO;
import nl.han.oose.domain.Playlist;

import java.util.Date;
import java.util.List;

public interface PlaylistDAO extends DAO<Playlist> {
    List<Playlist> getAll(int userId) throws DatabaseException, EntityNotFoundException;
    void delete(int id) throws DatabaseException;
    Playlist getPlaylist(int id) throws DatabaseException, EntityNotFoundException;
    void updateByName(Playlist entity) throws DatabaseException;
}
