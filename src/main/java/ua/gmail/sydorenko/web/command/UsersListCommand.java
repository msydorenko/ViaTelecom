package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author M.Sydorenko
 */
public class UsersListCommand extends GeneralCommand {
    private static final long serialVersionUID = 8379728542243211585L;
    private static final Logger LOG = Logger.getLogger(UsersListCommand.class);

    public UsersListCommand(AddressDao addressDao, BillDao billDao, ContactDao contactDao, ServiceDao serviceDao, TariffDao tariffDao, UserDao userDao) {
        super(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException {
        LOG.debug("Command 'list of users' starts");

        List<User> users = userDao.readAll();
        List<User> completeUser = new ArrayList<>();
        for (User user : users) {
            user.setBill(billDao.readById(user.getBill().getId()).get(0));
            user.setAddress(addressDao.readById(user.getAddress().getId()).get(0));
            user.setContact(contactDao.readById(user.getContact().getId()).get(0));
            completeUser.add(user);
        }
        request.setAttribute("completeUser", completeUser);
        LOG.debug("Command 'list of users' finished");

        return Path.PAGE_CLIENTS;
    }
}
