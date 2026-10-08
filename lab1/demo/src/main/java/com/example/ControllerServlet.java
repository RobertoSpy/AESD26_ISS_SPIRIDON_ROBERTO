package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ControllerServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String value = req.getParameter("value");

        LOG.info("method=" + req.getMethod()
                + ", ip=" + req.getRemoteAddr()
                + ", user-agent=" + req.getHeader("User-Agent")
                + ", languages=" + java.util.Collections.list(req.getLocales())
                + ", value=" + value);

        if ("desktop".equals(req.getParameter("client"))) {
            resp.setContentType("text/plain;charset=UTF-8");
            resp.getWriter().print(value);
            return;
        }

        if ("1".equals(value)) {
            req.getRequestDispatcher("/page1.html").forward(req, resp);
        } else if ("2".equals(value)) {
            req.getRequestDispatcher("/page2.html").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "value must be 1 or 2");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
