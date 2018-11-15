package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Address;
import ua.gmail.sydorenko.database.entity.Bill;
import ua.gmail.sydorenko.database.entity.Contact;
import ua.gmail.sydorenko.database.entity.User;
import ua.gmail.sydorenko.util.SecurePassword;

import javax.servlet.http.HttpServletRequest;

/**
 * @author M.Sydorenko
 */
public class CreateOrUpdateClientCommand implements Command {
    private static final long serialVersionUID = -1947553783638751603L;
    private static final Logger LOG = Logger.getLogger(CreateOrUpdateClientCommand.class);
    private static final int ID_ROLE_CLIENT = 2;

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'create or update client' starts");
        UserDao userDao = new UserDaoImpl();
        BillDao billDao = new BillDaoImpl();
        AddressDao addressDao = new AddressDaoImpl();
        ContactDao contactDao = new ContactDaoImpl();

        Address address = new Address();
        address.setCountry(request.getParameter("country"));
        address.setCity(request.getParameter("city"));
        address.setStreet(request.getParameter("street"));
        address.setHouse(Integer.parseInt(request.getParameter("house")));
        address.setFlat(Integer.parseInt(request.getParameter("flat")));
        LOG.trace("Create address: " + address);

        Contact contact = new Contact();
        contact.setPhoneNumber(Long.parseLong(request.getParameter("phone")));
        contact.setEmail(request.getParameter("email"));
        LOG.trace("Create contact: " + contact);

        Bill bill = new Bill();
        bill.setNumber(request.getParameter("bill"));
        bill.setValue(Integer.parseInt(request.getParameter("balance")));
        LOG.trace("Create bill: " + bill);

        User user = new User();
        user.setLogin(request.getParameter("login"));
        String password = SecurePassword.getSaltedHash(request.getParameter("password"));
        user.setPassword(password);
        user.setFirst_name(request.getParameter("fname"));
        user.setLast_name(request.getParameter("lname"));
        user.setRoleId(ID_ROLE_CLIENT);
        LOG.trace("Create user: " + user);

        if ((request.getParameter("idUserForUpdate") != null) && (!request.getParameter("idUserForUpdate").isEmpty())) {
            addressDao.update(address);
            contactDao.update(contact);
            billDao.update(bill);
            userDao.update(user);
        } else {
            addressDao.create(address);
            contactDao.create(contact);
            billDao.create(bill);
            userDao.create(user);
        }

        LOG.debug("Command 'create or update client' finished");
        return Path.COMMAND_CLIENTS_LIST;
    }
}
