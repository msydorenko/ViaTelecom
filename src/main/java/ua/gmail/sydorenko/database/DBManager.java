package ua.gmail.sydorenko.database;

import ua.gmail.sydorenko.database.exception.DaoSystemException;

import java.sql.Connection;

/**
 * Interface for getting connection to database.
 *
 * @author M.Sydorenko
 */
public interface DBManager {

    /**
     * Get a connection.
     *
     * @return connection.
     * @throws DaoSystemException
     */
    Connection getConnection() throws DaoSystemException;

    /**
     * Commit changes to database and close
     *
     * @param connection
     * @throws DaoSystemException
     */
    void commitAndClose(Connection connection) throws DaoSystemException;

    /**
     * Roll back changes in database and close.
     *
     * @param connection
     * @throws DaoSystemException
     */
    void rollbackAndClose(Connection connection) throws DaoSystemException;
}
