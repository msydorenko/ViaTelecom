package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.UserDao;
import ua.gmail.sydorenko.database.dao.UserDaoImpl;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author M.Sydorenko
 */
public class DeleteClientCommand implements Command {
    private static final long serialVersionUID = 682034925387675499L;
    private static final Logger LOG = Logger.getLogger(DeleteClientCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'delete client' starts");
        UserDao userDao = new UserDaoImpl();
        int idUserForChange = Integer.parseInt(request.getParameter("idUserForChange"));
        LOG.trace("Id client for delete: " + idUserForChange);
        userDao.delete(idUserForChange);

        LOG.debug("Command 'delete client' finished");
        return Path.COMMAND_CLIENTS_LIST;
    }
}
