package ua.gmail.sydorenko.database;

import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author M.Sydorenko
 */
public interface DBManager {

    Connection getConnection() throws DaoSystemException;

    void commitAndClose(Connection connection) throws DaoSystemException;

    void rollbackAndClose(Connection connection) throws DaoSystemException;
}
