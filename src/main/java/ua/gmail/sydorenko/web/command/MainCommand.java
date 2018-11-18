package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Service;
import ua.gmail.sydorenko.database.entity.Tariff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author M.Sydorenko
 */
public class MainCommand extends GeneralCommand {
    private static final long serialVersionUID = -4461981504404538233L;
    private static final Logger LOG = Logger.getLogger(MainCommand.class);

    public MainCommand(AddressDao addressDao, BillDao billDao, ContactDao contactDao, ServiceDao serviceDao, TariffDao tariffDao, UserDao userDao) {
        super(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException {
        LOG.debug("Command 'main' starts");

        HttpSession session = request.getSession(false);

        List<Service> serviceList = serviceDao.readAll();
        List<Tariff> tariffList = tariffDao.readAll();
        LOG.trace("Obtain list of services and list of tariffs");

        session.setAttribute("tariffList", tariffList);
        session.setAttribute("serviceList", serviceList);
        LOG.debug("Command 'main' was successfully finished");

        return Path.PAGE_MAIN;
    }
}
