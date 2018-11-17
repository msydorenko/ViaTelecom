package ua.gmail.sydorenko.database;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;

import javax.sql.DataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author M.Sydorenko
 */
public class MySQLManager implements DBManager {
    private static final Logger LOG = Logger.getLogger(DBManager.class);

    /**
     * Method establish connection to database
     *
     * @return Connection
     */
    @Override
    public Connection getConnection() throws DaoSystemException {
        Connection con = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/ViatelecomDB");
            con = ds.getConnection();
        } catch (NamingException ex) {
            LOG.error("Cannot obtain a connection from the pool", ex);
            ex.printStackTrace();
        } catch (SQLException e) {
            LOG.error("Cannot obtain a connection from the pool", e);
            throw new DaoSystemException("Cannot obtain a connection from the pool", e);
        }
        return con;
    }

    /**
     * Method to commit and close connection
     *
     * @param connection
     * @throws DaoSystemException throws if connection wasn't close
     */
    @Override
    public void commitAndClose(Connection connection) throws DaoSystemException {
        try {
            connection.commit();
            connection.close();
        } catch (Exception e) {
            LOG.error("Cannot close connection! ", e);
            throw new DaoSystemException("Cannot close connection! ", e);
        }
    }

    /**
     * Method to rollback and close connection
     *
     * @param connection
     * @throws DaoSystemException throws if connection wasn't close
     */
    @Override
    public void rollbackAndClose(Connection connection) throws DaoSystemException {
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
            LOG.error("Cannot close connection! ", e);
            throw new DaoSystemException("Cannot close connection! ", e);
        }
    }
}
