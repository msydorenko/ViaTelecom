package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Tariff;
import ua.gmail.sydorenko.util.PdfCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DownloadCommand extends GeneralCommand {
    private static final long serialVersionUID = -5868243282075555305L;
    private static final Logger LOG = Logger.getLogger(DownloadCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        LOG.debug("Command 'download tariff's list' starts");

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("tariffList") != null) {
            List<Tariff> tariffList = (List<Tariff>) session.getAttribute("tariffList");
            PdfCreator.downloadTariffs(tariffList);
        }
        LOG.debug("Command 'download tariff's list' finished");
        return Path.PAGE_MAIN;
    }
}
