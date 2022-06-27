package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.filters;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.Name;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.exepcions.ServiceJdbcException;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.util.ConexionBaseDatosPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

// /* indica que se ejecutara en todas las rutas que empiecen por
@WebFilter("/cdi_inyeccion_de_dependencia/*")
public class ConexionFilter implements Filter {

    @Inject
    @Named("conexionBaseDatosBean") // usamos el nombre del producer de la conexion
    private Connection connection;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try (Connection connection = this.connection) {

            if (connection.getAutoCommit()) {
                // Desactivamos el autocommit para poder hacer un rolback dado el casofalloe
                connection.setAutoCommit(false);
            }


            try {
                // Ya no lo guardamos en la sesion ya que se usa inyeccion de dependencia
                // servletRequest.setAttribute("connection", connection); // Creamos el atributo para que sea accesible desde cualquier request
                System.out.println("connection in conexionFilter=\t" + connection);
                filterChain.doFilter(servletRequest, servletResponse);
                connection.commit();
                // Escucha la exepcion padre y la personalizada
            } catch (SQLException | ServiceJdbcException e) {
                connection.rollback();
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la base de datos, error: " + e.getMessage());
                e.printStackTrace();
            }


        } catch (SQLException e) {
            System.out.println("Error en la conexion a la base de datos");
            e.printStackTrace();
        }

    }
}
