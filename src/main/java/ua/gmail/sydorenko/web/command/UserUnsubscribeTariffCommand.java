package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.entity.Tariff;
import ua.gmail.sydorenko.database.entity.User;
import ua.gmail.sydorenko.database.exception.DaoSystemException;
import ua.gmail.sydorenko.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Set;

/**
 * Command for unsubscribe tariff for a user.
 *
 * @author M.Sydorenko
 */
public class UserUnsubscribeTariffCommand extends GeneralCommand {
    private static final long serialVersionUID = -4568479273029386042L;
    private static final Logger LOG = Logger.getLogger(UserUnsubscribeTariffCommand.class);

    public UserUnsubscribeTariffCommand(AddressDao addressDao, BillDao billDao, ContactDao contactDao, ServiceDao serviceDao, TariffDao tariffDao, UserDao userDao) {
        super(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException {
        LOG.debug("Command 'unsubscribe tariff for user' starts");
        int tariffId = Integer.parseInt(request.getParameter("tariffId"));
        LOG.trace("Obtain tariff id for unsubscribe: " + tariffId);

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        LOG.trace("Obtain user from session: " + user);

        Set<Tariff> tariffs = user.getTariffs();
        Iterator<Tariff> iterator = tariffs.iterator();
        while (iterator.hasNext()) {
            Tariff next = iterator.next();
            if (next.getId() == tariffId) {
                iterator.remove();
            }
        }

        user.setTariffs(tariffs);
        LOG.trace("Refresh list of tariffs for user: " + user);

        userDao.deleteTariffForUser(user, tariffId);
        LOG.trace("Delete link on tariff in table user_orders");

        session.setAttribute("user", user);
        LOG.trace("Update user in session " + user);
        LOG.debug("Command 'unsubscribe tariff for user' successfully finished");

        return Path.PAGE_MAIN;
    }
}
