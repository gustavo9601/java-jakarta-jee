package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.repositories;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.entities.Producto;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.config.Repositorio;

import java.util.List;
import java.util.logging.Logger;

@RepositoryJPA
// Anotacion personalizada, que tiene un Sterotype agrupando varias anotaciones
@Repositorio
public class ProductoRepositoryJPAImpl implements Repository<Producto> {

    @Inject
    // @Named("beanEntityManager") // Si existiera otra instancia de EntityManager, podria usar este nombre
    private EntityManager entityManager;


    @Inject
    @Named("loggerBean")
    private transient Logger loggerBean;


    @Override
    public List<Producto> listar() throws Exception {
        return this.entityManager
                .createQuery("SELECT p FROM Producto p", Producto.class)
                .getResultList();
    }

    @Override
    public Producto porId(Long id) throws Exception {
        return this.entityManager
                .createQuery("SELECT p FROM Producto p WHERE p.id = :id", Producto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void guardar(Producto producto) throws Exception {
        if(producto.getId() == null || producto.getId() == 0) {
            this.entityManager.persist(producto);
            this.loggerBean.info("Se creo el producto: " + producto.toString());
        } else {
            this.entityManager.merge(producto);
            this.loggerBean.info("Se actualizo el producto: " + producto.toString());
        }

    }

    @Override
    public void eliminar(Long id) throws Exception {
        Producto producto = this.porId(id);
        this.entityManager.remove(producto);
        this.loggerBean.info("Se elimino el producto con id: " + id);
    }
}
