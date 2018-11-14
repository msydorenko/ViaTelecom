package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.UserDao;
import ua.gmail.sydorenko.database.dao.UserDaoImpl;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author M.Sydorenko
 */
public class ChangeUserStatusCommand implements Command {
    private static final long serialVersionUID = 8793955674256165894L;
    private static final Logger LOG = Logger.getLogger(ChangeUserStatusCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'change user status' starts");
        UserDao userDao = new UserDaoImpl();

        int idUserForStatusChange = Integer.parseInt(request.getParameter("idUserForStatusChange"));
        String parameter = request.getParameter("action");
        User user = userDao.readById(idUserForStatusChange).get(0);
        switch (parameter) {
            case "unblock": {
                user.setBlocked(false);
                break;
            }
            case "block": {
                user.setBlocked(true);
                break;
            }
        }
        LOG.trace("Obtain user from table and change status " + user);
        userDao.update(user);
        LOG.trace("Update user in table");
        LOG.debug("Command 'change user status' finished");

        return Path.COMMAND_USERSLIST;
    }
}
