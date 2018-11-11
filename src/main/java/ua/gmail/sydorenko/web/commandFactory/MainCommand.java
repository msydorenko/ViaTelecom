package ua.gmail.sydorenko.web.commandFactory;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.ServiceDao;
import ua.gmail.sydorenko.database.dao.ServiceDaoImpl;
import ua.gmail.sydorenko.database.dao.TariffDao;
import ua.gmail.sydorenko.database.dao.TariffDaoImpl;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Service;
import ua.gmail.sydorenko.database.entity.Tariff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author M.Sydorenko
 */
public class MainCommand implements Command {
    private static final long serialVersionUID = -4461981504404538233L;
    private static final Logger LOG = Logger.getLogger(MainCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'main' starts");
        ServiceDao serviceDao = new ServiceDaoImpl();
        TariffDao tariffDao = new TariffDaoImpl();

        HttpSession session = request.getSession(false);
        List<Service> serviceList = (List<Service>) session.getAttribute("serviceList");
        List<Tariff> tariffList = tariffDao.readAll();
        if (serviceList == null) {
            serviceList = serviceDao.readAll();
        }
        LOG.trace("Obtain list of services and list of tariffs");
        session.setAttribute("tariffList", tariffList);
        session.setAttribute("serviceList", serviceList);
        LOG.debug("Command 'main' was successfully finished");

        return Path.PAGE_MAIN;
    }
}
