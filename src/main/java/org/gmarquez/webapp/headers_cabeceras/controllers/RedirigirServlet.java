package org.gmarquez.webapp.headers_cabeceras.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cabeceras/redirecion_a_hora_actualizada_refresh")
public class RedirigirServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/java-jakarta-jee/cabeceras/hora_actualizada_refresh");
    }
}
