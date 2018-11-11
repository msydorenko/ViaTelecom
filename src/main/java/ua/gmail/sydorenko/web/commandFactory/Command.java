package ua.gmail.sydorenko.web.commandFactory;

import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author M.Sydorenko
 */
public interface Command extends Serializable {
    String execute(HttpServletRequest request) throws DaoSystemException;
}
