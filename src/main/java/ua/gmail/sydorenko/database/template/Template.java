package ua.gmail.sydorenko.database.template;

import ua.gmail.sydorenko.database.DBManager;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author M.Sydorenko
 */
public abstract class Template<T extends Entity> {

    /**
     * Execute query (CREATE, UPDATE, DELETE ) the database
     *
     * @param manager the DataSource instance
     * @param query   the database query
     */
    public void executeQuery(DBManager manager, String query) throws DaoSystemException {
        executeQuery(manager, query, new Object[0]);
    }

    /**
     * Execute query (CREATE, UPDATE, DELETE ) the database
     *
     * @param manager the DataSource instance
     * @param query   the database query
     * @param values  arguments
     */
    public void executeQuery(DBManager manager, String query, Object... values) throws DaoSystemException {
        Connection connection = manager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setParamInQuery(preparedStatement, values);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            manager.rollbackAndClose(connection);
            throw new DaoSystemException("Cannot execute and return result! ", e);
        }
        manager.commitAndClose(connection);
    }

    /**
     * Obtain data from database by parameter
     *
     * @param manager  the DataSource instance
     * @param query    the database query
     * @return the list of {@link ua.gmail.sydorenko.database.entity.Entity}
     */
    public List<T> executeAndReturn(DBManager manager, String query) throws DaoSystemException {
        return executeAndReturn(manager, query, new Object[0]);
    }

    /**
     * Obtain data from database by parameter
     *
     * @param manager  the DataSource instance
     * @param query    the database query
     * @param values   arguments
     * @return the list of {@link ua.gmail.sydorenko.database.entity.Entity}
     */
    public List<T> executeAndReturn(DBManager manager, String query, Object... values) throws DaoSystemException {
        List<T> list = new ArrayList<>();
        Connection connection = manager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setParamInQuery(preparedStatement, values);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(getResultRow(resultSet));
                }
            }
        } catch (SQLException e) {
            manager.rollbackAndClose(connection);
            throw new DaoSystemException("Cannot execute and return result! ", e);
        }
        manager.commitAndClose(connection);
        return list;
    }

    private void setParamInQuery(PreparedStatement ps, Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            ps.setObject(i + 1, values[i]);
        }
    }

    /**
     * Obtain next automatic id mysql
     *
     * @param manager the DataSource instance
     * @param query    the database query
     * @return next automatic id mysql
     */
    public int readNextAutoIncrement(DBManager manager, String query) throws DaoSystemException {
        Integer nextId = -1;
        Connection connection = manager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                nextId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            manager.rollbackAndClose(connection);
            throw new DaoSystemException("Cannot execute and return result! ", e);
        }
        return nextId;
    }

    public abstract T getResultRow(ResultSet resultSet) throws SQLException;
}
