package com.ray.servleast.controller;

import com.ray.servleast.exception.BadCredentialException;
import com.ray.servleast.exception.ParameterNotPresentException;
import com.ray.servleast.service.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ray on 2017/12/29.
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        PrintWriter writer = resp.getWriter();
        Object isAuthenticated = req.getSession().getAttribute("authenticated");
        if (isAuthenticated != null && (Boolean)isAuthenticated) {
            resp.setStatus(200);
            writer.println("Already Authenticated!");
        } else {
            if (username == null || password == null) {
                throw new ParameterNotPresentException("Necessary Parameter Not Presented");
            } else {
                if (AuthenticationService.authenticate(req.getServletContext(), username, password)) {
                    req.getSession().setAttribute("authenticated", true);
                    resp.setStatus(200);
                    writer.println("Authenticated!");
                } else {
                    throw new BadCredentialException("Bad Credential");
                }
            }
        }
    }
}
