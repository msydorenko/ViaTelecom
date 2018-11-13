package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.TariffDao;
import ua.gmail.sydorenko.database.dao.TariffDaoImpl;
import ua.gmail.sydorenko.database.dao.UserDao;
import ua.gmail.sydorenko.database.dao.UserDaoImpl;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Tariff;
import ua.gmail.sydorenko.database.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

public class UserUnsubscribeTariffCommand implements Command {
    private static final long serialVersionUID = -4568479273029386042L;
    private static final Logger LOG = Logger.getLogger(UserUnsubscribeTariffCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'unsubscribe tariff for user' starts");
        int tariffId = Integer.parseInt(request.getParameter("tariffId"));
        LOG.trace("Obtain tariff id for unsubscribe: " + tariffId);

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        LOG.trace("Obtain user from session: " + user);

        Set<Tariff> tariffs = user.getTariffs();
        for (Tariff tariff : tariffs) {
            if (tariff.getId() == tariffId) {
                tariffs.remove(tariff);
            }
        }
        user.setTariffs(tariffs);
        LOG.trace("Refresh list of tariffs for user: " + user);

        UserDao userDao = new UserDaoImpl();
        userDao.deleteTariffForUser(user, tariffId);
        LOG.trace("Delete link on tariff in table user_orders");

        session.setAttribute("user", user);
        LOG.trace("Update user in session " + user);
        LOG.debug("Command 'unsubscribe tariff for user' successfully finished");

        return Path.PAGE_MAIN;
    }
}
