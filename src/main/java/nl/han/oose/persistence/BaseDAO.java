package nl.han.oose.persistence;

import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;
import nl.han.oose.persistence.configuration.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public abstract class BaseDAO<T> implements DAO<T>{
    protected static final int EMPTY = -1;
    private SessionFactory sessionFactory;

    protected BaseDAO() {}

    protected BaseDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(T entity) throws DatabaseException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            handleExceptionWithTransaction(transaction, e);
        } finally {
            closeSession(session);
        }
    };

    public List<T> getListByIdAndHql(int id, String hql) throws DatabaseException, EntityNotFoundException {
        Session session = null;
        List<T> list = null;

        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery(hql);
            if (id != EMPTY) query.setParameter("id", id);
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

    public T getByIdAndHql(int id, String hql) throws DatabaseException, EntityNotFoundException {
        Session session = null;
        T entity = null;

        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            entity = (T) query.uniqueResult();
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

    public void update(T entity) throws DatabaseException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            handleExceptionWithTransaction(transaction, e);
        } finally {
            closeSession(session);
        }
    };

    public void delete(int id, String hql) throws DatabaseException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();;
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            handleExceptionWithTransaction(transaction, e);
        } finally {
            closeSession(session);
        }
    };

    protected SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = HibernateUtil.getSessionFactory();
        }
        return sessionFactory;
    }

    protected void handleException(Exception e) throws DatabaseException {
        e.printStackTrace();
        throw new DatabaseException(DatabaseException.MESSAGE);
    }

    protected void handleExceptionWithTransaction(Transaction transaction, Exception e) throws DatabaseException {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
        throw new DatabaseException(DatabaseException.MESSAGE);
    }

    protected void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
