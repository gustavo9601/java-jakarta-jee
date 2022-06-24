package org.gmarquez.webapp.base_de_datos_filters.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gmarquez.webapp.base_de_datos_filters.services.ProductoService;
import org.gmarquez.webapp.base_de_datos_filters.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/base_de_datos_filter/producto_eliminar")
public class ProductoEliminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idProducto = req.getParameter("id");
        if (idProducto != null && !idProducto.isBlank()) {
            Long id;
            try {
                id = Long.parseLong(idProducto);
            } catch (NumberFormatException e) {
                id = 0L;
            }

            Connection connection = (Connection) req.getAttribute("connection");
            ProductoService productoService = new ProductoServiceJdbcImpl(connection);
            productoService.eliminar(id);
            resp.sendRedirect(req.getContextPath() + "/base_de_datos_filter/tabla_productos");

        }
    }
}
