package org.gmarquez.webapp.base_de_datos_filters.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gmarquez.webapp.base_de_datos_filters.models.Categoria;
import org.gmarquez.webapp.base_de_datos_filters.models.Producto;
import org.gmarquez.webapp.base_de_datos_filters.services.ProductoService;
import org.gmarquez.webapp.base_de_datos_filters.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/base_de_datos_filter/producto_form")
public class ProductoFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ProductoService productoService = new ProductoServiceJdbcImpl(connection);
        List<Categoria> categorias = productoService.listarCategorias();

        req.setAttribute("categorias", categorias);
        getServletContext().getRequestDispatcher("/base_de_datos_filter/producto_form.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Producto producto = new Producto();
        producto.setNombre(req.getParameter("nombre"));
        producto.setPrecio(Long.parseLong(req.getParameter("precio")));
        producto.setSku(req.getParameter("sku"));
        producto.setCategoria(new Categoria(Long.parseLong(req.getParameter("categoria_id")), ""));
        producto.setFechaRegistro(LocalDate.parse(req.getParameter("fecha_registro"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        Connection connection = (Connection) req.getAttribute("connection");
        ProductoService productoService = new ProductoServiceJdbcImpl(connection);
        productoService.guardar(producto);

        // Se redirije para evitar al recargar el servicio se vuelva a crear
        resp.sendRedirect(getServletContext().getContextPath() + "/base_de_datos_filter/tabla_productos");
    }
}
