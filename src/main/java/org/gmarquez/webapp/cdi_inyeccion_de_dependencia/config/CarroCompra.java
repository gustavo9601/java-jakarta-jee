package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.config;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* Un Sterotype permite crear anotaciones genericas que agrupen otras anitaciones
* de esta forma solo colocando el nombre de esta anotacion se ejecutaran todas las demas, y se centralizan en un solo lado
* */

@SessionScoped
@Named
@Stereotype
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CarroCompra {
}
