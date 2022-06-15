package org.gmarquez.webapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/crear_producto")
public class FormularioProducto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Especifica la redireccion del contexto, pasando la url del jsp y las variables req y resp
        getServletContext().getRequestDispatcher("/tarea2/formulario_productos.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String precioString = req.getParameter("precio");
        String fabricante = req.getParameter("fabricante");
        List<String> categorias = req.getParameterValues("categorias") != null ? List.of(req.getParameterValues("categorias")) : null;
        double precio = 0;

        // Validaciones
        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre es requerido");
        }

        try {
            precio = Double.parseDouble(precioString);
        } catch (NumberFormatException e) {
            errores.put("precio", "El precio debe ser un numero");
        }

        if (fabricante == null || fabricante.isBlank() || (fabricante.length() < 4 || fabricante.length() > 10)) {
            errores.put("fabricante", "El fabricante es invalido");
        }

        if (categorias == null || categorias.size() == 0) {
            errores.put("categorias", "Debe seleccionar al menos una categoria");
        }

        if (errores.isEmpty()) {
            req.setAttribute("nombre", nombre);
            req.setAttribute("precio", precio);
            req.setAttribute("fabricante", fabricante);
            req.setAttribute("categorias", categorias);
            getServletContext().getRequestDispatcher("/tarea2/vista_productos.jsp").forward(req, resp);
        } else {
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/tarea2/formulario_productos.jsp").forward(req, resp);
        }


    }
}
