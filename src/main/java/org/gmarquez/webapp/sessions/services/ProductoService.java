package org.gmarquez.webapp.sessions.services;

import org.gmarquez.webapp.sessions.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();

    Optional<Producto> porId(Long id);

}
