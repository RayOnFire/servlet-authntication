package com.ray.servleast.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ray on 2017/12/30.
 */
@WebServlet(name = "IndexController", urlPatterns = {"/"}, initParams = {@WebInitParam(name = "annotation-param", value = "annotation-param")})
public class IndexController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        PrintWriter writer = resp.getWriter();
        writer.println(getInitParameter("annotation-param"));
        writer.println(getInitParameter("servlet-param"));
        writer.println(req.getServletContext().getInitParameter("context-param"));
    }
}
