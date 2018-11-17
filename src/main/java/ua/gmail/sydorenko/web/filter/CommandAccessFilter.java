package ua.gmail.sydorenko.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class CommandAccessFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(CommandAccessFilter.class);

    public void destroy() {
        LOG.debug("CommandAccessFilter destruction starts");
        // NOP
        LOG.debug("CommandAccessFilter destruction finished");

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        LOG.debug("CommandAccessFilter destruction starts");
        // NOP
        LOG.debug("CommandAccessFilter destruction finished");
    }

}
