package org.gmarquez.webapp.headers_cabeceras.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream(); // capturamos el json que se envia por el cliente


        // Deserializamos el json a un objeto
        ObjectMapper mapper = new ObjectMapper();
        // Creamos el objeto proeducto desde el mapper y el json
        Producto producto = mapper.readValue(jsonStream, Producto.class);


        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("<h1>Producto recibido:</h1>");
        resp.getWriter().write("<p>Nombre: " + producto.getNombre() + "</p>");
        resp.getWriter().write("<p>Precio: " + producto.getPrecio() + "</p>");
        resp.getWriter().write("<p>Descripcion: " + producto.getDescripcion() + "</p>");


    }
}
