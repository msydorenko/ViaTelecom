package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.BillDao;
import ua.gmail.sydorenko.database.dao.BillDaoImpl;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Bill;
import ua.gmail.sydorenko.database.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author M.Sydorenko
 */
public class RechargeCommand implements Command {
    private static final long serialVersionUID = 3170568683038905724L;
    private static final Logger LOG = Logger.getLogger(RechargeCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'recharge' starts");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        Bill bill = user.getBill();
        LOG.trace("User " + user + "has bill: " + bill);

        int newBalance = Integer.parseInt(request.getParameter("newBalance"));
        LOG.trace("User want to update the balance to " + newBalance + "UAH");

        bill.setValue(newBalance);
        user.setBill(bill);
        session.setAttribute("user", user);
        LOG.trace("Update user in session " + user);

        BillDao billDao = new BillDaoImpl();
        billDao.update(bill);
        LOG.trace("Update balance in table spr_bills");
        LOG.debug("Command 'recharge' successfully finished");

        return Path.COMMAND_MAIN;
    }
}
