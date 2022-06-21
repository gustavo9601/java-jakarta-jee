package org.gmarquez.webapp.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.gmarquez.webapp.listeners.models.Carro;


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
        sce.getServletContext().log("contextInitialized");
        sce.getServletContext().log("Inicializando la aplicacion");

        this.servletContext = sce.getServletContext(); // Inicializamos el contexto para todos los demas metodos

        // Se crea el atributo de contexto global
        this.servletContext.setAttribute("mensajeGlobal", "Hola Mundo Global");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        this.servletContext.log("contextDestroyed");
        this.servletContext.log("Destruyendo la aplicacion");
    }

    /*
     * Servicios de contexto por request
     * */

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        this.servletContext.log("requestInitialized");
        this.servletContext.log("Inicializando el request");

        // Se crea el atributo de request, que se creara por cada request
        sre.getServletRequest().setAttribute("mensajeRequest", "Hola Mundo Request");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        this.servletContext.log("requestDestroyed");
        this.servletContext.log("Destruyendo el request");
    }


    /*
     * Servicios de contexto por sesion
     * */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        this.servletContext.log("sessionCreated");
        this.servletContext.log("Creando la sesion");

        // Se crea el atributo de sesion, que se creara por cada sesion
        // Carro carro = new Carro();
        //  se.getSession().setAttribute("carro", carro);
        // this.servletContext.log("Sesion carro = " + carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        this.servletContext.log("sessionDestroyed");
        this.servletContext.log("Destruyendo la sesion");
    }


}
