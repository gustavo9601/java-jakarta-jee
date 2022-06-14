package org.gmarquez.webapp.forms;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/formulario")
public class FormularioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter writer = resp.getWriter()) {
            writer.println("<h1>Solo sirve por POST</h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        // Campturando las variables, de la misma forma que se captura por get
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String correo = req.getParameter("correo");
        String contrasena = req.getParameter("contrasena");

        String pais = req.getParameter("pais");
        String[] lenguajes = req.getParameterValues("lenguajes"); // optiene los values
        String[] roles = req.getParameterValues("roles");

        int edad = Integer.parseInt(req.getParameter("edad"));

        try (PrintWriter writer = resp.getWriter()) {

            writer.println("<h1>Resultado formulario</h1>");
            writer.println("<h2>Nombre: " + nombre + "</h2>");
            writer.println("<h2>Apellido: " + apellido + "</h2>");
            writer.println("<h2>Correo: " + correo + "</h2>");
            writer.println("<h2>Contraseña: " + contrasena + "</h2>");
            writer.println("<h2>Edad: " + edad + "</h2>");
            writer.println("<h2>Pais: " + pais + "</h2>");
            writer.println("<h2>Lenguajes: ");
            for (String lenguaje : lenguajes) {
                writer.println("<h2>" + lenguaje + "</h2>");
            }
            writer.println("<h2>Roles: </h2>");
            for (String rol : roles) {
                writer.println("<h2>" + rol + "</h2>");
            }

        }

    }
}
