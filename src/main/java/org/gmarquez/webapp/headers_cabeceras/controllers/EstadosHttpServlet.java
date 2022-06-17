package org.gmarquez.webapp.headers_cabeceras.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/cabeceras/estados_http")
public class EstadosHttpServlet extends HttpServlet {

    final static String USUARIO = "gmarquez";
    final static String PASSWORD = "123456";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/cabeceras/formulario_login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuario = req.getParameter("usuario");
        String password = req.getParameter("password");
        if (usuario.equals(USUARIO) && password.equals(PASSWORD)) {

            resp.getWriter().write("<h1>Bienvenido " + usuario + "</h1>");
            resp.getWriter().write("<a href='/java-jakarta-jee/cabeceras/inicio'>Inicio</a>");

        } else {

            // SC_UNAUTHORIZED = 401
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario o contraseña incorrectos");

        }

    }
}
