package nl.han.oose.persistence.playlist;

import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;
import nl.han.oose.persistence.BaseDAO;
import nl.han.oose.domain.Playlist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PlaylistDAOImpl extends BaseDAO<Playlist> implements PlaylistDAO {

    public PlaylistDAOImpl() {
        super();
    }

    public PlaylistDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Playlist> getAll(int userId) throws DatabaseException, EntityNotFoundException {
        String hql = "from Playlist p where p.owner.id = :id";
        return getListByIdAndHql(userId, hql);
    }

    @Override
    public void delete(int id) throws DatabaseException {
        String hql = "delete from Playlist as p where p.id = :id";
        delete(id, hql);
    }

    public Playlist getPlaylist(int id) throws DatabaseException, EntityNotFoundException {
        String hql = "from Playlist p where p.id = :id";
        return getByIdAndHql(id, hql);
    }

    @Override
    public void updateByName(Playlist entity) throws DatabaseException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Playlist playlist = (Playlist) session.get(Playlist.class, entity.getId());
            playlist.setName(entity.getName());
            transaction.commit();
        } catch (Exception e) {
            handleExceptionWithTransaction(transaction, e);
        } finally {
            closeSession(session);
        }
    };
}
