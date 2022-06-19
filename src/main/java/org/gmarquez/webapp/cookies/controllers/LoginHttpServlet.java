package org.gmarquez.webapp.cookies.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;


@WebServlet("/cookies/login")
public class LoginHttpServlet extends HttpServlet {

    final static String USUARIO = "g";
    final static String PASSWORD = "1";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0]; // capturamos las cookies
        Optional<Cookie> cookieOptional = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("nombreUsuario")) // filtramos las cookies por el nombre usuario // getName() es el nombre de la cookie
                .findAny();

        if (cookieOptional.isPresent()) { // si existe la cookie
            // redireccionamos a la tabla de productos
            resp.sendRedirect("/java-jakarta-jee/cookies/tabla_productos");
        } else {
            // Cargando la vista de login
            getServletContext().getRequestDispatcher("/cookies/formulario_login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuario = req.getParameter("usuario");
        String password = req.getParameter("password");
        if (usuario.equals(USUARIO) && password.equals(PASSWORD)) {

            Cookie nombreUsuarioCookie = new Cookie("nombreUsuario", usuario); // crea cookie sin fecha de expericaion
            resp.addCookie(nombreUsuarioCookie); // la añade a las respuesta

            resp.getWriter().write("<h1>Bienvenido " + usuario + "</h1>");
            resp.getWriter().write(" <a href='/java-jakarta-jee/cookies/inicio'>Inicio</a><br>");
            resp.getWriter().write(" <a href='/java-jakarta-jee/cookies/tabla_productos'>Tabla de productos</a>");

        } else {

            // SC_UNAUTHORIZED = 401
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario o contraseña incorrectos");

        }

    }
}
