package org.gmarquez.webapp.base_de_datos_filters.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gmarquez.webapp.base_de_datos_filters.models.Categoria;
import org.gmarquez.webapp.base_de_datos_filters.models.Producto;
import org.gmarquez.webapp.base_de_datos_filters.services.ProductoService;
import org.gmarquez.webapp.base_de_datos_filters.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/base_de_datos_filter/producto_form")
public class ProductoFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ProductoService productoService = new ProductoServiceJdbcImpl(connection);
        List<Categoria> categorias = productoService.listarCategorias();

        req.setAttribute("categorias", categorias);

        String idProducto = req.getParameter("id");
        if (idProducto != null && !idProducto.isBlank()) {
            Long id;
            try {
                id = Long.parseLong(idProducto);
            } catch (NumberFormatException e) {
                id = 0L;
            }
            Optional<Producto> producto = productoService.porId(id);
            producto.ifPresent(p -> req.setAttribute("producto", p));
        }

        getServletContext().getRequestDispatcher("/base_de_datos_filter/producto_form.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Producto producto = new Producto();

        String idProducto = req.getParameter("id");

        String nombre = req.getParameter("nombre");
        String precio = req.getParameter("precio");
        String categoriaStr = req.getParameter("categoria_id");
        String fechaRegistroStr = req.getParameter("fecha_registro");
        String sku = req.getParameter("sku");

        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre es obligatorio");
        }
        if (sku == null || sku.isBlank()) {
            errores.put("sku", "El sku es obligatorio");
        }
        if (precio == null || precio.isBlank()) {
            errores.put("precio", "El precio es obligatorio");
        }
        if (categoriaStr == null || categoriaStr.isBlank()) {
            errores.put("categoria_id", "La categoria es obligatoria");
        }
        if (fechaRegistroStr == null || fechaRegistroStr.isBlank()) {
            errores.put("fecha_registro", "La fecha es obligatoria");
        }

        if (errores.isEmpty()) {
            producto.setNombre(nombre);
            producto.setPrecio(Double.parseDouble(precio));
            producto.setCategoria(new Categoria(Long.parseLong(categoriaStr), null));
            producto.setFechaRegistro(LocalDate.parse(fechaRegistroStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            producto.setSku(sku);

            Connection connection = (Connection) req.getAttribute("connection");
            ProductoService productoService = new ProductoServiceJdbcImpl(connection);

            if(idProducto != null && !idProducto.isBlank()) {
                producto.setId(Long.parseLong(idProducto));
            }
            productoService.guardar(producto);

            // Se redirije para evitar al recargar el servicio se vuelva a crear
            resp.sendRedirect(getServletContext().getContextPath() + "/base_de_datos_filter/tabla_productos");
        } else {
            req.setAttribute("errores", errores);
            // llamamos el doGet para que se vuelva a cargar el formulario
            this.doGet(req, resp);
        }
    }
}
