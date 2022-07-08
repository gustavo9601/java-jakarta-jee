package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.repositories;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.config.Repositorio;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.entities.Categoria;

import java.util.List;
import java.util.logging.Logger;

@RepositoryJPA
@Repositorio
public class CategoryRepositoryJPAImpl implements Repository<Categoria> {

    @Inject
    EntityManager entityManager;

    @Inject
    @Named("loggerBean")
    private transient Logger loggerBean;

    @Override
    public List<Categoria> listar() throws Exception {
        return this.entityManager
                .createQuery("SELECT c FROM Categoria c", Categoria.class)
                .getResultList();
    }

    @Override
    public Categoria porId(Long id) throws Exception {
        return this.entityManager
                .createQuery("SELECT c FROM Categoria c WHERE c.id = :id", Categoria.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void guardar(Categoria categoria) throws Exception {
        if(categoria.getId() == null || categoria.getId() == 0){
            this.entityManager.persist(categoria);
            this.loggerBean.info("Se creo la categoria: " + categoria.toString());
        } else {
            this.entityManager.merge(categoria);
            this.loggerBean.info("Se actualizo la categoria: " + categoria.toString());
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.entityManager.remove(this.porId(id));
        this.loggerBean.info("Se elimino la categoria con id: " + id);
    }
}
