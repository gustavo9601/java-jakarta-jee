package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped  // Permitimos que la clase se pueda inyectar y sera una instancia a nivel aplicacion
public class CategoriaRepositoryImpl implements Repository<Categoria> {

    private Connection connection;

    // Inyectamos la conexion como atributo del contructor
    @Inject
    public CategoriaRepositoryImpl(@Named("conexionBaseDatosBean") Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias";
        try (Statement statement = this.connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                categorias.add(this.obtenerCategoria(resultSet));
            }
            return categorias;
        }
    }

    private Categoria obtenerCategoria(ResultSet resultSet) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(resultSet.getLong("id"));
        categoria.setNombre(resultSet.getString("nombre"));
        return categoria;
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        String sql = "SELECT * FROM categorias WHERE id = ?";
        Categoria categoria = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    categoria = this.obtenerCategoria(resultSet);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }
}
