package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.TariffDao;
import ua.gmail.sydorenko.database.dao.TariffDaoImpl;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Tariff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author M.Sydorenko
 */
public class AddTariffCommand implements Command {
    private static final long serialVersionUID = -2589405645948375128L;
    private static final Logger LOG = Logger.getLogger(AddTariffCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'add tariff' starts");
        String forward;
        HttpSession session = request.getSession(false);
        String uid = request.getParameter("uid");
        LOG.trace("Request parameter uid: " + uid);

        if (!uid.equals(session.getAttribute("uid"))) {
            session.setAttribute("uid", uid);
            LOG.trace("Set uid in the session from 'add tariff' command " + uid);
            forward = Path.COMMAND_MAIN;
        } else {
            LOG.warn("Resubmit form");
            return Path.PAGE_ERROR;
        }
        Tariff tariff = new Tariff();
        tariff.setSpr_service_id(Integer.parseInt(request.getParameter("service")));
        tariff.setName(request.getParameter("name"));
        tariff.setPrice(Integer.parseInt(request.getParameter("price")));
        tariff.setDescription(request.getParameter("description"));
        LOG.trace("Create new tariff: " + tariff);

        TariffDao service = new TariffDaoImpl();
        service.create(tariff);
        LOG.debug("Command 'add tariff' was successfully finished");

        return forward;
    }
}
