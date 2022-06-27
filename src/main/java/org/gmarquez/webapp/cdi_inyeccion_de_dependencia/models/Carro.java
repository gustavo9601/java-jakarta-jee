package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models;


import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


// Requeriere que la clase tenga un constructor sin recibir parametros
@SessionScoped // Por cada sesion tendra una nueva inyeccion
@Named("carro")  // por defecto sera el nombre de la clase, si le pasamos parametro tomara ese nombre
public class Carro implements Serializable { // Para poder injectarse se debe impelemtar la itnerazcion Serializable
    private List<ItemCarro> itemCarros;

    public Carro() {
        this.itemCarros = new ArrayList<>();
    }

    public List<ItemCarro> getItemCarros() {
        return itemCarros;
    }

    public Carro addItemCarro(ItemCarro itemCarro) {
        if (this.getItemCarros().contains(itemCarro)) {
            // Obtenemos el itemCarro que ya existe en el carro
            System.out.println("this.getItemCarros().indexOf(itemCarro)=\t" + this.getItemCarros().indexOf(itemCarro));
            ItemCarro itemCarroActual = this.getItemCarros().get(this.getItemCarros().indexOf(itemCarro));
            // Le modificamos la cantidad con lo que llegue del nuevo item
            itemCarroActual.setCantidad(itemCarroActual.getCantidad() + itemCarro.getCantidad());
        } else {
            // Lo añadimos al carro
            this.itemCarros.add(itemCarro);
        }
        return this;
    }

    public void eliminarItemCarro(ItemCarro itemCarro) {
        this.itemCarros.remove(itemCarro);
    }

    public double getTotal() {
        return this.getItemCarros()
                .stream()
                .mapToDouble(itemCarro -> itemCarro.getImporte())// Pasamos de un Strem de Item Carro a un Strm de Doubles
                .sum(); // Suma todo el stream de doubles
    }

    public boolean estaVacio() {
        return this.getItemCarros().isEmpty();
    }

    public int cantidadProductos() {
        return this.getItemCarros().size();
    }

    @Override
    public String toString() {
        return "Carro{" +
                "itemCarros=" + itemCarros +
                '}';
    }
}
