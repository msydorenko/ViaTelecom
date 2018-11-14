package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.User;

import javax.servlet.http.HttpServletRequest;

public class EditUserCommand implements Command {
    private static final long serialVersionUID = -7092103951978477774L;
    private static final Logger LOG = Logger.getLogger(EditUserCommand.class);


    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'edit user' starts");
        UserDao userDao = new UserDaoImpl();
        BillDao billDao = new BillDaoImpl();
        AddressDao addressDao = new AddressDaoImpl();
        ContactDao contactDao = new ContactDaoImpl();

        int idUserForChange = Integer.parseInt(request.getParameter("idUserForChange"));
        User user = userDao.readById(idUserForChange).get(0);
        user.setBill(billDao.readById(user.getBill().getId()).get(0));
        user.setAddress(addressDao.readById(user.getAddress().getId()).get(0));
        user.setContact(contactDao.readById(user.getContact().getId()).get(0));
        LOG.trace("Obtain user for change " + user);

        request.setAttribute("userForUpdate", user);
        LOG.debug("Command 'edit user' finished");
        return Path.PAGE_EDIT_CLIENT;
    }
}
