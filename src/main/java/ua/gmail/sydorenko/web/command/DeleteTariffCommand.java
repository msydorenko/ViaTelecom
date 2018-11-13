package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.TariffDao;
import ua.gmail.sydorenko.database.dao.TariffDaoImpl;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author M.Sydorenko
 */
public class DeleteTariffCommand implements Command {
    private static final long serialVersionUID = -7451015799760653817L;
    private static final Logger LOG = Logger.getLogger(DeleteTariffCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command delete tariff starts");
        int tariffId = Integer.parseInt(request.getParameter("tariffId"));
        LOG.trace("Id tariff: " + tariffId);
        TariffDao service = new TariffDaoImpl();
        service.delete(tariffId);
        LOG.debug("Command successfully delete");
        return Path.COMMAND_MAIN;
    }
}
