package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.repositories;

import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends Repository<Usuario> {
    Usuario porUsername(String username) throws SQLException;
}
