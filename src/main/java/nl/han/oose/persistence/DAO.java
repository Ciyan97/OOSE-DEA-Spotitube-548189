package nl.han.oose.persistence;

import nl.han.oose.exceptions.DatabaseException;
import nl.han.oose.exceptions.EntityNotFoundException;

import java.util.List;

public interface DAO<T> {
    void save(T entity) throws DatabaseException;
    void update(T entity) throws DatabaseException;
    void delete(int id, String hql) throws DatabaseException;
    List<T> getListByIdAndHql(int id, String hql) throws DatabaseException, EntityNotFoundException;
    T getByIdAndHql(int id, String hql) throws DatabaseException, EntityNotFoundException;
}
