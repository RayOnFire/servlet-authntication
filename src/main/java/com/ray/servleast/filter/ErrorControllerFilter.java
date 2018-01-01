package com.ray.servleast.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Ray on 2017/12/31.
 */
@WebFilter(filterName = "ErrorControllerFilter", servletNames = {"ErrorController"}, dispatcherTypes = {DispatcherType.ERROR})
public class ErrorControllerFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");
        Exception exception = (Exception) req.getAttribute("javax.servlet.error.exception");
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        logger.info(requestUri + "-" + servletName + "-" + statusCode + "-" + exception);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}