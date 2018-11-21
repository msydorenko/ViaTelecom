package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.entity.Bill;
import ua.gmail.sydorenko.database.entity.User;
import ua.gmail.sydorenko.database.exception.DaoSystemException;
import ua.gmail.sydorenko.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command for recharge a bill for a user.
 *
 * @author M.Sydorenko
 */
public class RechargeCommand extends GeneralCommand {
    private static final long serialVersionUID = 3170568683038905724L;
    private static final Logger LOG = Logger.getLogger(RechargeCommand.class);

    public RechargeCommand(AddressDao addressDao, BillDao billDao, ContactDao contactDao, ServiceDao serviceDao, TariffDao tariffDao, UserDao userDao) {
        super(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException {
        LOG.debug("Command 'recharge' starts");
        String forward = checkResubmit(request);
        LOG.trace("Check is successfully finished");
        if (forward.equals(Path.COMMAND_ERROR)) {
            return forward;
        }

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        Bill bill = user.getBill();
        LOG.trace("User " + user + "has bill: " + bill);
        int oldBalance = bill.getValue();

        int deposit = Integer.parseInt(request.getParameter("newBalance"));
        LOG.trace("User want to update the balance to " + deposit + "UAH");

        int newBalance = oldBalance + deposit;
        bill.setValue(newBalance);
        user.setBill(bill);
        session.setAttribute("user", user);
        LOG.trace("Update user in session " + user);

        billDao.update(bill);
        LOG.trace("Update balance in table spr_bills");
        LOG.debug("Command 'recharge' successfully finished");

        return forward;
    }


}
