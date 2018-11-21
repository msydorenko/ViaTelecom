package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.exception.DaoSystemException;
import ua.gmail.sydorenko.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command to logout and invalidate current session.
 *
 * @author M.Sydorenko
 */
public class LogoutCommand extends GeneralCommand {
    private static final long serialVersionUID = 6566772108670704658L;
    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();

        LOG.debug("Command finished");
        return Path.PAGE_INDEX;
    }
}
