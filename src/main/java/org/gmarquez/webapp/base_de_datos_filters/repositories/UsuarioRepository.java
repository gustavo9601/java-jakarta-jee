package org.gmarquez.webapp.base_de_datos_filters.repositories;

import org.gmarquez.webapp.base_de_datos_filters.models.Usuario;

import java.sql.SQLException;
import java.util.Optional;

public interface UsuarioRepository extends Repository<Usuario> {
    Usuario porUsername(String username) throws SQLException;
}
