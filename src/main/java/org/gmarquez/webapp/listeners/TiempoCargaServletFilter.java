package org.gmarquez.webapp.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "TiempoCargaServletFilter", urlPatterns = {"/*"})
public class TiempoCargaServletFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();

        String servletPath = ((HttpServletRequest) servletRequest).getServletPath();

        servletRequest.getServletContext().log("Tiempo de carga: " + (end - start) + "ms, en el path: " + servletPath);
    }
}
