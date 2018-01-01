package com.ray.servleast.controller;

import com.ray.servleast.exception.BadCredentialException;
import com.ray.servleast.exception.ParameterNotPresentException;
import com.ray.servleast.exception.PermissionDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ray on 2017/12/31.
 */
@WebServlet(name = "ErrorController", urlPatterns = {"/error"})
public class ErrorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Exception exception = (Exception) req.getAttribute("javax.servlet.error.exception");
        if (exception instanceof ParameterNotPresentException || exception instanceof BadCredentialException) {
            resp.setStatus(400);
        }
        if (exception instanceof PermissionDeniedException) {
            resp.setStatus(403);
        }
        PrintWriter writer = resp.getWriter();
        writer.println(exception.getMessage());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
