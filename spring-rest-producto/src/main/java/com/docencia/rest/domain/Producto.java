package com.docencia.rest.domain;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author nexphernandez
 * @version 1.0.0
 */

public class Producto {

    private int id;

    private String nombre;

    private BigDecimal precio;

    private int stock;

    private DetalleProducto detalleProducto;

    /**
     * Constructor vacio
     */
    public Producto() {
    }

    /**
     * Consturctor con los atributos de la producto
     * 
     * @param nombre nombre del producto
     * @param precio precio del producto
     * @param stock  stock del producto
     */
    public Producto(String nombre, BigDecimal precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    

    public Producto(int id, String nombre, BigDecimal precio, int stock, DetalleProducto detalleProducto) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.detalleProducto = detalleProducto;
    }

    /**
     * Constructor con el identificador de la producto
     * 
     * @param id identificador del producto
     */
    public Producto(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DetalleProducto getDetalleProducto() {
        return detalleProducto;
    }

    public void setDetalleProducto(DetalleProducto detalleProducto) {
        this.detalleProducto = detalleProducto;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Producto other = (Producto) obj;
        return id == other.id;
    }

}