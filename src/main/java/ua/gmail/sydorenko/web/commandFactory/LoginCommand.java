package ua.gmail.sydorenko.web.commandFactory;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Address;
import ua.gmail.sydorenko.database.entity.Role;
import ua.gmail.sydorenko.database.entity.Tariff;
import ua.gmail.sydorenko.database.entity.User;
import ua.gmail.sydorenko.util.SecurePassword;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author M.Sydorenko
 */
public class LoginCommand implements Command {
    private static final long serialVersionUID = -6425350012888495169L;
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        LOG.trace("Request parameter: loging --> " + login);
        String password = request.getParameter("password");
        String errorMessage;
        String forward = Path.PAGE_LOGIN;

        if (password.equals("") || login.equals("") || login == null || password == null) {
            errorMessage = "Enter your login and password";
            request.setAttribute("errorMessage", errorMessage);
            LOG.error("errorMessage --> " + errorMessage);
            return forward;
        }

        UserDao userDao = new UserDaoImpl();
        BillDao billDao = new BillDaoImpl();//TODO
        AddressDao addressDao = new AddressDaoImpl();//TODO
        ContactDao contactDao = new ContactDaoImpl();//TODO

        User user = userDao.readByLogin(login).get(0);
        LOG.trace("Found in DB: user --> " + user);
        List<Tariff> tariffList = userDao.readTariffForUser(user);
        for (Tariff tariff : tariffList) {
            user.getTariffs().add(tariff);
        }
//        if (user == null || !user.getPassword().equals(password)) {
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
}
