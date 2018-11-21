package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.exception.DaoSystemException;
import ua.gmail.sydorenko.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command for opening error page if user type a wrong command.
 *
 * @author M.Sydorenko
 */
public class NoCommand extends GeneralCommand {
    private static final long serialVersionUID = 4272101993597627486L;
    private static final Logger LOG = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException {
        LOG.debug("No command");
        return Path.COMMAND_ERROR;
    }
}
