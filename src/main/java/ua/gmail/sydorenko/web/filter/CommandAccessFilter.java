package ua.gmail.sydorenko.web.filter;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.entity.Role;
import ua.gmail.sydorenko.web.command.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author M.Sydorenko
 */

public class CommandAccessFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(CommandAccessFilter.class);
    private Map<Role, List<String>> accessMap = new HashMap<>();
    private static List<String> commons = new ArrayList<String>();
    private static List<String> outOfControl = new ArrayList<String>();

    public void destroy() {
        LOG.debug("CommandAccessFilter destruction starts");
        // NOP
        LOG.debug("CommandAccessFilter destruction finished");

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        LOG.debug("Filter starts");
        if (accessAllowed(req)) {
            LOG.debug("Filter finished");
            chain.doFilter(req, resp);
        } else {
            LOG.warn("Access isn't alowed");
            req.getRequestDispatcher(Path.PAGE_ERROR).forward(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        LOG.debug("CommandAccessFilter destruction starts");
        outOfControl.add("locale");
        outOfControl.add("login");
        outOfControl.add("openLoginPage");
        LOG.trace("List out of control: " + outOfControl);

        commons.add("main");
        commons.add("tariffList");
        commons.add("sort");
        commons.add("locale");
        commons.add("download");
        commons.add("noCommand");
        commons.add("logout");
        LOG.trace("Commons list: " + commons);

        accessMap.put(Role.ADMIN, adminList());
        accessMap.put(Role.CLIENT, clientList());
        LOG.trace("Access map: " + accessMap);
        LOG.debug("CommandAccessFilter destruction finished");
    }

    private boolean accessAllowed(ServletRequest req) {
        HttpServletRequest request = (HttpServletRequest) req;
        String commandName = request.getParameter("command");
        LOG.trace("Command from request: " + commandName);

        if (commandName == null || commandName.isEmpty()) {
            return false;
        }

        if (outOfControl.contains(commandName)) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }

        Role role = (Role) session.getAttribute("userRole");
        if (role == null) {
            return false;
        }

        return accessMap.get(role).contains(commandName) || commons.contains(commandName);
    }

    private List<String> adminList() {
        List<String> adminList = new ArrayList<>();
        adminList.add("addTariff");
        adminList.add("editTariff");
        adminList.add("deleteTariff");
        adminList.add("openEditTariffPage");
        adminList.add("usersList");
        adminList.add("changeUserStatus");
        adminList.add("clientData");
        adminList.add("createOrUpdate");
        adminList.add("deleteUser");
        return adminList;
    }

    private List<String> clientList() {
        List<String> clientList = new ArrayList<>();
        clientList.add("userSubscribeTariff");
        clientList.add("userUnsubscribeTariff");
        clientList.add("recharge");
        clientList.add("clientData");
        return clientList;
    }
}

