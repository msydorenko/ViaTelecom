package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author M.Sydorenko
 */
public class ChangeUserStatusCommand extends GeneralCommand {
    private static final long serialVersionUID = 8793955674256165894L;
    private static final Logger LOG = Logger.getLogger(ChangeUserStatusCommand.class);

    public ChangeUserStatusCommand(AddressDao addressDao, BillDao billDao, ContactDao contactDao, ServiceDao serviceDao, TariffDao tariffDao, UserDao userDao) {
        super(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao);
    }

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'change user status' starts");

        int idUserForChange = Integer.parseInt(request.getParameter("idUserForChange"));
        String parameter = request.getParameter("action");
        User user = userDao.readById(idUserForChange).get(0);
        switch (parameter) {
            case "unblock":
                user.setBlocked(false);
                break;
            case "block":
                user.setBlocked(true);
                break;
        }
        LOG.trace("Obtain user from table and change status " + user);
        userDao.update(user);
        LOG.trace("Update user in table");
        LOG.debug("Command 'change user status' finished");

        return Path.COMMAND_CLIENTS_LIST;
    }
}
