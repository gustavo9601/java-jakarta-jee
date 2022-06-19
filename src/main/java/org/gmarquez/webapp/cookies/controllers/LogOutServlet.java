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

@WebServlet("/cookies/cerrar_session")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0]; // capturamos las cookies

        Optional<Cookie> cookieEncontrada = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("nombreUsuario"))
                .findAny(); // filtramos las cookies por el nombre usuario // getName() es el nombre de la cookie

        if (cookieEncontrada.isPresent()) {
            // eliminamos la cookie
            Cookie nuevaCookie = new Cookie("nombreUsuario", ""); // sin valor
            nuevaCookie.setMaxAge(0); // al no tener duracion se eliminaria
            resp.addCookie(nuevaCookie); // la añade a las respuesta

            // redireccionamos al inicio
            resp.sendRedirect("/java-jakarta-jee/cookies/inicio");
        }
    }
}
