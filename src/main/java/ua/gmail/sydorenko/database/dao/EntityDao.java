package ua.gmail.sydorenko.database.dao;

import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Entity;

import java.util.List;

/**
 * @author M.Sydorenko
 */
public interface EntityDao<T extends Entity> {
    List<T> readAll() throws DaoSystemException;

    List<T> readById(int id) throws DaoSystemException;

    void create(T t) throws DaoSystemException;

    void update(T t) throws DaoSystemException;

    void delete(int id) throws DaoSystemException;
}
