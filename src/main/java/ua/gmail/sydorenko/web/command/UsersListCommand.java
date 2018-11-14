package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class UsersListCommand implements Command {
    private static final long serialVersionUID = 8379728542243211585L;
    private static final Logger LOG = Logger.getLogger(UsersListCommand.class);


    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'list of users' starts");
        HttpSession session = request.getSession(false);
        UserDao userDao = new UserDaoImpl();
        BillDao billDao = new BillDaoImpl();
        AddressDao addressDao = new AddressDaoImpl();
        ContactDao contactDao = new ContactDaoImpl();

        List<User> users = userDao.readAll();
        LOG.error("list " + users);
        List<User> completeUser = new ArrayList<>();
        for (User user : users) {
            user.setBill(billDao.readById(user.getBill().getId()).get(0));
            LOG.error("bill " + user.getBill());

            user.setAddress(addressDao.readById(user.getAddress().getId()).get(0));
            LOG.error("address " + user.getAddress());
            user.setContact(contactDao.readById(user.getContact().getId()).get(0));
            LOG.error("cont " + user.getContact());
            completeUser.add(user);
            LOG.error("add to list  " + user);
        }
        LOG.error("new list with users ------->  " + completeUser);
        session.setAttribute("completeUser", completeUser);
        LOG.debug("Command 'list of users' finished");

        return Path.PAGE_CLIENTS;
    }
}
