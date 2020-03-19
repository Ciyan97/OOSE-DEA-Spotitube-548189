package nl.han.oose.persistence.user;

import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.UserDoesNotExistException;
import nl.han.oose.persistence.BaseDAO;
import nl.han.oose.domain.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

    public UserDAOImpl() {
        super();
    }

    public UserDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User get(String username, String password) throws UserDoesNotExistException, DatabaseException {
        String hql = "select u from User u where u.user = :user and u.password = :password";
        Session session = null;
        User user = null;

        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery(hql);
            query.setParameter("user", username);
            query.setParameter("password", username);
            user = (User) query.uniqueResult();
        } catch (Exception e) {
            handleException(e);
        } finally {
            closeSession(session);
        }

        if (user == null) {
            throw new UserDoesNotExistException(UserDoesNotExistException.MESSAGE);
        }
        return user;
    }
}
