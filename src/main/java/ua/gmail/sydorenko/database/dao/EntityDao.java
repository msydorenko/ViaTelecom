package ua.gmail.sydorenko.database.dao;

import ua.gmail.sydorenko.database.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Entity;

import java.util.List;

/**
 * @author M.Sydorenko
 */
public interface EntityDao<T extends Entity> {

    /**
     * Get all entities.
     *
     * @return list of all entities.
     */
    List<T> readAll() throws DaoSystemException;

    /**
     * Get entity by id.
     *
     * @param id of entity object.
     * @return list of entity.
     */
    List<T> readById(int id) throws DaoSystemException;

    /**
     * Create new entity.
     *
     * @param t entity
     */
    void create(T t) throws DaoSystemException;

    /**
     * Update existing entity.
     *
     * * @param t entity
     */
    void update(T t) throws DaoSystemException;

    /**
     * Delete existing entity by id.
     *
     * @param id of entity for deleting.
     */
    void delete(int id) throws DaoSystemException;
}
