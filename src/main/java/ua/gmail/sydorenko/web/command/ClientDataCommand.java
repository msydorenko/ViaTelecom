package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.User;

import javax.servlet.http.HttpServletRequest;

public class ClientDataCommand extends GeneralCommand {
    private static final long serialVersionUID = -7092103951978477774L;
    private static final Logger LOG = Logger.getLogger(ClientDataCommand.class);

    public ClientDataCommand(AddressDao addressDao, BillDao billDao, ContactDao contactDao, ServiceDao serviceDao, TariffDao tariffDao, UserDao userDao) {
        super(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao);
    }

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'client data' starts");
        if (request.getParameter("idUserForChange") != null && !request.getParameter("idUserForChange").isEmpty()) {

            int idUserForChange = Integer.parseInt(request.getParameter("idUserForChange"));
            User user = userDao.readById(idUserForChange).get(0);
            user.setBill(billDao.readById(user.getBill().getId()).get(0));
            user.setAddress(addressDao.readById(user.getAddress().getId()).get(0));
            user.setContact(contactDao.readById(user.getContact().getId()).get(0));
            LOG.trace("Obtain user for change " + user);

            request.setAttribute("userForUpdate", user);
        }

        LOG.debug("Command 'client data' finished");
        return Path.PAGE_CLIENT_DATA;
    }
}
