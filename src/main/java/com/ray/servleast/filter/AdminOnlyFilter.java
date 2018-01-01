package com.ray.servleast.filter;

import com.ray.servleast.dao.User;
import com.ray.servleast.exception.PermissionDeniedException;
import com.ray.servleast.model.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by Ray on 2017/12/31.
 */
@WebFilter(filterName = "AdminOnlyFilter", urlPatterns = {"/admin"})
public class AdminOnlyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User currentUser = (User) servletRequest.getServletContext().getAttribute("user");
        if (currentUser != null && currentUser.getRoles().contains(Role.ROLE_ADMIN)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            throw new PermissionDeniedException("Permission Denied");
        }
    }

    @Override
    public void destroy() {

    }
}
