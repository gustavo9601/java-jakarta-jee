package org.gmarquez.webapp.base_de_datos_filters.services;

import org.gmarquez.webapp.base_de_datos_filters.exepcions.ServiceJdbcException;
import org.gmarquez.webapp.base_de_datos_filters.models.Producto;
import org.gmarquez.webapp.base_de_datos_filters.repositories.ProductoRepositoryJdbcImpl;
import org.gmarquez.webapp.base_de_datos_filters.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImpl implements ProductoService {

    private Repository<Producto> repository;

    // Recibe la conexion
    public ProductoServiceJdbcImpl(Connection connection) {
        this.repository = new ProductoRepositoryJdbcImpl(connection);
    }

    @Override
    public List<Producto> listar() {
        try {
            return this.repository.listar();
        } catch (SQLException e) {
            e.printStackTrace();
            // Usamos la exepcion personalizada para que la unica instancia que realice el rollback sea la del filtro
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(this.repository.porId(id));
        } catch (SQLException e) {
            e.printStackTrace();
            // Usamos la exception personalizada para que la unica instancia que realice el rollback sea la del filtro
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }
}
