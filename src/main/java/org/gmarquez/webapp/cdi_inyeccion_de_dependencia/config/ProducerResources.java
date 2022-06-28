package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.config;


import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Named;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ProducerResources {


    /*
    * Es necesario realizarlo de esta forma ya que la clae connection no es nuestra sino de java
    * por lo tanto debemos especificar que producira
    * */

    // Se inyecta como resource ya que es propio de Java, pero le especificamos el name para que identifique cual recurso debe cargar
    @Resource(name = "jdbc/mysqlDB")
    private DataSource ds;

    @Produces
    @RequestScoped
    @Named("conexionBaseDatosBean")

    private Connection beanConnection() throws SQLException {
        // Context initContext = null;
        // initContext = new InitialContext();
        // Context envContext = (Context) initContext.lookup("java:/comp/env");
        // jdbc/mysqlDB => debe ser el mismo del archivo webapp/WEB-INF/web.xml  <res-ref-name></res-ref-name>
        // DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");
        return this.ds.getConnection();
    };


    // InjectionPoint // proporciona la metadata de las clases que realicen la inyeccion de este metodo
    @Produces
    @Named("loggerBean")
    private Logger beanLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}
