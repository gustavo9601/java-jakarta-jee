package org.gmarquez.webapp.base_de_datos_filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.gmarquez.webapp.base_de_datos_filters.models.Carro;


/*
 *
 * Los Listeners cumplen la funcion similar a un Middleware, escuchando eventos
 * que ocurren a lo largo del contexto de ejecucion de la aplicacion (ciclo de vida).
 *
 * - Los listeners se aplicaran a cualquier servlet
 * - Los filtros aplicaran sobre el ciclo de vida de los Request, y permite especificar a Servlet afectar
 * */

// @WebListener(nombre del listener)
@WebListener
public class AplicacionListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    /*
     * Servicios de contexto general
     * */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Inicializando la aplicacion Listener Base de datos Filter");

        this.servletContext = sce.getServletContext(); // Inicializamos el contexto para todos los demas metodos
    }


    /*
     * Servicios de contexto por sesion
     * */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        this.servletContext.log("Creando la sesion en Listener Base de datos Filter");

        // Se crea el atributo de sesion, que se creara por cada sesion
        Carro carro = new Carro();
        se.getSession().setAttribute("carro", carro);
        this.servletContext.log("Sesion carro = " + carro);
    }
}
