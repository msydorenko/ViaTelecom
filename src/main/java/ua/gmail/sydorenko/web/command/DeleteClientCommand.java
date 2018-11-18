package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author M.Sydorenko
 */
public class DeleteClientCommand extends GeneralCommand {
    private static final long serialVersionUID = 682034925387675499L;
    private static final Logger LOG = Logger.getLogger(DeleteClientCommand.class);

    public DeleteClientCommand(AddressDao addressDao, BillDao billDao, ContactDao contactDao, ServiceDao serviceDao, TariffDao tariffDao, UserDao userDao) {
        super(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao);
    }

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'delete client' starts");
        int idUserForChange = Integer.parseInt(request.getParameter("idUserForChange"));
        LOG.trace("Id client for delete: " + idUserForChange);
        userDao.delete(idUserForChange);

        LOG.debug("Command 'delete client' finished");
        return Path.COMMAND_CLIENTS_LIST;
    }
}
