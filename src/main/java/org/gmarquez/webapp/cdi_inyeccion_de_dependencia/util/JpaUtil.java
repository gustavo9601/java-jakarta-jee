package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    
    private static final EntityManagerFactory entityManagerFactory = JpaUtil.buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {

        // ejemplo-hibernate-jpa-cdi // nombre conexion en persistence.xml
        return Persistence.createEntityManagerFactory("ejemplo-hibernate-jpa-cdi");
    }
    // Singleton de conexion

    public static EntityManager getEntityManager() {
        return JpaUtil.entityManagerFactory.createEntityManager();
    }
}
