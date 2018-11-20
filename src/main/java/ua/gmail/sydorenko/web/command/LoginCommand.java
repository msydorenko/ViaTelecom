package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.*;
import ua.gmail.sydorenko.util.SecurePassword;
import ua.gmail.sydorenko.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author M.Sydorenko
 */
public class LoginCommand extends GeneralCommand {
    private static final long serialVersionUID = -6425350012888495169L;
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    public LoginCommand(AddressDao addressDao, BillDao billDao, ContactDao contactDao, ServiceDao serviceDao, TariffDao tariffDao, UserDao userDao) {
        super(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        LOG.trace("Request parameter: loging --> " + login);
        String password = request.getParameter("password");

        String errorMessage;
        String forward = Path.PAGE_LOGIN;
        if (fillFields(request, login, password)) {
            return forward;
        }
        LOG.trace("Check fields is successfully finished");

        User user = createUser(login);
        LOG.trace("Create user is successfully finished");

        if (user == null || !SecurePassword.check(password, user.getPassword())) {
            errorMessage = "Incorrect data. Please, check it!";
            request.setAttribute("errorMessage", errorMessage);
            LOG.error("errorMessage --> " + errorMessage);
            return forward;
        } else {
            Role role = Role.getRole(user);
            LOG.trace("userRole --> " + role);
            forward = Path.COMMAND_MAIN;

            session.setAttribute("user", user);
            LOG.trace("Set the session attribute: user --> " + user);
            session.setAttribute("userRole", role);
            LOG.trace("Set the session attribute: userRole --> " + role);

            LOG.info("User " + user + " logged as " + role.toString().toLowerCase());
        }
        return forward;
    }

    private boolean fillFields(HttpServletRequest request, String login, String password) {
        String errorMessage;
        if (login == null || password == null || password.equals("") || login.equals("")) {
            errorMessage = "Enter your login and password";
            request.setAttribute("errorMessage", errorMessage);
            LOG.error("errorMessage --> " + errorMessage);
            return true;
        }
        return false;
    }

    private User createUser(String login) throws DaoSystemException {
        User user = userDao.readByLogin(login).get(0);
        int userId = user.getId();
        LOG.trace("Found in DB: user --> " + user);

        List<Tariff> tariffList = userDao.readTariffForUser(user);
        for (Tariff tariff : tariffList) {
            user.getTariffs().add(tariff);
        }
        LOG.trace("Obtain list of tariff for user --> " + user);

        Bill bill = billDao.readById(userId).get(0);
        LOG.trace("Obtain bill for user --> " + user);
        Address address = addressDao.readById(userId).get(0);
        LOG.trace("Obtain address for user --> " + user);
        Contact contact = contactDao.readById(userId).get(0);
        LOG.trace("Obtain contact data for user --> " + user);

        user.setBill(bill);
        user.setAddress(address);
        user.setContact(contact);
        return user;
    }
}
