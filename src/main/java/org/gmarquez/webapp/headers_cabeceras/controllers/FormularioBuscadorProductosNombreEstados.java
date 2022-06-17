package org.gmarquez.webapp.headers_cabeceras.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gmarquez.webapp.headers_cabeceras.models.Producto;
import org.gmarquez.webapp.headers_cabeceras.services.ProductoService;
import org.gmarquez.webapp.headers_cabeceras.services.ProductoServiceImlp;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/cabeceras/buscador_productos")
public class FormularioBuscadorProductosNombreEstados extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/cabeceras/formulario_buscador_productos.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreABuscar = req.getParameter("nombre");

        if (nombreABuscar == null || nombreABuscar.isBlank()) {
            // SC_BAD_REQUEST = 400
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El nombre no puede ser nulo o vacio");
        }

        ProductoService productoService = new ProductoServiceImlp();
        List<Producto> productos = productoService.listar();

        Optional<Producto> productoEncontrado = productos.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(nombreABuscar.toLowerCase()))
                .findFirst();

        if (productoEncontrado.isPresent()) {
            resp.getWriter().write("<h1>Producto encontrado</h1>");
            resp.getWriter().write("<p>Nombre: " + productoEncontrado.get().getNombre() + "</p>");
            resp.getWriter().write("<p>Precio: " + productoEncontrado.get().getPrecio() + "</p>");
            resp.getWriter().write("<a href='/java-jakarta-jee/cabeceras/inicio'>Inicio</a>");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontro el producto");
        }

    }
}
