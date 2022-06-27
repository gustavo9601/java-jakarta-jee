package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    public static final String URL = "jdbc:mysql://localhost:3306/java_curso_1?serverTimezone=America/Bogota";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
