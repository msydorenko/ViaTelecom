package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Tariff;
import ua.gmail.sydorenko.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to add tariff to a database from html form.
 *
 * @author M.Sydorenko
 */
public class AddTariffCommand extends GeneralCommand {
    private static final long serialVersionUID = -2589405645948375128L;
    private static final Logger LOG = Logger.getLogger(AddTariffCommand.class);

    public AddTariffCommand(AddressDao addressDao, BillDao billDao, ContactDao contactDao, ServiceDao serviceDao, TariffDao tariffDao, UserDao userDao) {
        super(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException {
        LOG.debug("Command 'add tariff' starts");
        String forward = checkResubmit(request);
        LOG.trace("Check is successfully finished");
        if (forward.equals(Path.COMMAND_ERROR)) {
            return forward;
        }

        Tariff tariff = new Tariff();
        tariff.setSpr_service_id(Integer.parseInt(request.getParameter("service")));
        tariff.setName(request.getParameter("name"));
        tariff.setPrice(Integer.parseInt(request.getParameter("price")));
        tariff.setDescription(request.getParameter("description"));
        LOG.trace("Create new tariff: " + tariff);

        tariffDao.create(tariff);
        LOG.debug("Command 'add tariff' was successfully finished");

        return forward;
    }
}
