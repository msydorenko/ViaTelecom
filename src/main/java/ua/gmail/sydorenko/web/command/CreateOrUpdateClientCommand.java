package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Address;
import ua.gmail.sydorenko.database.entity.Bill;
import ua.gmail.sydorenko.database.entity.Contact;
import ua.gmail.sydorenko.database.entity.User;
import ua.gmail.sydorenko.util.SecurePassword;
import ua.gmail.sydorenko.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author M.Sydorenko
 */
public class CreateOrUpdateClientCommand extends GeneralCommand {
    private static final long serialVersionUID = -1947553783638751603L;
    private static final Logger LOG = Logger.getLogger(CreateOrUpdateClientCommand.class);
    private static final int ID_ROLE_CLIENT = 2;

    public CreateOrUpdateClientCommand(AddressDao addressDao, BillDao billDao, ContactDao contactDao, ServiceDao serviceDao, TariffDao tariffDao, UserDao userDao) {
        super(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException {
        LOG.debug("Command 'create or update client' starts");
        String forward = checkResubmit(request);
        LOG.trace("Check is successfully finished");
        if (forward.equals(Path.COMMAND_ERROR)) {
            return forward;
        }
        String idUserForUpdate = request.getParameter("idUserForUpdate");
        LOG.trace("idUserForUpdate: " + idUserForUpdate);

        Address address = createAddress(request, idUserForUpdate);
        Contact contact = createContact(request, idUserForUpdate);
        Bill bill = createBill(request, idUserForUpdate);
        User user = createUser(request, idUserForUpdate);

        if (idUserForUpdate != null) {
            addressDao.update(address);
            contactDao.update(contact);
            billDao.update(bill);
            userDao.update(user);
            LOG.trace("Update client: " + user);
        } else {
            addressDao.create(address);
            contactDao.create(contact);
            billDao.create(bill);
            userDao.create(user);
            LOG.trace("Create client: " + user);
        }
        LOG.debug("Command 'create or update client' finished");
        return forward;
    }

    private User createUser(HttpServletRequest request, String idUserForUpdate) {
        User user = new User();
        if (idUserForUpdate != null) {
            user.setId(Integer.parseInt(idUserForUpdate));
        }
        user.setLogin(request.getParameter("login"));
        String password = SecurePassword.getSaltedHash(request.getParameter("password"));
        user.setPassword(password);
        user.setFirst_name(request.getParameter("fname"));
        user.setLast_name(request.getParameter("lname"));
        user.setRoleId(ID_ROLE_CLIENT);
        LOG.trace("Create user: " + user);
        return user;
    }

    private Bill createBill(HttpServletRequest request, String idUserForUpdate) {
        Bill bill = new Bill();
        if (idUserForUpdate != null) {
            bill.setId(Integer.parseInt(idUserForUpdate));
        }
        bill.setNumber(request.getParameter("bill"));
        bill.setValue(Integer.parseInt(request.getParameter("balance")));
        LOG.trace("Create bill: " + bill);
        return bill;
    }

    private Contact createContact(HttpServletRequest request, String idUserForUpdate) {
        Contact contact = new Contact();
        if (idUserForUpdate != null) {
            contact.setId(Integer.parseInt(idUserForUpdate));
        }
        contact.setPhoneNumber(Long.parseLong(request.getParameter("phone")));
        contact.setEmail(request.getParameter("email"));
        LOG.trace("Create contact: " + contact);
        return contact;
    }

    private Address createAddress(HttpServletRequest request, String idUserForUpdate) {
        Address address = new Address();
        if (idUserForUpdate != null) {
            address.setId(Integer.parseInt(idUserForUpdate));
        }
        address.setCountry(request.getParameter("country"));
        address.setCity(request.getParameter("city"));
        address.setStreet(request.getParameter("street"));
        address.setHouse(Integer.parseInt(request.getParameter("house")));
        address.setFlat(Integer.parseInt(request.getParameter("flat")));
        LOG.trace("Create address: " + address);
        return address;
    }
}
