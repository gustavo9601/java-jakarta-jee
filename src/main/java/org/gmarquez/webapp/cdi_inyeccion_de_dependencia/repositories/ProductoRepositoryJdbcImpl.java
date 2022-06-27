package org.gmarquez.webapp.cdi_inyeccion_de_dependencia.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.Categoria;
import org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.Producto;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped  // Permitimos que la clase se pueda inyectar y sera una instancia a nivel aplicacion
public class ProductoRepositoryJdbcImpl implements Repository<Producto> {

    @Inject
    @Named("conexionBaseDatosBean")
    private Connection connection;  // inyectamos la conexion


    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT p.*, c.nombre as categoria FROM productos p INNER JOIN categorias c ON p.categoria_id = c.id"
            );

            while (resultSet.next()) {
                productos.add(this.getProducto(resultSet));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    private Producto getProducto(ResultSet resultSet) throws SQLException {
        Producto producto = new Producto();
        producto.setId(resultSet.getLong("id"));
        producto.setSku(resultSet.getString("sku"));
        producto.setNombre(resultSet.getString("nombre"));
        producto.setPrecio(resultSet.getDouble("precio"));
        producto.setCategoria(new Categoria(resultSet.getLong("categoria_id"), resultSet.getString("categoria")));
        producto.setFechaRegistro(resultSet.getDate("fecha_registro").toLocalDate());
        return producto;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;

        try (PreparedStatement statement = this.connection.prepareStatement(
                "SELECT p.*, c.nombre as categoria FROM productos p INNER JOIN categorias c ON p.categoria_id = c.id WHERE p.id = ?")
        ) {
            statement.setLong(1, id); // seteamos el parametro

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) { // si retorna un registro
                    producto = this.getProducto(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;

        if (producto.getId() != null && producto.getId() > 0) {
            sql = "UPDATE productos SET nombre=?, precio=?, sku=?, categoria_id=? WHERE id=?";
        } else {
            sql = "INSERT INTO productos (nombre, precio, sku, categoria_id, fecha_registro) VALUES (?, ?, ?, ?, ?)";
        }

        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setString(3, producto.getSku());
            statement.setLong(4, producto.getCategoria().getId());
            if (producto.getId() != null && producto.getId() > 0) {
                statement.setLong(5, producto.getId());
            }else{
                statement.setDate(5, Date.valueOf(producto.getFechaRegistro()));
            }
            statement.executeUpdate();
        }


    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
