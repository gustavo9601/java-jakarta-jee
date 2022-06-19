package org.gmarquez.webapp.listeners.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/listeners/inicio")
public class InicioListenersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Atributos definidos en el listener
        String mensajeRequest = (String) req.getAttribute("mensajeRequest");
        String mensajeGlobal = (String) req.getServletContext().getAttribute("mensajeGlobal");

        getServletContext().getRequestDispatcher("/listeners/inicio.jsp").forward(req, resp);
    }
}
