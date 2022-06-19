package org.gmarquez.webapp.sessions.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;


@WebServlet("/sessions/login")
public class LoginHttpServlet extends HttpServlet {

    final static String USUARIO = "g";
    final static String PASSWORD = "1";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Optional<String> sessionOptional = session.getAttribute("nombreUsuario") != null ? Optional.of((String) session.getAttribute("nombreUsuario")) : Optional.empty();


        if (sessionOptional.isPresent()) { // si existe la sesion
            // redireccionamos a la tabla de productos
            resp.sendRedirect(req.getContextPath() + "/sessions/tabla_productos");
        } else {
            // Cargando la vista de login
            getServletContext().getRequestDispatcher("/sessions/formulario_login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuario = req.getParameter("usuario");
        String password = req.getParameter("password");
        if (usuario.equals(USUARIO) && password.equals(PASSWORD)) {

            HttpSession session = req.getSession(); // Obtenemos la sesion
            session.setAttribute("nombreUsuario", usuario); //  guardamos la nueva variable

            resp.getWriter().write("<h1>Bienvenido " + usuario + "</h1>");
            resp.getWriter().write(" <a href='" + req.getContextPath() + "/sessions/inicio'>Inicio</a><br>");
            resp.getWriter().write(" <a href='" + req.getContextPath() + "/sessions/tabla_productos'>Tabla de productos</a>");

        } else {

            // SC_UNAUTHORIZED = 401
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario o contraseña incorrectos");

        }

    }
}
