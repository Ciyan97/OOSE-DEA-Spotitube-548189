package nl.han.oose.persistence.track;

import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;
import nl.han.oose.persistence.BaseDAO;
import nl.han.oose.domain.tracks.Track;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

public class TrackDAOImpl extends BaseDAO<Track> implements TrackDAO {

    public TrackDAOImpl() {
        super();
    }

    public TrackDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Set<Track> getAll() throws DatabaseException, EntityNotFoundException {
        String hql = "from Track";
        return getSetByIdAndHql(EMPTY, hql);
    }

    @Override
    public Set<Track> getAllInPlaylist(int playlistId) throws DatabaseException, EntityNotFoundException {
        String hql = "select p.tracks from Playlist p where p.id = :id";
        return getSetByIdAndHql(playlistId, hql);
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

    public List<String> getTrackNamesInPlaylist(int playlistId) throws DatabaseException, EntityNotFoundException {
        String hql = "select t.title from Playlist p inner join p.tracks t where p.id = :playlistId";
        Session session = null;
        List<String> list = null;

        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery(hql);
            query.setParameter("playlistId", playlistId);
            list = query.list();
        } catch (Exception e) {
            handleException(e);
        } finally {
            closeSession(session);
        }

        if (list == null) {
            throw new EntityNotFoundException(EntityNotFoundException.MESSAGE);
        }
        return list;
    }

    @Override
    public void delete(int id) throws DatabaseException {
        String hql = "delete from Track as t where t.id = :id";
        delete(id, hql);
    }
}
