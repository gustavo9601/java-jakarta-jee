package org.gmarquez.webapp.base_de_datos_filters.repositories;

import org.gmarquez.webapp.base_de_datos_filters.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositoryImpl implements UsuarioRepository{

    private Connection connection;

    public UsuarioRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        return null;
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Usuario usuario) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    @Override
    public Usuario porUsername(String username) throws SQLException {
        Usuario usuario = null;
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuarios WHERE username = ?")){
            statement.setString(1, username);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    usuario = new Usuario();
                    usuario.setId(resultSet.getLong("id"));
                    usuario.setUsername(resultSet.getString("username"));
                    usuario.setPassword(resultSet.getString("password"));
                    usuario.setEmail(resultSet.getString("email"));
                }
            }
        }
        return usuario;
    }
}
