package org.gmarquez.webapp.base_de_datos_filters.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/base_de_datos_filter/ver-carro")
public class VerCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Renderizando la vista del carro
        getServletContext().getRequestDispatcher("/base_de_datos_filter/carro.jsp").forward(req, resp);
    }
}
