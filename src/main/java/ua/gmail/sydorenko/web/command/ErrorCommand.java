package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.exception.DaoSystemException;
import ua.gmail.sydorenko.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Error command. Open error page.
 *
 * @author M.Sydorenko
 */
public class ErrorCommand extends GeneralCommand {
    private static final long serialVersionUID = 969313750722127938L;
    private static final Logger LOG = Logger.getLogger(ErrorCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException {
        LOG.trace("Return error page");
        return Path.PAGE_ERROR;
    }
}
