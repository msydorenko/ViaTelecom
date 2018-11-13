package ua.gmail.sydorenko.web.command;

import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Tariff;
import ua.gmail.sydorenko.util.PdfCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DownloadCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("tariffList") != null) {
            List<Tariff> tariffList = (List<Tariff>) session.getAttribute("tariffList");
            PdfCreator.downloadTariffs(tariffList);
        }
        return Path.PAGE_MAIN;
    }
}
