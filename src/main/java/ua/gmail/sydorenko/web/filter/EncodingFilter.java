package ua.gmail.sydorenko.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Encoding filter for convert text to a UTF-8 encoding.
 *
 * @author M.Sydorenko
 */
public class EncodingFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(EncodingFilter.class);
    private String encoding;

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        LOG.debug("Filter starts");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        LOG.trace("Request uri --> " + httpRequest.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            LOG.trace("Request encoding = null, set encoding --> " + encoding);
            request.setCharacterEncoding(encoding);
        }

        LOG.debug("Filter finished");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        LOG.debug("Filter initialization starts");
        encoding = fConfig.getInitParameter("encoding");
        LOG.trace("Encoding from web.xml --> " + encoding);
        LOG.debug("Filter initialization finished");
    }

    public void destroy() {
        LOG.debug("Filter destruction starts");
        // NOP
        LOG.debug("Filter destruction finished");
    }

}
