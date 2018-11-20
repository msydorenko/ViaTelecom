package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.*;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Tariff;
import ua.gmail.sydorenko.util.Sorter;
import ua.gmail.sydorenko.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * @author M.Sydorenko
 */
public class SortCommand extends GeneralCommand {
    private static final long serialVersionUID = -1622239054448131571L;
    private static final Logger LOG = Logger.getLogger(SortCommand.class);

    public SortCommand(AddressDao addressDao, BillDao billDao, ContactDao contactDao, ServiceDao serviceDao, TariffDao tariffDao, UserDao userDao) {
        super(addressDao, billDao, contactDao, serviceDao, tariffDao, userDao);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException {
        LOG.debug("Command 'sort command' starts");

        String value = request.getParameter("value");
        HttpSession session = request.getSession(false);
        List<Tariff> tariffList = tariffDao.readAll();
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
        LOG.debug("Command 'sort command' finished");
        return Path.PAGE_MAIN;
    }
}
