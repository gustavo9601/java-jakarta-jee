package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.repositories;

import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.entities.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends Repository<Usuario> {
    Usuario porUsername(String username) throws Exception;
}
