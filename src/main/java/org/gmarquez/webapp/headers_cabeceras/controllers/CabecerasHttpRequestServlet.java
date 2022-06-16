package org.gmarquez.webapp.headers_cabeceras.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cabeceras/cabeceras_http_request")
public class CabecerasHttpRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String metodoHttp = req.getMethod(); // POST | GET | PUT | DELETE
        String requestURI = req.getRequestURI(); // nombre proyecto + el mapping
        String requestUrl = req.getRequestURL().toString(); // http://localhost:8080/cabeceras
        String contextPath = req.getContextPath(); // /java-jakarta-jee
        String servletPath = req.getServletPath(); // /cabeceras
        String ipServidor = req.getLocalAddr(); // ip del servidor
        int puertoServidor = req.getLocalPort(); // puerto del servidor
        String schemaHttp = req.getScheme(); // http | https
        String hostYPuerto = req.getHeader("host"); // host:puerto
        String ipClienteRemoto = req.getRemoteAddr(); // ip del cliente


        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cabeceras HTTP</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Cabeceras HTTP</h1>");

            out.println("<p>Metodo HTTP: " + metodoHttp + "</p>");
            out.println("<p>Request URI: " + requestURI + "</p>");
            out.println("<p>Request URL: " + requestUrl + "</p>");
            out.println("<p>Context Path: " + contextPath + "</p>");
            out.println("<p>Servlet Path: " + servletPath + "</p>");
            out.println("<p>IP Servidor: " + ipServidor + "</p>");
            out.println("<p>Puerto Servidor: " + puertoServidor + "</p>");
            out.println("<p>Schema HTTP: " + schemaHttp + "</p>");
            out.println("<p>Host y Puerto: " + hostYPuerto + "</p>");
            out.println("<p>IP Cliente Remoto: " + ipClienteRemoto + "</p>");

            out.println("<a href='/java-jakarta-jee/cabeceras/inicio'>Inicio</a>");

            out.println("</body>");


        }
    }
}
