package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.config;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@RequestScoped
@Named
@Stereotype
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Repositorio {
}
