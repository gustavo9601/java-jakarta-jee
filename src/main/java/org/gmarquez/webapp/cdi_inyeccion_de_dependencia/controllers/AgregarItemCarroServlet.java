package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.Carro;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.ItemCarro;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.entities.Producto;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.services.ProductoService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/cdi_inyeccion_de_dependencia/agregar-item-carro-servlet")
public class AgregarItemCarroServlet extends HttpServlet {

    // Inyectamos la dependencia del carro
    @Inject
    private Carro carro;
    @Inject
    @Named("productoServiceJdcImpl")
    ProductoService productoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        System.out.println("id = " + id);
        // Connection connection = (Connection) req.getAttribute("connection");
        // ProductoService productoService = new ProductoServiceJdbcImpl(connection);

        Optional<Producto> productoEncontrado = this.productoService.porId(id);
        System.out.println("productoEncontrado = " + productoEncontrado);

        if (productoEncontrado.isPresent()) {
            ItemCarro itemCarro = new ItemCarro(productoEncontrado.get(), 1);
            // Carro carro;

            HttpSession session = req.getSession();
            System.out.println("*** carro antes ***");
            System.out.println(session.getAttribute("carro"));

            // La session siempre va a exisitir porque se crea en el listener de inicio de sesion
            // carro = (Carro) session.getAttribute("carro");

            this.carro.addItemCarro(itemCarro);
            session.setAttribute("carro", this.carro);

            System.out.println("*** carro despues ***");
            System.out.println(session.getAttribute("carro"));
            System.out.println("cantidad productos =\t" + ((Carro) session.getAttribute("carro")).cantidadProductos());
        }

        // Redireccionadno para ver el carro
        resp.sendRedirect(req.getContextPath() + "/cdi_inyeccion_de_dependencia/ver-carro");

    }
}
