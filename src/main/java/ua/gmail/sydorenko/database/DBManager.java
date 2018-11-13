package ua.gmail.sydorenko.database;

import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBManager {

    Connection getConnection() throws DaoSystemException, SQLException;

    void commitAndClose(Connection connection) throws DaoSystemException;

    void rollbackAndClose(Connection connection) throws DaoSystemException;
}
