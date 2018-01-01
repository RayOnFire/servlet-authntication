package com.ray.servleast.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ray on 2017/12/29.
 */
@WebFilter(servletNames = {"WelcomeController"})
public class WelcomeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Object isAuthenticated = request.getSession().getAttribute("authenticated");
        if (isAuthenticated != null && (Boolean) isAuthenticated) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse)servletResponse).setStatus(401);
            PrintWriter writer = servletResponse.getWriter();
            writer.println("Unauthorized!");
        }
    }

    @Override
    public void destroy() {

    }
}
