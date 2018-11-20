package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.web.Path;

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

    public String checkResubmit(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String command = request.getParameter("command");
        String forward = resultPage(command);

        String uid = request.getParameter("uid");
        LOG.trace("Request parameter 'uid': " + uid);

        if (session != null && !uid.equals(session.getAttribute("uid"))) {
            session.setAttribute("uid", uid);
            LOG.trace("Set uid in the session: " + uid);
        } else {
            LOG.warn("Resubmit form");
            return Path.COMMAND_ERROR;
        }
        return forward;
    }

    private String resultPage(String command) {
        String forward;
        if (command.equals("createOrUpdate")) {
            forward = Path.COMMAND_CLIENTS_LIST;
        } else {
            forward = Path.COMMAND_MAIN;
        }
        return forward;
    }
}
