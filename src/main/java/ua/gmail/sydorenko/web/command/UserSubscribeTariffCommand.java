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

/**
 * @author M.Sydorenko
 */
public class UserSubscribeTariffCommand implements Command {
    private static final long serialVersionUID = 6346303699053123541L;
    private static final Logger LOG = Logger.getLogger(UserSubscribeTariffCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'subscribe tariff for user' starts");
        HttpSession session = request.getSession(false);
        UserDao userDao = new UserDaoImpl();
        TariffDao tariffDao = new TariffDaoImpl();

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        int currentBalance = user.getBill().getValue();
        LOG.trace("Current bill value: " + currentBalance);

        int tariffId = Integer.parseInt(request.getParameter("tariffId"));
        Tariff tariff = tariffDao.readById(tariffId).get(0);
        LOG.trace("Obtain tariff by id for subscribe" + tariff);

        int resultBalance = currentBalance - tariff.getPrice();
        LOG.trace("Balance after added tariff: " + resultBalance);
        String errorMessage;
        if (resultBalance < 0) {
            user.setActive_status(true);
            userDao.update(user);
            errorMessage = "There is not enough money in the account to complete this operation! " +
                    "Please replenish your account and try again.";
            request.setAttribute("errorMessage", errorMessage);
            LOG.error("errorMessage --> " + errorMessage);
        } else {
            user.getTariffs().add(tariff);
            userDao.createTariffForUser(userId, tariffId);
        }

        session.setAttribute("user", user);
        LOG.trace("Update user in session " + user);
        LOG.debug("Command 'subscribe tariff for user' successfully finished");

        return Path.PAGE_MAIN;
    }
}
