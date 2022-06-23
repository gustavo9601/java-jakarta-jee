package org.gmarquez.webapp.base_de_datos_filters.services;

import org.gmarquez.webapp.base_de_datos_filters.exepcions.ServiceJdbcException;
import org.gmarquez.webapp.base_de_datos_filters.models.Categoria;
import org.gmarquez.webapp.base_de_datos_filters.models.Producto;
import org.gmarquez.webapp.base_de_datos_filters.repositories.CategoriaRepositoryImpl;
import org.gmarquez.webapp.base_de_datos_filters.repositories.ProductoRepositoryJdbcImpl;
import org.gmarquez.webapp.base_de_datos_filters.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImpl implements ProductoService {

    private Repository<Producto> productoRepository;
    private Repository<Categoria> categoriaRepository;

    // Recibe la conexion
    public ProductoServiceJdbcImpl(Connection connection) {
        this.productoRepository = new ProductoRepositoryJdbcImpl(connection);
        this.categoriaRepository = new CategoriaRepositoryImpl(connection);
    }

    @Override
    public List<Producto> listar() {
        try {
            return this.productoRepository.listar();
        } catch (SQLException e) {
            e.printStackTrace();
            // Usamos la exepcion personalizada para que la unica instancia que realice el rollback sea la del filtro
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(this.productoRepository.porId(id));
        } catch (SQLException e) {
            e.printStackTrace();
            // Usamos la exception personalizada para que la unica instancia que realice el rollback sea la del filtro
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            this.productoRepository.guardar(producto);
        } catch (SQLException e) {
            e.printStackTrace();
            // Usamos la exception personalizada para que la unica instancia que realice el rollback sea la del filtro
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            this.productoRepository.eliminar(id);
        } catch (SQLException e) {
            e.printStackTrace();
            // Usamos la exception personalizada para que la unica instancia que realice el rollback sea la del filtro
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }

    @Override
    public List<Categoria> listarCategorias() {
        try {
            return this.categoriaRepository.listar();
        } catch (SQLException e) {
            e.printStackTrace();
            // Usamos la exception personalizada para que la unica instancia que realice el rollback sea la del filtro
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(this.categoriaRepository.porId(id));
        } catch (SQLException e) {
            e.printStackTrace();
            // Usamos la exception personalizada para que la unica instancia que realice el rollback sea la del filtro
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }
}
