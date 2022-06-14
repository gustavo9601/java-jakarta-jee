package org.gmarquez.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/parametros/url_get")
public class ParametroGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String saludo = request.getParameter("saludo");
        String nombre = request.getParameter("nombre") == null ? "Anonimo" : request.getParameter("nombre");
        // System.out.println("saludo = " + saludo);
        PrintWriter out = response.getWriter(); // Modifcaremos el documento html

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("    <head>");
        out.println("        <meta charset=\"UTF-8\">");
        out.println("        <title>Paraemtro get Servlet</title>");
        out.println("    </head>");
        out.println("    <body>");
        out.println("        <h1>Saludo:\t" + saludo + "!</h1>");
        out.println("        <h1>Nombre:\t" + nombre + "!</h1>");
        out.println("    </body>");
        out.println("</html>");
        out.close();
    }
}
