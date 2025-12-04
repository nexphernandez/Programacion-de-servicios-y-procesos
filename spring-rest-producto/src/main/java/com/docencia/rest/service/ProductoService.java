package com.docencia.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docencia.rest.domain.Producto;
import com.docencia.rest.mappers.DetalleProductoMapper;
import com.docencia.rest.mappers.ProductoMapper;
import com.docencia.rest.modelo.DetalleProductoDocument;
import com.docencia.rest.modelo.ProductoEntity;
import com.docencia.rest.repository.interfaces.IDetalleProductoRepository;
import com.docencia.rest.repository.interfaces.ProductoRepository;
import com.docencia.rest.service.interfaces.ProductoServicesInterface;

@Service
public class ProductoService implements ProductoServicesInterface {

    private ProductoMapper productoMapper;
    private DetalleProductoMapper detalleProductoMapper;

    private ProductoRepository productoRepository;
    private IDetalleProductoRepository detalleProductoRepository;

    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Autowired
    public void setDetalleProductoRepository(IDetalleProductoRepository detalleProductoRepository) {
        this.detalleProductoRepository = detalleProductoRepository;
    }

    @Autowired
    public void setProductoMapper(ProductoMapper productoMapper) {
        this.productoMapper = productoMapper;
    }

    @Autowired
    public void setDetalleProductoMapper(DetalleProductoMapper detalleProductoMapper) {
        this.detalleProductoMapper = detalleProductoMapper;
    }

    @Override
    public Optional<Producto> findById(int id) {
        Optional<ProductoEntity> entityOpt = productoRepository.findById(id);
        if (entityOpt.isEmpty()) {
            return Optional.empty();
        }

        ProductoEntity entity = entityOpt.get();
        DetalleProductoDocument detalleDoc = detalleProductoRepository.findByProductoId(id).get();

        Producto producto = productoMapper.toDomain(entity, detalleDoc);
        return Optional.of(producto);
    }

    @Override
    public Optional<Producto> find(Producto producto) {
        return findById(producto.getId());
    }

    @Override
    public List<Producto> findAll() {
         List<ProductoEntity> entities = productoRepository.findAll();

        return entities.stream()
                .map(entity -> {
                    DetalleProductoDocument detalleDoc =
                            detalleProductoRepository.findByProductoId(entity.getId()).orElse(null);
                    return productoMapper.toDomain(entity, detalleDoc);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Producto save(Producto producto) {
        ProductoEntity entityToSave = productoMapper.toProducto(producto);
        entityToSave = productoRepository.save(entityToSave);
        if (producto.getDetalleProducto() == null) {
            return productoMapper.toProducto(entityToSave);
        }
        DetalleProductoDocument detalleDoc = detalleProductoMapper.toDocument(producto.getDetalleProducto());
        detalleDoc.setProductoId(entityToSave.getId());
        detalleDoc = detalleProductoRepository.save(detalleDoc);
        return productoMapper.toDomain(entityToSave, detalleDoc);

    }

    @Override
    public boolean deleteById(int id) {
        Producto producto = new Producto(id);
        return delete(producto);
    }

    @Override
    public boolean delete(Producto producto) {
        if (producto == null) {
            return false;
        }
        if (!productoRepository.existsById(producto.getId())) {
            return false;
        }
        int id = producto.getId();

        productoRepository.deleteById(id);
        detalleProductoRepository.deleteById(id);
        return true;
    }

    
}
