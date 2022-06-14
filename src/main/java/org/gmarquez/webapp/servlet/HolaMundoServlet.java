package org.gmarquez.webapp.servlet;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

// Mapeo de la ruta /hola_mundo que respondera al endpoint
@WebServlet("/hola_mundo")
public class HolaMundoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); // Modifcaremos el documento html

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("    <head>");
        out.println("        <meta charset=\"UTF-8\">");
        out.println("        <title>Hola Mundo Servlet</title>");
        out.println("    </head>");
        out.println("    <body>");
        out.println("        <h1>Hola Mundo Servlet!</h1>");
        out.println("    </body>");
        out.println("</html>");
        out.close();
    }
}
