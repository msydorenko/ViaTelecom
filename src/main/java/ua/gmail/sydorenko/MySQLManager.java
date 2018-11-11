package ua.gmail.sydorenko;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;

import javax.sql.DataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public class MySQLManager implements DBManager {
    private static final Logger LOG = Logger.getLogger(MySQLManager.class.getName());
    private static MySQLManager instance;
    private static DataSource dataSource;

    private MySQLManager() {

    }

    /**
     * Create single instance of MySQLManager
     *
     * @throws DaoSystemException if attempt to connect to database was failed
     */
    public static synchronized MySQLManager getInstance() {
        if (instance == null)
            instance = new MySQLManager();
        return instance;
    }

    /**
     * Method establish connection to database
     *
     * @return Connection
     */
    public Connection getConnection() throws DaoSystemException {
        Connection con = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/ViatelecomDB");
            con = ds.getConnection();
        } catch (NamingException ex) {
            ex.printStackTrace();
            LOG.error("Cannot obtain a connection from the pool", ex);
        } catch (SQLException e) {
            e.printStackTrace();
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
