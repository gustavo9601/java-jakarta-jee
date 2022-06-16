package org.gmarquez.webapp.headers_cabeceras.services;

import org.gmarquez.webapp.headers_cabeceras.models.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImlp implements ProductoService {
    @Override
    public List<Producto> listar() {
        return Arrays.asList(
                new Producto("Producto 1", "Descripcion del producto 1", 10.0),
                new Producto("Producto 2", "Descripcion del producto 2", 20.0),
                new Producto("Producto 3", "Descripcion del producto 3", 30.0)
        );
    }

}
