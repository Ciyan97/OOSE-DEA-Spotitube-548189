package nl.han.oose.persistence.track;

import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;
import nl.han.oose.persistence.BaseDAO;
import nl.han.oose.domain.tracks.Track;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class TrackDAOImpl extends BaseDAO<Track> implements TrackDAO {

    public TrackDAOImpl() {
        super();
    }

    public TrackDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Track> getAll() throws DatabaseException, EntityNotFoundException {
        String hql = "from Track";
        return getListByIdAndHql(EMPTY, hql);
    }

    @Override
    public List<Track> getAllInPlaylist(int playlistId) throws DatabaseException, EntityNotFoundException {
        String hql = "select p.tracks from Playlist p where p.id = :id";
        return getListByIdAndHql(playlistId, hql);
    }

    @Override
    public List<Track> getAllExcludingPlaylist(int playlistId) throws DatabaseException, EntityNotFoundException {
        String hql = "select p.tracks from Playlist p where p.id != :id";
        return getListByIdAndHql(playlistId, hql);
    }

    @Override
    public Track getTrack(int id) throws DatabaseException, EntityNotFoundException {
        Session session = null;
        Track entity = null;

        try {
            session = getSessionFactory().openSession();
            entity = session.get(Track.class, id);
        } catch (Exception e) {
            handleException(e);
        } finally {
            closeSession(session);
        }

        if (entity == null) {
            throw new EntityNotFoundException(EntityNotFoundException.MESSAGE);
        }
        return entity;
    }

    @Override
    public void updateTrackIsOfflineAvailable(int id, boolean isOfflineAvailable) throws DatabaseException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Track track = (Track) session.get(Track.class, id);
            track.setOfflineAvailable(isOfflineAvailable);
            transaction.commit();
        } catch (Exception e) {
            handleExceptionWithTransaction(transaction, e);
        } finally {
            closeSession(session);
        }
    }
}
