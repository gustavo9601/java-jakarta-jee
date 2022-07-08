package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.exepcions.ServiceJdbcException;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.entities.Categoria;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.entities.Producto;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.repositories.Repository;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.repositories.RepositoryJPA;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named("productoServiceJdcImpl") // Le Agregamos un nombre para que se diferencia de la otra clase que implementa la interfaz ProductoService
public class ProductoServiceJdbcImpl implements ProductoService {

    @Inject
    @RepositoryJPA // Le agregamos el calificador para que sepa que es una clase que implementa la interfaz RepositoryJPA
    private Repository<Producto> productoRepository;
    @Inject
    @RepositoryJPA
    private Repository<Categoria> categoriaRepository;


    @Override
    public List<Producto> listar() {
        try {
            return this.productoRepository.listar();
        } catch (Exception e) {
            e.printStackTrace();
            // Usamos la exepcion personalizada para que la unica instancia que realice el rollback sea la del filtro
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(this.productoRepository.porId(id));
        } catch (Exception e) {
            e.printStackTrace();
            // Usamos la exception personalizada para que la unica instancia que realice el rollback sea la del filtro
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            this.productoRepository.guardar(producto);
        } catch (Exception e) {
            e.printStackTrace();
            // Usamos la exception personalizada para que la unica instancia que realice el rollback sea la del filtro
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            this.productoRepository.eliminar(id);
        } catch (Exception e) {
            e.printStackTrace();
            // Usamos la exception personalizada para que la unica instancia que realice el rollback sea la del filtro
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }

    @Override
    public List<Categoria> listarCategorias() {
        try {
            return this.categoriaRepository.listar();
        } catch (Exception e) {
            e.printStackTrace();
            // Usamos la exception personalizada para que la unica instancia que realice el rollback sea la del filtro
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(this.categoriaRepository.porId(id));
        } catch (Exception e) {
            e.printStackTrace();
            // Usamos la exception personalizada para que la unica instancia que realice el rollback sea la del filtro
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }
}
