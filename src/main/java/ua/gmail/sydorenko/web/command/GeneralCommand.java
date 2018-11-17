package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class GeneralCommand implements Command {
    private static final long serialVersionUID = 3035771318660706686L;
    private static final Logger LOG = Logger.getLogger(GeneralCommand.class);

    AddressDao addressDao;
    BillDao billDao;
    ContactDao contactDao;
    ServiceDao serviceDao;
    TariffDao tariffDao;
    UserDao userDao;

    public GeneralCommand() {
    }

    public GeneralCommand(AddressDao addressDao, BillDao billDao, ContactDao contactDao, ServiceDao serviceDao, TariffDao tariffDao, UserDao userDao) {
        this.addressDao = addressDao;
        this.billDao = billDao;
        this.contactDao = contactDao;
        this.serviceDao = serviceDao;
        this.tariffDao = tariffDao;
        this.userDao = userDao;
    }

    String checkResubmit(HttpServletRequest request) {
        String forward;
        HttpSession session = request.getSession(false);
        String uid = request.getParameter("uid");
        LOG.trace("Request parameter 'uid': " + uid);

        if (session != null && !uid.equals(session.getAttribute("uid"))) {
            session.setAttribute("uid", uid);
            LOG.trace("Set uid in the session: " + uid);
            forward = Path.PAGE_MAIN;
        } else {
            LOG.warn("Resubmit form");
            return Path.PAGE_ERROR;
        }
        return forward;
    }
}
