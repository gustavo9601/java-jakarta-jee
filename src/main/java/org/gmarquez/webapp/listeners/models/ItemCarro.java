package org.gmarquez.webapp.listeners.models;

import java.util.Objects;

public class ItemCarro {
    private Producto producto;
    private int cantidad;

    public ItemCarro(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporte() {
        return this.getCantidad() * this.getProducto().getPrecio();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemCarro)) return false;
        ItemCarro itemCarro = (ItemCarro) o;
        return Objects.equals(this.getProducto().getId(), itemCarro.getProducto().getId());
    }

    @Override
    public String toString() {
        return "ItemCarro{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                '}';
    }
}
