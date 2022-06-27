package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.exepcions.ServiceJdbcException;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.Usuario;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.repositories.UsuarioRepository;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.repositories.UsuarioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    // Realizamos la inyeccion del usuarioRepositoryImpl, como solo existe esta clase que implementa esta interfaz
    // El CDI identificara que es esa clase la que debe devolver, si hubiesen mas clases que implementen esta interfaz,
    // Se deberia usar un calificador con Named para indicar cual es la clase que debe devolver

    @Inject
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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
