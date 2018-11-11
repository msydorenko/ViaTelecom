package ua.gmail.sydorenko.web.commandFactory;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.TariffDao;
import ua.gmail.sydorenko.database.dao.TariffDaoImpl;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Tariff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author M.Sydorenko
 */
public class TariffListCommand implements Command {
    private static final long serialVersionUID = -8021035582917973234L;
    private static final Logger LOG = Logger.getLogger(TariffListCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command for list of tariffs starts");
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        TariffDao tariffService = new TariffDaoImpl();
        List<Tariff> tariffList = ((TariffDaoImpl) tariffService).readByIdService(serviceId);
        HttpSession session = request.getSession(false);
        session.setAttribute("tariffList", tariffList);
        LOG.debug("Command for list of tariffs finished");

        return Path.PAGE_MAIN;
    }
}
