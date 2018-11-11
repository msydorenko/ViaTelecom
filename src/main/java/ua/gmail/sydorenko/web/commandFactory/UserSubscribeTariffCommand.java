package ua.gmail.sydorenko.web.commandFactory;

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

/**
 * @author M.Sydorenko
 */
public class UserSubscribeTariffCommand implements Command {
    private static final long serialVersionUID = 6346303699053123541L;
    private static final Logger LOG = Logger.getLogger(UserSubscribeTariffCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'add tariff for user' starts");
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        int userId = user.getId();
/*
        user.getBill();
*/
        int tariffId = Integer.parseInt(request.getParameter("tariffId"));
        LOG.trace("Obtain user's id - " + userId + " and tariff's id - " + tariffId);
        TariffDao serviceTariff = new TariffDaoImpl();
        Tariff tariff = serviceTariff.readById(tariffId).get(0);
        LOG.trace("Obtain tariff by id " + tariff);
        user.getTariffs().add(tariff);
        session.setAttribute("user", user);
        LOG.trace("Update user in session " + tariff);
        UserDao serviceUser = new UserDaoImpl();
        serviceUser.createTariffForUser(userId, tariffId);
        LOG.debug("Command 'add tariff for user' successfully finished");

        return Path.PAGE_MAIN;
    }
}
