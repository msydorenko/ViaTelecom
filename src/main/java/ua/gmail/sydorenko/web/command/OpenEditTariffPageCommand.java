package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Tariff;

import javax.servlet.http.HttpServletRequest;

/**
 * @author M.Sydorenko
 */
public class OpenEditTariffPageCommand extends GeneralCommand {
    private static final long serialVersionUID = 5519053904416872146L;
    private static final Logger LOG = Logger.getLogger(OpenEditTariffPageCommand.class);

    public OpenEditTariffPageCommand(AddressDao addressDao, BillDao billDao, ContactDao contactDao, ServiceDao serviceDao, TariffDao tariffDao, UserDao userDao) {
        super(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao);
    }

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'open page to update' starts");
        int tariffId = Integer.parseInt(request.getParameter("tariffId"));
        LOG.trace("Id tariff for update --> " + tariffId);

        Tariff tariff = tariffDao.readById(tariffId).get(0);
        request.setAttribute("tariffForUpdate", tariff);
        LOG.trace("Tariff for update --> " + tariffId);
        LOG.debug("Command 'open page for update' end");
        return Path.PAGE_EDIT_TARIFF;
    }
}
