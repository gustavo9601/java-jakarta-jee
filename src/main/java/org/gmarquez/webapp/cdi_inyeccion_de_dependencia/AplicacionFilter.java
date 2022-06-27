package org.gmarquez.webapp.cdi_inyeccion_de_dependencia;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;


/*
*
* Filtros => Son una forma de filtrar las peticiones que se envian a un servlet.
* - Mucho mas similar a un Middleware a nivel de request
* */

// Se especifica sobre que rutas o servlets va a aplicar
@WebFilter({"/cdi_inyeccion_de_dependencia/tabla_productos", "/cdi_inyeccion_de_dependencia/ver-carro"})
public class AplicacionFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        this.filterConfig.getServletContext().log("doFilter");
        this.filterConfig.getServletContext().log("Filtrando");

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        Optional<String> sessionOptional = session.getAttribute("nombreUsuario") != null ? Optional.of((String) session.getAttribute("nombreUsuario")) : Optional.empty();

        if (sessionOptional.isPresent()) {
            // Indica que puede continuar el request
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // como no hay session retornaremos un error
            // Casteamos el request ya que es muy generico en el doFilter
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Por favor inicio sesion para continuar");
        }
    }
}
