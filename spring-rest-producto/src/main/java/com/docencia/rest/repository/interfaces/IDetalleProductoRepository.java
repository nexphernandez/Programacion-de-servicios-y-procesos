package com.docencia.rest.repository.interfaces;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.docencia.rest.modelo.DetalleProductoDocument;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
public interface IDetalleProductoRepository extends MongoRepository<DetalleProductoDocument, Integer>{
    Optional<DetalleProductoDocument> findByProductoId(int productoId);
    boolean deleteByProductoId(int productoId);
}
