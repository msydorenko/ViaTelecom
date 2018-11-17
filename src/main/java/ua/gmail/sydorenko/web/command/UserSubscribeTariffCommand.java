package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Bill;
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

        String forward = checkResubmit(request);
        LOG.trace("Check is successfully finished ");
        HttpSession session = request.getSession(false);

        UserDao userDao = new UserDaoImpl();
        TariffDao tariffDao = new TariffDaoImpl();
        BillDao billDao = new BillDaoImpl();

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        Bill bill = user.getBill();
        int currentBalance = bill.getValue();
        LOG.trace("Current bill value: " + currentBalance);

        int tariffId = Integer.parseInt(request.getParameter("tariffId"));
        Tariff tariff = tariffDao.readById(tariffId).get(0);
        LOG.trace("Obtain tariff by id for subscribe" + tariff);

        int resultBalance = currentBalance - tariff.getPrice();
        LOG.trace("Balance after added tariff: " + resultBalance);
        String errorMessage;
        if (resultBalance < 0) {
            user.setBlocked(true);
            userDao.update(user);
            errorMessage = "There is not enough money in the account to complete this operation! " +
                    "Please replenish your account and try again.";
            request.setAttribute("errorMessage", errorMessage);
            LOG.error("errorMessage --> " + errorMessage);
        } else {
            user.getTariffs().add(tariff);
            userDao.createTariffForUser(userId, tariffId);
            bill.setValue(resultBalance);
            user.setBill(bill);
            billDao.update(bill);
        }

        session.setAttribute("user", user);
        LOG.trace("Update user in session " + user);
        LOG.debug("Command 'subscribe tariff for user' successfully finished");

        return forward;
    }

    private String checkResubmit(HttpServletRequest request) {
        String forward;
        HttpSession session = request.getSession(false);
        String uid = request.getParameter("uid");
        LOG.trace("Request parameter in 'subscribe tariff' command: " + uid);

        if (session != null && !uid.equals(session.getAttribute("uid"))) {
            session.setAttribute("uid", uid);
            LOG.trace("Set uid in the session in 'subscribe tariff' command" + uid);
            forward = Path.PAGE_MAIN;
        } else {
            LOG.warn("Resubmit form");
            return Path.PAGE_ERROR;
        }
        return forward;
    }
}
