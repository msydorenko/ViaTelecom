package ua.gmail.sydorenko.web.commandFactory;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

/**
 * @author M.Sydorenko
 */
public class I18NCommand implements Command {
    private static final long serialVersionUID = -3165651694385267236L;
    private static final Logger LOG = Logger.getLogger(I18NCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.debug("Command I18N starts");
        String localeToSet = request.getParameter("i18n");
        String fmtLocale = "javax.servlet.jsp.jstl.fmt.locale";
        LOG.trace("Locale from request: " + localeToSet);
        if (localeToSet != null && !localeToSet.isEmpty()) {
            HttpSession session = request.getSession();
            Config.set(session, fmtLocale, localeToSet);
            session.setAttribute("defaultLocale", localeToSet);
        }
        LOG.debug("Command I18 finished");
        return Path.PAGE_MAIN;
    }
}