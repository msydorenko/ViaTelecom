package ua.gmail.sydorenko.web.command;

import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Tariff;
import ua.gmail.sydorenko.util.Sorter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * @author M.Sydorenko
 */
public class SortCommand implements Command {
    private static final long serialVersionUID = -1622239054448131571L;

    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        String value = request.getParameter("value");
        HttpSession session = request.getSession(false);
        List<Tariff> tariffList = (List<Tariff>) session.getAttribute("tariffList");
        switch (value) {
            case "az": {
                Collections.sort(tariffList, Sorter.SORT_BY_NAME_AZ);
                break;
            }
            case "za": {
                Collections.sort(tariffList, Sorter.SORT_BY_NAME_ZA);
                break;
            }
            case "min": {
                Collections.sort(tariffList, Sorter.SORT_BY_PRICE_MIN_MAX);
                break;
            }
            case "max": {
                Collections.sort(tariffList, Sorter.SORT_BY_PRICE_MAX_MIN);
                break;
            }
        }
        session.setAttribute("tariffList", tariffList);
        return Path.PAGE_MAIN;
    }
}
