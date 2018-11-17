package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author M.Sydorenko
 */
public class NoCommand implements Command {
    private static final long serialVersionUID = 4272101993597627486L;
    private static final Logger LOG = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("No command");
        return Path.PAGE_ERROR;
    }
}
