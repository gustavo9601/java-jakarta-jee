package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cdi_inyeccion_de_dependencia/ver-carro")
public class VerCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Renderizando la vista del carro
        getServletContext().getRequestDispatcher("/cdi_inyeccion_de_dependencia/carro.jsp").forward(req, resp);
    }
}
