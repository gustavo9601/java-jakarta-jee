package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.services.ProductoService;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/cdi_inyeccion_de_dependencia/producto_eliminar")
public class ProductoEliminarServlet extends HttpServlet {
    @Inject
    @Named("productoServiceJdcImpl")
    private ProductoService productoService;

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

            // Connection connection = (Connection) req.getAttribute("connection");
            // ProductoService productoService = new ProductoServiceJdbcImpl(connection);
            this.productoService.eliminar(id);
            resp.sendRedirect(req.getContextPath() + "/cdi_inyeccion_de_dependencia/tabla_productos");

        }
    }
}
