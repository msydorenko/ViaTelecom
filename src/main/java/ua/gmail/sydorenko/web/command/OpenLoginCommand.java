package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenLoginCommand extends GeneralCommand {
    private static final long serialVersionUID = -1094779617517312566L;
    private static final Logger LOG = Logger.getLogger(OpenLoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException {
        LOG.debug("Open page login");
        return Path.PAGE_LOGIN;
    }
}
