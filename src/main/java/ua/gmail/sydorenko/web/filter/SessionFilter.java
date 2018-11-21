package ua.gmail.sydorenko.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Session filter for checking if session exist and if not send to a index page.
 *
 * @author M.Sydorenko
 */
public class SessionFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(SessionFilter.class);

    public void destroy() {
        LOG.debug("SessionFilter destruction starts");
        // NOP
        LOG.debug("SessionFilter destruction finished");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        LOG.debug("SessionFilter starts");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        if (session != null) {
            LOG.trace("Session exist");
            chain.doFilter(request, response);
        } else {
            LOG.trace("Session finished");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        LOG.debug("SessionFilter destruction starts");
        // NOP
        LOG.debug("SessionFilter destruction finished");
    }

}
