package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.Producto;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.services.ProductoService;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

// {} // pasando un array de rutas este contralador escuchara ambas
@WebServlet({"/cdi_inyeccion_de_dependencia/tabla_productos", "/cdi_inyeccion_de_dependencia/productos"})
public class TablaProductosServlet extends HttpServlet {

    @Inject
    @Named("productoServiceJdcImpl")
    private ProductoService productoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Optional<String> sessionOptional = session.getAttribute("nombreUsuario") != null ? Optional.of((String) session.getAttribute("nombreUsuario")) : Optional.empty();

        if (sessionOptional.isPresent()) { // si existe la cookie
            // redireccionamos a la tabla de productos
            resp.setContentType("text/html;charset=UTF-8");

            // Capturamos la conexxion del atributo seteado en el filtro
            // Connection connection = (Connection) req.getAttribute("connection");
            // System.out.println("conexion en tabla=\t" + connection);

            //ProductoService productoService = new ProductoServiceJdbcImpl(connection);
            List<Producto> productos = this.productoService.listar();

            req.setAttribute("nombreUsuario", sessionOptional.get()); // Le enviamos el valor de la cookie
            req.setAttribute("productos", productos); // Seteamos el atributo productos para que se pueda usar en la vista
            getServletContext().getRequestDispatcher("/cdi_inyeccion_de_dependencia/tabla_productos.jsp").forward(req, resp);
        }else{
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Por favor inicio sesion");
        }


    }
}
