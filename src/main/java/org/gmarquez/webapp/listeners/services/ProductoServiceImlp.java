package org.gmarquez.webapp.listeners.services;

import org.gmarquez.webapp.listeners.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImlp implements ProductoService {
    @Override
    public List<Producto> listar() {
        return Arrays.asList(
                new Producto(1L,"Producto 1", "Descripcion del producto 1", 10.0),
                new Producto(2L,"Producto 2", "Descripcion del producto 2", 20.0),
                new Producto(3L,"Producto 3", "Descripcion del producto 3", 30.0)
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

}
