package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Bill;
import ua.gmail.sydorenko.database.entity.Tariff;
import ua.gmail.sydorenko.database.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author M.Sydorenko
 */
public class UserSubscribeTariffCommand extends GeneralCommand {
    private static final long serialVersionUID = 6346303699053123541L;
    private static final Logger LOG = Logger.getLogger(UserSubscribeTariffCommand.class);

    public UserSubscribeTariffCommand(AddressDao addressDao, BillDao billDao, ContactDao contactDao, ServiceDao serviceDao, TariffDao tariffDao, UserDao userDao) {
        super(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException {
        LOG.debug("Command 'subscribe tariff for user' starts");

        String forward = checkResubmit(request);
        LOG.trace("Check is successfully finished ");
        if (forward.equals(Path.PAGE_ERROR)) {
            return forward;
        }

        balanceProcessing(request);
        LOG.debug("Command 'subscribe tariff for user' successfully finished");

        return forward;
    }

    private void balanceProcessing(HttpServletRequest request) throws DaoSystemException {
        HttpSession session = request.getSession(false);

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
    }
}
