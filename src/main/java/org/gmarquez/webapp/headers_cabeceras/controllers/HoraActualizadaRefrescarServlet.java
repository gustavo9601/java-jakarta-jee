package org.gmarquez.webapp.headers_cabeceras.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/cabeceras/hora_actualizada_refresh")
public class HoraActualizadaRefrescarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Refresh", "1"); // se actualiza cada 1 segundo la pagina

        LocalTime horaActual = LocalTime.now();

        resp.getWriter().write("<h1>Hora actualizada</h1>");
        resp.getWriter().write("<p>Hora: " + horaActual.format(DateTimeFormatter.ofPattern("hh::mm:ss")) + "</p>");
        resp.getWriter().write("<a href='/java-jakarta-jee/cabeceras/inicio'>Inicio</a>");
    }

}
