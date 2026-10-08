package com.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = { "", "/welcome" })
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset=\"UTF-8\"><title>Welcome</title></head><body>");
        out.println("<h1>Bine ai venit!</h1>");
        out.println("<p>Ora serverului: " + LocalDateTime.now() + "</p>");
        out.println("<form method=\"post\" action=\"controller\">");
        out.println("  <select name=\"value\">");
        out.println("    <option value=\"1\">1</option>");
        out.println("    <option value=\"2\">2</option>");
        out.println("  </select>");
        out.println("  <button type=\"submit\">Trimite</button>");
        out.println("</form>");
        out.println("</body></html>");
    }
}
