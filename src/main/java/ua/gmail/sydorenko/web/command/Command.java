package ua.gmail.sydorenko.web.command;

import ua.gmail.sydorenko.database.exception.DaoSystemException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * General interface for all command.
 *
 * @author M.Sydorenko
 */
public interface Command extends Serializable {
    String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException;
}
