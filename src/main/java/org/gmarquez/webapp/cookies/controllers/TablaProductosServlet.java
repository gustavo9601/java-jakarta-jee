package org.gmarquez.webapp.cookies.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gmarquez.webapp.cookies.models.Producto;
import org.gmarquez.webapp.cookies.services.ProductoService;
import org.gmarquez.webapp.cookies.services.ProductoServiceImlp;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// {} // pasando un array de rutas este contralador escuchara ambas
@WebServlet({"/cookies/tabla_productos", "/cookies/productos"})
public class TablaProductosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0]; // capturamos las cookies
        Optional<Cookie> cookieOptional = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("nombreUsuario")) // filtramos las cookies por el nombre usuario // getName() es el nombre de la cookie
                .findAny();

        if (cookieOptional.isPresent()) { // si existe la cookie
            // redireccionamos a la tabla de productos
            resp.setContentType("text/html;charset=UTF-8");

            ProductoService productoService = new ProductoServiceImlp();
            List<Producto> productos = productoService.listar();

            req.setAttribute("nombreUsuario", cookieOptional.get().getValue()); // Le enviamos el valor de la cookie
            req.setAttribute("productos", productos); // Seteamos el atributo productos para que se pueda usar en la vista
            getServletContext().getRequestDispatcher("/cookies/tabla_productos.jsp").forward(req, resp);
        }else{
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Por favor inicio sesion");
        }


    }
}
