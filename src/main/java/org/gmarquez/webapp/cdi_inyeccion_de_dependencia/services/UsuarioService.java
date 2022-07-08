package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.services;

import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.entities.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> login(String username, String password);
}
