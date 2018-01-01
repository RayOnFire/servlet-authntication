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
@WebServlet(name = "WelcomeController", urlPatterns = {"/welcome"})
public class WelcomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("welcome!");
        writer.println(req.getServletContext().getInitParameter("context-param"));
    }
}
