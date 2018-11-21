package ua.gmail.sydorenko.database.exception;

/**
 * Dao Exception.
 *
 * @author M.Sydorenko
 */
public class DaoException extends Exception {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
