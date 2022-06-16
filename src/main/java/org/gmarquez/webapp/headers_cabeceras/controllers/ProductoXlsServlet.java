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

@WebServlet("/cabeceras/producto_xls")
public class ProductoXlsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/vnd.ms-excel"); // Seteamos la cebecera para que responda un excel
        resp.setHeader("Content-Disposition", "attachment;filename=productos.xls");

        ProductoService productoService = new ProductoServiceImlp();
        List<Producto> productos = productoService.listar();


        req.setAttribute("productos", productos); // Seteamos el atributo productos para que se pueda usar en la vista

        getServletContext().getRequestDispatcher("/cabeceras/producto_xls.jsp").forward(req, resp);
    }
}
