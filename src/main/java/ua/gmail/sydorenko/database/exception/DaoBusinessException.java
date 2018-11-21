package ua.gmail.sydorenko.database.exception;

/**
 * Dao Business Exception.
 *
 * @author M.Sydorenko
 */
public class DaoBusinessException extends DaoException{
    public DaoBusinessException(String message) {
        super(message);
    }

    public DaoBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
