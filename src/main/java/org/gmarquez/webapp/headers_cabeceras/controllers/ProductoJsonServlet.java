package org.gmarquez.webapp.headers_cabeceras.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebServlet("/cabeceras/json")
public class ProductoJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductoService productoService = new ProductoServiceImlp();
        List<Producto> productos = productoService.listar();

        // Serializamos el objeto a un string
        ObjectMapper mapper = new ObjectMapper();
        String productosSerializados = mapper.writeValueAsString(productos);

        resp.setContentType("application/json"); // Seteamos la cebecera para que responda un json
        resp.getWriter().write(productosSerializados);

    }
}
