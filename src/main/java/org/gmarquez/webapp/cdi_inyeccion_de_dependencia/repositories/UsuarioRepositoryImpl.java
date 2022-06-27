package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Inject
    @Named("conexionBaseDatosBean")
    private Connection connection;



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
        try(PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM usuarios WHERE username = ?")){
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
