package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Tariff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author M.Sydorenko
 */
public class TariffListCommand extends GeneralCommand {
    private static final long serialVersionUID = -8021035582917973234L;
    private static final Logger LOG = Logger.getLogger(TariffListCommand.class);

    public TariffListCommand(AddressDao addressDao, BillDao billDao, ContactDao contactDao, ServiceDao serviceDao, TariffDao tariffDao, UserDao userDao) {
        super(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao);
    }

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'list of tariffs' starts");
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));

        List<Tariff> tariffList = tariffDao.readByIdService(serviceId);
        HttpSession session = request.getSession(false);
        session.setAttribute("tariffList", tariffList);
        LOG.debug("Command 'list of tariffs' finished");

        return Path.PAGE_MAIN;
    }
}
