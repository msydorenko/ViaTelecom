package ua.gmail.sydorenko.web.command;

import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * @author M.Sydorenko
 */
public interface Command extends Serializable {
    String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException;
}
