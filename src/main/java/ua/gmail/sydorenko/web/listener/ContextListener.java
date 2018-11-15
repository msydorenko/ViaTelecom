package ua.gmail.sydorenko.web.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class ContextListener implements ServletContextListener {
    private final static Logger LOG = Logger.getLogger(ContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent event) {
        LOG.trace("ContextListener start init process");
        ServletContext servletContext = event.getServletContext();
        initLod4j(servletContext);
        initI18N(servletContext);
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
}
