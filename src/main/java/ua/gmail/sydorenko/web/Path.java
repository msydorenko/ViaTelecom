package ua.gmail.sydorenko.web;

/**
 * Path to all pages.
 *
 * @author M.Sydorenko
 */
public interface Path {

    String PAGE_INDEX = "/index.jsp";
    String PAGE_LOGIN = "/login.jsp";
    String PAGE_EDIT_TARIFF = "/WEB-INF/jsp/editTariff.jsp";
    String PAGE_MAIN = "/WEB-INF/jsp/main.jsp";
    String PAGE_CLIENTS = "/WEB-INF/jsp/clients.jsp";
    String PAGE_CLIENT_DATA = "/WEB-INF/jsp/clientData.jsp";
    String PAGE_ERROR = "/error.jsp";

/*
    String PAGE_CLIENT_DATA = "/WEB-INF/jsp/editClient!!!.jsp";
*/

    String COMMAND_ERROR = "viatelecom?command=error";
    String COMMAND_MAIN = "viatelecom?command=main";
    String COMMAND_CLIENTS_LIST = "viatelecom?command=usersList";
    String COMMAND_CLIENT_DATAS = "viatelecom?command=clientData";
}
