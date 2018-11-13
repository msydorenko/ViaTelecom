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
public class AddTariffCommand implements Command {
    private static final long serialVersionUID = -2589405645948375128L;
    private static final Logger LOG = Logger.getLogger(AddTariffCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'add tariff' starts");
        Tariff tariff = new Tariff();
        tariff.setSpr_service_id(Integer.parseInt(request.getParameter("service")));
        tariff.setName(request.getParameter("name"));
        tariff.setPrice(Integer.parseInt(request.getParameter("price")));
        tariff.setDescription(request.getParameter("description"));
        LOG.trace("Create new tariff: " + tariff);

        TariffDao service = new TariffDaoImpl();
        service.create(tariff);
        LOG.debug("Command 'add tariff' was successfully finished");

        return Path.COMMAND_MAIN;
    }
}
