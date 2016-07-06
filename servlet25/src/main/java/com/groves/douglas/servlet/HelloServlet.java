package com.groves.douglas.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Douglas Groves on 05/07/2016.
 */
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type", "application/xhtml+xml");
        resp.getOutputStream().println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        resp.getOutputStream().println("<html>");
        resp.getOutputStream().println("<body>");
        resp.getOutputStream().println("<p>Hello world!</p>");
        resp.getOutputStream().println("</body>");
        resp.getOutputStream().println("</html>");
    }
}
