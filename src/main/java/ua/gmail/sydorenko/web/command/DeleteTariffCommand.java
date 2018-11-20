package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author M.Sydorenko
 */
public class DeleteTariffCommand extends GeneralCommand {
    private static final long serialVersionUID = -7451015799760653817L;
    private static final Logger LOG = Logger.getLogger(DeleteTariffCommand.class);

    public DeleteTariffCommand(AddressDao addressDao, BillDao billDao, ContactDao contactDao, ServiceDao serviceDao, TariffDao tariffDao, UserDao userDao) {
        super(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException {
        LOG.debug("Command delete tariff starts");
        int tariffId = Integer.parseInt(request.getParameter("tariffId"));
        LOG.trace("Id tariff: " + tariffId);

        tariffDao.delete(tariffId);
        LOG.debug("Command successfully delete");
        return Path.COMMAND_MAIN;
    }
}
