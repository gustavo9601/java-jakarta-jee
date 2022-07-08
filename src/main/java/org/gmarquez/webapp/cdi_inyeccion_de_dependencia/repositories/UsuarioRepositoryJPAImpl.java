package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.repositories;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.config.Repositorio;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.entities.Usuario;

import java.util.List;
import java.util.logging.Logger;

@RepositoryJPA
@Repositorio
public class UsuarioRepositoryJPAImpl implements UsuarioRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    @Named("loggerBean")
    private transient Logger loggerBean;


    @Override
    public List<Usuario> listar() throws Exception {
        return this.entityManager
                .createQuery("SELECT u FROM Usuario u", Usuario.class)
                .getResultList();
    }

    @Override
    public Usuario porId(Long id) throws Exception {
        return this.entityManager
                .createQuery("SELECT u FROM Usuario u WHERE u.id = :id", Usuario.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void guardar(Usuario usuario) throws Exception {
        if (usuario.getId() == null || usuario.getId() == 0) {
            this.entityManager.persist(usuario);
            this.loggerBean.info("Se creo el usuario: " + usuario.toString());
        } else {
            this.entityManager.merge(usuario);
            this.loggerBean.info("Se actualizo el usuario: " + usuario.toString());
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.entityManager.remove(this.porId(id));
        this.loggerBean.info("Se elimino el usuario con id: " + id);
    }

    @Override
    public Usuario porUsername(String username) throws Exception {
        return this.entityManager
                .createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class)
                .setParameter("username", username)
                .setMaxResults(1)
                .getSingleResult();
    }
}
