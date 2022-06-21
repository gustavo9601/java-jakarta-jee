package org.gmarquez.webapp.base_de_datos_filters.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.gmarquez.webapp.base_de_datos_filters.models.Carro;
import org.gmarquez.webapp.base_de_datos_filters.models.ItemCarro;
import org.gmarquez.webapp.base_de_datos_filters.models.Producto;
import org.gmarquez.webapp.base_de_datos_filters.services.ProductoService;
import org.gmarquez.webapp.base_de_datos_filters.services.ProductoServiceJdbcImpl;


import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/base_de_datos_filter/agregar-item-carro-servlet")
public class AgregarItemCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        System.out.println("id = " + id);
        Connection connection = (Connection) req.getAttribute("connection");
        ProductoService productoService = new ProductoServiceJdbcImpl(connection);

        Optional<Producto> productoEncontrado = productoService.porId(id);
        System.out.println("productoEncontrado = " + productoEncontrado);

        if (productoEncontrado.isPresent()) {
            ItemCarro itemCarro = new ItemCarro(productoEncontrado.get(), 1);
            Carro carro;

            HttpSession session = req.getSession();
            System.out.println("*** carro antes ***");
            System.out.println(session.getAttribute("carro"));

            // La session siempre va a exisitir porque se crea en el listener de inicio de sesion
            carro = (Carro) session.getAttribute("carro");

            carro.addItemCarro(itemCarro);
            session.setAttribute("carro", carro);

            System.out.println("*** carro despues ***");
            System.out.println(session.getAttribute("carro"));
            System.out.println("cantidad productos =\t" + ((Carro) session.getAttribute("carro")).cantidadProductos());
        }

        // Redireccionadno para ver el carro
        resp.sendRedirect(req.getContextPath() + "/base_de_datos_filter/ver-carro");

    }
}
