package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.User;

import javax.servlet.http.HttpServletRequest;

public class ClientDataCommand implements Command {
    private static final long serialVersionUID = -7092103951978477774L;
    private static final Logger LOG = Logger.getLogger(ClientDataCommand.class);


    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'client data' starts");
        if(request.getParameter("idUserForChange") != null && !request.getParameter("idUserForChange").isEmpty()){
        UserDao userDao = new UserDaoImpl();
        BillDao billDao = new BillDaoImpl();
        AddressDao addressDao = new AddressDaoImpl();
        ContactDao contactDao = new ContactDaoImpl();

        int idUserForChange = Integer.parseInt(request.getParameter("idUserForChange"));
        User user = userDao.readById(idUserForChange).get(0);
        user.setBill(billDao.readById(user.getBill().getId()).get(0));
        user.setAddress(addressDao.readById(user.getAddress().getId()).get(0));
        user.setContact(contactDao.readById(user.getContact().getId()).get(0));
        LOG.trace("Obtain user for change " + user);

        request.setAttribute("userForUpdate", user);}

        LOG.debug("Command 'client data' finished");
        return Path.PAGE_CLIENT_DATA;
    }
}
