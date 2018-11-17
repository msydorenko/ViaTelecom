package ua.gmail.sydorenko;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.web.command.Command;
import ua.gmail.sydorenko.web.command.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    public static final Logger LOG = Logger.getLogger(Controller.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DaoSystemException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DaoSystemException e) {
            e.printStackTrace();
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DaoSystemException {
        LOG.debug("Controller start");

        ServletContext servletContext = this.getServletContext();
        CommandFactory commandFactory = (CommandFactory) servletContext.getAttribute("commandFactory");
        Command command = commandFactory.getCommand(request);
        LOG.trace("Retrieve command " + command);

        String forward = command.execute(request);
        LOG.trace("Forward page " + forward);
        if (forward != null || !forward.equals("")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
            dispatcher.forward(request, response);
        }
    }
}
