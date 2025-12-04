package com.docencia.rest.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.docencia.rest.domain.Producto;
import com.docencia.rest.modelo.ProductoEntity;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
public interface ProductoServicesInterface {
    /**
     * Funcion que encuentra un producto sabiendo su id
     * @param id identificador del producto a buscar
     * @return Optional con el producto buscado/null
     */
    Optional<Producto> findById(int id);

    /**
     * Funcion que encuentra un producto
     * @param producto producto buscado
     * @return Optional con el producto buscado/null
     */
    Optional<Producto> find(Producto producto);

    /**
     * Funcion que busca todo los producto de la bbdd
     * @return lista con todo los productos
     */
    List<Producto> findAll();

    /**
     * Funcion que guarda un producto en la bbbd
     * @param producto producto a aniadir
     * @return devuelve el producto guardado/null
     */
    Producto save(Producto producto);

    /**
     * Funcion que borra un producto de la bbd sabiendo su id
     * @param id identificador del producto
     * @return true/false
     */
    boolean deleteById(int id);

    /**
     * Funcion que borra un producto de la bbd
     * @param producto producto a eliminar
     * @return true/false
     */
    boolean delete(Producto producto);
}
