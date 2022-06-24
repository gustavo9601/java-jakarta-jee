package org.gmarquez.webapp.base_de_datos_filters.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.gmarquez.webapp.base_de_datos_filters.repositories.UsuarioRepository;
import org.gmarquez.webapp.base_de_datos_filters.repositories.UsuarioRepositoryImpl;
import org.gmarquez.webapp.base_de_datos_filters.services.UsuarioService;
import org.gmarquez.webapp.base_de_datos_filters.services.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;


@WebServlet("/base_de_datos_filter/login")
public class LoginHttpServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Optional<String> sessionOptional = session.getAttribute("nombreUsuario") != null ? Optional.of((String) session.getAttribute("nombreUsuario")) : Optional.empty();


        if (sessionOptional.isPresent()) { // si existe la sesion
            // redireccionamos a la tabla de productos
            resp.sendRedirect(req.getContextPath() + "/base_de_datos_filter/tabla_productos");
        } else {
            // Cargando la vista de login
            getServletContext().getRequestDispatcher("/base_de_datos_filter/formulario_login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuario = req.getParameter("usuario");
        String password = req.getParameter("password");

        Connection connection = (Connection) req.getAttribute("connection");
        UsuarioService usuarioService = new UsuarioServiceImpl(connection);

        if (usuarioService.login(usuario, password).isPresent()) {

            HttpSession session = req.getSession(); // Obtenemos la sesion
            session.setAttribute("nombreUsuario", usuario); //  guardamos la nueva variable

            resp.getWriter().write("<h1>Bienvenido " + usuario + "</h1>");
            resp.getWriter().write(" <a href='" + req.getContextPath() + "/base_de_datos_filter/inicio'>Inicio</a><br>");
            resp.getWriter().write(" <a href='" + req.getContextPath() + "/base_de_datos_filter/tabla_productos'>Tabla de productos</a>");

        } else {

            // SC_UNAUTHORIZED = 401
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario o contraseña incorrectos");

        }

    }
}
