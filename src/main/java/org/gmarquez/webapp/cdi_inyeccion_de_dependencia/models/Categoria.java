package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models;

public class Categoria {

    private long id;
    private String nombre;

    public Categoria() {
    }

    public Categoria(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
