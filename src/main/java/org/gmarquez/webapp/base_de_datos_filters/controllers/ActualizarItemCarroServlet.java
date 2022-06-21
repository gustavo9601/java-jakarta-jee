package org.gmarquez.webapp.base_de_datos_filters.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.gmarquez.webapp.base_de_datos_filters.models.Carro;
import org.gmarquez.webapp.base_de_datos_filters.services.ProductoService;
import org.gmarquez.webapp.base_de_datos_filters.services.ProductoServiceImlp;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/base_de_datos_filter/actualizar-item-carro-servlet")
public class ActualizarItemCarroServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long[] indicesEliminar = this.parseStringArrayToLongArray(req.getParameterValues("eliminar"));

        this.eliminarItemsCarro(req, indicesEliminar);

        this.actualizarItemsCarro(req);

        resp.sendRedirect(req.getContextPath() + "/base_de_datos_filter/ver-carro");
    }


    private Long[] parseStringArrayToLongArray(String[] array) {
        if (array == null) {
            return null;
        }
        return Arrays.stream(array)
                .map(Long::parseLong)
                .toArray(Long[]::new);
    }

    private void actualizarItemsCarro(HttpServletRequest req) {
        ProductoService productoService = new ProductoServiceImlp();
        productoService.listar()
                .forEach(producto -> {
                    // Verificamos si llega la cantidad concatenada con el id del producto
                    String cantidad = req.getParameter("cantidad-" + producto.getId());
                    // Si existe la cantidad la parseamos a int y verificamos si es mayor a 0
                    if (cantidad != null) {
                        int cantidadInt = Integer.parseInt(cantidad);
                        // Si la cantidad es mayor a 0 iteramos sobre la sesion actual del carro, y actualizamos
                        // el item del carro si el producto es el mismo que el que estamos iterando
                        if (cantidadInt > 0) {

                            Carro sessionCarro = this.obtenerSesionCarro(req);
                            sessionCarro.getItemCarros()
                                    .stream()
                                    .filter(itemCarro -> itemCarro.getProducto().getId().equals(producto.getId()))
                                    .forEach(itemCarro -> {
                                        itemCarro.setCantidad(cantidadInt);
                                    });
                        }
                    }
                });
    }

    private void eliminarItemsCarro(HttpServletRequest req, Long[] indices) {
        if (indices != null) {
            Carro sessionCarro = this.obtenerSesionCarro(req);
            List<Long> indicesList = Arrays.asList(indices);

            sessionCarro
                    .getItemCarros()
                    // si el item coincide con alguno de los indices, con removeIf se elimina de la lista de items
                    // previene el error java.util.ConcurrentModificationException
                    .removeIf(itemCarro -> indicesList.contains(itemCarro.getProducto().getId()));

            this.actualizarSesionCarro(req, sessionCarro);
        }
    }

    private Carro obtenerSesionCarro(HttpServletRequest req) {
        HttpSession session = req.getSession();
        // return session.getAttribute("carro") != null ? (Carro) session.getAttribute("carro") : new Carro();
        return (Carro) session.getAttribute("carro"); // La sesion siempre va a existir ya que se inicializa en el listener
    }

    private void actualizarSesionCarro(HttpServletRequest req, Carro carro) {
        HttpSession session = req.getSession();
        session.setAttribute("carro", carro);
    }

}
