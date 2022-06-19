package org.gmarquez.webapp.listeners.services;

import org.gmarquez.webapp.listeners.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();

    Optional<Producto> porId(Long id);

}
