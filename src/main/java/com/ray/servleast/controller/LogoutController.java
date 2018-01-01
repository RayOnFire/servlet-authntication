package com.ray.servleast.controller;

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
@WebServlet(name = "LogoutController", urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object isAuthenticated = req.getSession().getAttribute("authenticated");
        PrintWriter writer = resp.getWriter();
        if (isAuthenticated != null && (Boolean)isAuthenticated) {
            req.getSession().invalidate();
            resp.setStatus(200);
            writer.println("Logout Successful");
        } else {
            // 401 Unauthorized
            resp.setStatus(401);
            writer.println("You Are Not Login");
        }
    }
}
