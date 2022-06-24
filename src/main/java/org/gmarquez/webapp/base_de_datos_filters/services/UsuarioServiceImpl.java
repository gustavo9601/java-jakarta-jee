package org.gmarquez.webapp.base_de_datos_filters.services;

import org.gmarquez.webapp.base_de_datos_filters.exepcions.ServiceJdbcException;
import org.gmarquez.webapp.base_de_datos_filters.models.Usuario;
import org.gmarquez.webapp.base_de_datos_filters.repositories.UsuarioRepository;
import org.gmarquez.webapp.base_de_datos_filters.repositories.UsuarioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService{

    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(Connection connection) {
        this.usuarioRepository = new UsuarioRepositoryImpl(connection);
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(this.usuarioRepository.porUsername(username))
                    .filter(usuario -> usuario.getPassword().equals(password)); // Comparacion del password
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
