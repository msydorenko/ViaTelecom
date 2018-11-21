package ua.gmail.sydorenko.database.exception;

/**
 * Dao System Exception.
 *
 * @author M.Sydorenko
 */
public class DaoSystemException extends DaoException {
    public DaoSystemException(String message) {
        super(message);
    }

    public DaoSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
