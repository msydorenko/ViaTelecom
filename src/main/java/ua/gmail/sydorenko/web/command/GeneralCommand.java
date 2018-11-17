package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.AddressDao;
import ua.gmail.sydorenko.database.dao.BillDao;
import ua.gmail.sydorenko.database.dao.ContactDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class GeneralCommand implements Command {
    private static final long serialVersionUID = 3035771318660706686L;
    private static final Logger LOG = Logger.getLogger(GeneralCommand.class);


    public GeneralCommand() {
    }


    public String checkResubmit(HttpServletRequest request) {
        String forward;
        HttpSession session = request.getSession(false);
        String uid = request.getParameter("uid");
        LOG.trace("Request parameter in 'recharge' command: " + uid);

        if (session != null && !uid.equals(session.getAttribute("uid"))) {
            session.setAttribute("uid", uid);
            LOG.trace("Set uid in the session in 'recharge' command" + uid);
            forward = Path.PAGE_MAIN;
        } else {
            LOG.warn("Resubmit form");
            return Path.PAGE_ERROR;
        }
        return forward;
    }
}
