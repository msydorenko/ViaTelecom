package ua.gmail.sydorenko.web.commandFactory;

import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;

import javax.servlet.http.HttpServletRequest;

public class UserUnsubscribeTariffCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws DaoSystemException {
        return null;
    }
}
