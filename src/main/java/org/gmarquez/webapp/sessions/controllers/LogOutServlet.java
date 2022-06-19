package org.gmarquez.webapp.sessions.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebServlet("/sessions/cerrar_session")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Optional<String> sessionOptional = session.getAttribute("nombreUsuario") != null ? Optional.of((String) session.getAttribute("nombreUsuario")) : Optional.empty();

        if (sessionOptional.isPresent()) {
            // elimina todas las variables de la sesion
            req.getSession().invalidate();
            // redireccionamos al inicio
            resp.sendRedirect("/java-jakarta-jee/sessions/inicio");
        }
    }
}
