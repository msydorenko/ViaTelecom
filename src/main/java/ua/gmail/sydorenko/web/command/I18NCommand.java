package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

/**
 * Command for changing the local language between english and russian.
 *
 * @author M.Sydorenko
 */
public class I18NCommand extends GeneralCommand {
    private static final long serialVersionUID = -3165651694385267236L;
    private static final Logger LOG = Logger.getLogger(I18NCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command I18N starts");
        String forward = null;
        String localeToSet = request.getParameter("i18n");
        String fmtLocale = "javax.servlet.jsp.jstl.fmt.locale";
        LOG.trace("Locale from request: " + localeToSet);
        if (localeToSet != null && !localeToSet.isEmpty()) {
            HttpSession session = request.getSession();
            Config.set(session, fmtLocale, localeToSet);
            session.setAttribute("defaultLocale", localeToSet);
        }
        String page = request.getParameter("page");
        switch (page) {
            case "main": {
                forward = Path.PAGE_MAIN;
                break;
            }
            case "index": {
                forward = Path.PAGE_INDEX;
                break;
            }
            case "clients": {
                forward = Path.PAGE_CLIENTS;
                break;
            }
        }
        LOG.debug("Command I18 finished");
        return forward;
    }
}