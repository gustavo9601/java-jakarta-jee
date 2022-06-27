package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.services;

import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.Categoria;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();

    Optional<Producto> porId(Long id);

    void guardar(Producto producto);

    void eliminar(Long id);

    List<Categoria> listarCategorias();

    Optional<Categoria> porIdCategoria(Long id);

}
