package ua.gmail.sydorenko.web.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.gmail.sydorenko.database.DBManager;
import ua.gmail.sydorenko.database.MySQLManager;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.template.*;
import ua.gmail.sydorenko.web.command.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

/**
 * Listener for initialization of DBManager, DAO, logger, command factory and internationalization.
 *
 * @author M.Sydorenko
 */
public class ContextListener implements ServletContextListener {
    private final static Logger LOG = Logger.getLogger(ContextListener.class);
    private AddressDao addressDao;
    private BillDao billDao;
    private ContactDao contactDao;
    private ServiceDao serviceDao;
    private TariffDao tariffDao;
    private UserDao userDao;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        LOG.trace("ContextListener start init process");
        ServletContext servletContext = event.getServletContext();
        initLod4j(servletContext);
        initI18N(servletContext);
        initDao();
        initCommandFactory(servletContext);
        LOG.trace("Initialization has finished successfully");
    }

    /**
     * Initializes log4j framework.
     *
     * @param servletContext
     */
    private void initLod4j(ServletContext servletContext) {
        LOG.trace("Start init Log4j");
        try {
            PropertyConfigurator.configure(servletContext.getRealPath("log4j.properties"));
        } catch (Exception e) {
            LOG.warn("Properties for log4j don't load", e);
        }
        LOG.trace("Log4J initialization finished");
    }

    /**
     * Initializes i18n subsystem.
     */
    private void initI18N(ServletContext servletContext) {
        LOG.trace("I18N initialisation start");
        String locales = servletContext.getInitParameter("locales");
        if (locales == null || locales.isEmpty()) {
            LOG.warn("'locales' init parameter is empty, the default encoding will be used");
        } else {
            List<String> localeList = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(locales);
            while (st.hasMoreTokens()) {
                String locale = st.nextToken();
                localeList.add(locale);
            }
            LOG.debug("Application attribute set: locales --> " + locales);
            servletContext.setAttribute("locales", locales);
        }
        LOG.debug("I18N subsystem initialization finished");
    }

    /**
     * Initialize DAO.
     */
    private void initDao() {
        LOG.trace("DAO initialisation start");
        DBManager manager = new MySQLManager();
        addressDao = new AddressDaoImpl(manager, new AddressTemplate());
        billDao = new BillDaoImpl(manager, new BillTemplate());
        contactDao = new ContactDaoImpl(manager, new ContactTemplate());
        serviceDao = new ServiceDaoImpl(manager, new ServiceTemplate());
        tariffDao = new TariffDaoImpl(manager, new TariffTemplate());
        userDao = new UserDaoImpl(manager, new UserTemplate());
        LOG.debug("DAO initialization finished");
    }

    /**
     * Initialize command factory.
     *
     * @param servletContext
     */
    private void initCommandFactory(ServletContext servletContext) {
        LOG.trace("CommandFactory initialisation start");
        Map<String, Command> commandList = new HashMap<>();
        commandList.put("login", new LoginCommand(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao));
        commandList.put("main", new MainCommand(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao));
        commandList.put("addTariff", new AddTariffCommand(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao));
        commandList.put("tariffList", new TariffListCommand(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao));
        commandList.put("sort", new SortCommand(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao));
        commandList.put("editTariff", new EditTariffCommand(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao));
        commandList.put("deleteTariff", new DeleteTariffCommand(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao));
        commandList.put("openEditTariffPage", new OpenEditTariffPageCommand(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao));
        commandList.put("userSubscribeTariff", new UserSubscribeTariffCommand(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao));
        commandList.put("userUnsubscribeTariff", new UserUnsubscribeTariffCommand(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao));
        commandList.put("locale", new I18NCommand());
        commandList.put("download", new DownloadCommand());
        commandList.put("recharge", new RechargeCommand(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao));
        commandList.put("usersList", new UsersListCommand(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao));
        commandList.put("changeUserStatus", new ChangeUserStatusCommand(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao));
        commandList.put("clientData", new ClientDataCommand(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao));
        commandList.put("createOrUpdate", new CreateOrUpdateClientCommand(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao));
        commandList.put("deleteUser", new DeleteClientCommand(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao));
        commandList.put("noCommand", new NoCommand());
        commandList.put("logout", new LogoutCommand());
        commandList.put("openLoginPage", new OpenLoginCommand());
        commandList.put("error", new ErrorCommand());
        LOG.trace("Create list of command");

        CommandFactory commandFactory = new CommandFactory(commandList);
        servletContext.setAttribute("commandFactory", commandFactory);

        LOG.debug("CommandFactory initialization finished");
    }
}
