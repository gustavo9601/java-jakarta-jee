package org.gmarquez.webapp.base_de_datos_filters.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    public static final String URL = "jdbc:mysql://localhost:3306/java_curso_1?serverTimezone=America/Bogota";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
