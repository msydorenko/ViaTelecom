package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.TariffDao;
import ua.gmail.sydorenko.database.dao.TariffDaoImpl;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Tariff;

import javax.servlet.http.HttpServletRequest;

/**
 * @author M.Sydorenko
 */
public class OpenEditTariffPageCommand implements Command {
    private static final long serialVersionUID = 5519053904416872146L;
    private static final Logger LOG = Logger.getLogger(OpenEditTariffPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command open page to update starts");
        int tariffId = Integer.parseInt(request.getParameter("tariffId"));
        LOG.trace("Id tariff for update --> " + tariffId);
        TariffDao service = new TariffDaoImpl();
        Tariff tariff = service.readById(tariffId).get(0);
        System.out.println(tariff.getName() + " " + tariffId);
        request.setAttribute("tariffForUpdate", tariff);
        LOG.trace("Tariff for update --> " + tariffId);
        LOG.debug("Command open page for update end");
        return Path.PAGE_OPEN_EDIT_TARIFF;
    }
}
