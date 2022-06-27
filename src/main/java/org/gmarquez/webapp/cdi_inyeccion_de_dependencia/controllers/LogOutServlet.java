package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet( "/cdi_inyeccion_de_dependencia/cerrar_session")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Optional<String> sessionOptional = session.getAttribute("nombreUsuario") != null ? Optional.of((String) session.getAttribute("nombreUsuario")) : Optional.empty();

        if (sessionOptional.isPresent()) {
            // elimina todas las variables de la sesion
            req.getSession().invalidate();
            // redireccionamos al inicio
            resp.sendRedirect(getServletContext().getContextPath() + "/cdi_inyeccion_de_dependencia/inicio");
        }
    }
}
