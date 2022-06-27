package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.config;


import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ProducerResourses {


    /*
    * Es necesario realizarlo de esta forma ya que la clae connection no es nuestra sino de java
    * por lo tanto debemos especificar que producira
    * */

    @Produces
    @RequestScoped
    @Named("conexionBaseDatosBean")

    private Connection beanConnection() throws NamingException, SQLException {
        Context initContext = null;
        initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        // jdbc/mysqlDB => debe ser el mismo del archivo webapp/WEB-INF/web.xml  <res-ref-name></res-ref-name>
        DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");
        return ds.getConnection();
    };

}
