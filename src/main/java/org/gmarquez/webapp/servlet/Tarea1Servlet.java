package org.gmarquez.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/tarea_1")
public class Tarea1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();
        LocalDate localDate = LocalDate.now();

        writer.println("<h1>Tarea 1: Servlet y envio d parametros");
        writer.println("<h2>Nombre completo: " + req.getParameter("nombre") + " " + req.getParameter("apellido") + "</h2>");
        writer.println("<h3>La fecha actual es: " + localDate.format(DateTimeFormatter.ofPattern("dd 'de' MMMM, yyyy")) +"</h3>");


        writer.close();
    }
}
