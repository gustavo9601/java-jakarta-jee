package org.gmarquez.webapp.base_de_datos_filters.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.gmarquez.webapp.base_de_datos_filters.exepcions.ServiceJdbcException;
import org.gmarquez.webapp.base_de_datos_filters.util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

// /* indica que se ejecutara en todas las rutas que empiecen por
@WebFilter("/base_de_datos_filter/*")
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try (Connection connection = ConexionBaseDatos.getConnection()) {

            if (connection.getAutoCommit()) {
                // Desactivamos el autocommit para poder hacer un rolback dado el casofalloe
                connection.setAutoCommit(false);
            }

            try {
                servletRequest.setAttribute("connection", connection); // Creamos el atributo para que sea accesible desde cualquier request
                filterChain.doFilter(servletRequest, servletResponse);
                connection.commit();
                // Esucaha la exepcion padre y la personalizada
            } catch (SQLException | ServiceJdbcException e) {
                connection.rollback();
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la base de datos, error: " + e.getMessage());
                e.printStackTrace();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
