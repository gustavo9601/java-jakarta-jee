package org.gmarquez.webapp.base_de_datos_filters.services;

import org.gmarquez.webapp.base_de_datos_filters.models.Categoria;
import org.gmarquez.webapp.base_de_datos_filters.models.Producto;

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
