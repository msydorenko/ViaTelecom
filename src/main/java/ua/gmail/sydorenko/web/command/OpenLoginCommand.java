package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.exception.DaoSystemException;
import ua.gmail.sydorenko.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command for opening a login page.
 *
 * @author M.Sydorenko
 */
public class OpenLoginCommand extends GeneralCommand {
    private static final long serialVersionUID = -1094779617517312566L;
    private static final Logger LOG = Logger.getLogger(OpenLoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException {
        LOG.debug("Open page login");
        return Path.PAGE_LOGIN;
    }
}
