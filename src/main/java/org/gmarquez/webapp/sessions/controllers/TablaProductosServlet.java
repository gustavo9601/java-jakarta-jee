package org.gmarquez.webapp.sessions.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.gmarquez.webapp.sessions.models.Producto;
import org.gmarquez.webapp.sessions.services.ProductoService;
import org.gmarquez.webapp.sessions.services.ProductoServiceImlp;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

// {} // pasando un array de rutas este contralador escuchara ambas
@WebServlet({"/sessions/tabla_productos", "/sessions/productos"})
public class TablaProductosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Optional<String> sessionOptional = session.getAttribute("nombreUsuario") != null ? Optional.of((String) session.getAttribute("nombreUsuario")) : Optional.empty();

        if (sessionOptional.isPresent()) { // si existe la cookie
            // redireccionamos a la tabla de productos
            resp.setContentType("text/html;charset=UTF-8");

            ProductoService productoService = new ProductoServiceImlp();
            List<Producto> productos = productoService.listar();

            req.setAttribute("nombreUsuario", sessionOptional.get()); // Le enviamos el valor de la cookie
            req.setAttribute("productos", productos); // Seteamos el atributo productos para que se pueda usar en la vista
            getServletContext().getRequestDispatcher("/sessions/tabla_productos.jsp").forward(req, resp);
        }else{
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Por favor inicio sesion");
        }


    }
}
