package org.gmarquez.webapp.forms;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/formulario")
public class FormularioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Especifica la redireccion del contexto, pasando la url del jsp y las variables req y resp
        getServletContext().getRequestDispatcher("/formulario.jsp").forward(req, resp);
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

        int edad = !req.getParameter("edad").isBlank() ? Integer.parseInt(req.getParameter("edad")) : 0;

        // Validaciones de errores
        Map<String, String> errores = new HashMap<>();
        // .isBlank() => .equals("") ||  .equals(" ")

        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre es requerido");
        }
        if (apellido == null || apellido.isBlank()) {
            errores.put("apellido", "El apellido es requerido");
        }
        if (correo == null || correo.isBlank() || !correo.contains("@")) {
            errores.put("correo", "El correo es requerido y debe tener un @");
        }
        if (contrasena == null || contrasena.isBlank()) {
            errores.put("contrasena", "La contraseña es requerida");
        }
        if (edad < 18) {
            errores.put("edad", "La edad debe ser mayor a 18");
        }
        if (pais == null || pais.isBlank()) {
            errores.put("pais", "El pais es requerido");
        }
        if (lenguajes == null || lenguajes.length == 0) {
            errores.put("lenguajes", "Debe seleccionar al menos un lenguaje");
        }
        if (roles == null || roles.length == 0) {
            errores.put("roles", "Debe seleccionar al menos un rol");
        }


        try (PrintWriter writer = resp.getWriter()) {

            if (errores.isEmpty()) {


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
            }else{
                /*writer.println("<h1>Errores</h1>");
                for (String error : errores) {
                    writer.println("<h2>" + error + "</h2>");
                }
                writer.println("<a href='/java-jakarta-jee'>Volver</a>");
                */
                // Pasando a la vista la variable errores
                req.setAttribute("errores", errores);

                // Especifica la redireccion del contexto, pasando la url del jsp y las variables req y resp
                getServletContext().getRequestDispatcher("/formulario.jsp").forward(req, resp);
            }
        }

    }
}
