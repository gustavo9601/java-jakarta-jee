package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionBaseDatosPool {
    public static final String URL = "jdbc:mysql://localhost:3306/java_curso_1?serverTimezone=America/Bogota";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Context initContext = null;
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            // jdbc/mysqlDB => debe ser el mismo del archivo webapp/WEB-INF/web.xml  <res-ref-name></res-ref-name>
            DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");
            return ds.getConnection();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }
}
