package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Tariff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author M.Sydorenko
 */
public class EditTariffCommand extends GeneralCommand {
    private static final long serialVersionUID = 8622466701403055217L;
    private static final Logger LOG = Logger.getLogger(EditTariffCommand.class);

    public EditTariffCommand(AddressDao addressDao, BillDao billDao, ContactDao contactDao, ServiceDao serviceDao, TariffDao tariffDao, UserDao userDao) {
        super(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao);
    }

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Edit tariff command start");

        Tariff tariff = new Tariff();
        tariff.setId(Integer.parseInt(request.getParameter("tariffIdForUpdate")));
        tariff.setSpr_service_id(Integer.parseInt(request.getParameter("tariffServiceId")));
        tariff.setName(request.getParameter("tariffName"));
        tariff.setPrice(Integer.parseInt(request.getParameter("tariffPrice")));
        tariff.setDescription(request.getParameter("tariffDescription"));
        LOG.trace("Create new value for tariff --> " + tariff);

        tariffDao.update(tariff);
        LOG.debug("Edit tariff was finished successfully");
        return Path.COMMAND_MAIN;
    }
}
