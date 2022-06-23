package org.gmarquez.webapp.base_de_datos_filters.services;

import org.gmarquez.webapp.base_de_datos_filters.models.Categoria;
import org.gmarquez.webapp.base_de_datos_filters.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImlp implements ProductoService {
    @Override
    public List<Producto> listar() {
        return Arrays.asList(
                new Producto(1L, "123", "Producto 1", "Descripcion 1", 9000L),
                new Producto(2L, "456", "Producto 2", "Descripcion 2", 10000L),
                new Producto(3L, "789", "Producto 3", "Descripcion 3", 11000L)
        );
    }

    @Override
    public Optional<Producto> porId(Long id) {
        return this.listar()
                .stream()
                .peek(
                        producto -> {
                            System.out.println("producto.getId()=\t" + producto.getId());
                        }
                )
                .filter(producto -> producto.getId() == id)
                .findAny();
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public List<Categoria> listarCategorias() {
        return null;
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        return Optional.empty();
    }

}
