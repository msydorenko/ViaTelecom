package ua.gmail.sydorenko.database.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.gmail.sydorenko.database.DBManager;
import ua.gmail.sydorenko.database.exception.DaoSystemException;
import ua.gmail.sydorenko.database.template.Template;
import ua.gmail.sydorenko.web.command.AddTariffCommand;
import ua.gmail.sydorenko.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class AddTariffCommandTest {
    private AddTariffCommand command;
    private AddressDao addressDao;
    private BillDao billDao;
    private ContactDao contactDao;
    private ServiceDao serviceDao;
    private TariffDao tariffDao;
    private UserDao userDao;
    private DBManager manager;
    private Template template;

    @Before
    public void init() {
        manager = mock(DBManager.class);
        template = mock(Template.class);
        addressDao = mock(AddressDao.class);
        billDao = mock(BillDao.class);
        contactDao = mock(ContactDao.class);
        serviceDao = mock(ServiceDao.class);
        tariffDao = mock(TariffDao.class);
        userDao = mock(UserDao.class);
        command = new AddTariffCommand(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao);
    }

    @Test
    public void testExecuteWithSeccessfulForward() throws DaoSystemException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(command.checkResubmit(request)).thenReturn(Path.COMMAND_MAIN);

        verify(request).getParameter("service");
        verify(request).getParameter("name");
        verify(request).getParameter("price");
        verify(request).getParameter("description");
    }
}
