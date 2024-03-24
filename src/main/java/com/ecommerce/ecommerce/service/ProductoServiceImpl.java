package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.dto.producto.ProductoCreate;
import com.ecommerce.ecommerce.model.dto.producto.ProductoInfo;
import com.ecommerce.ecommerce.model.dto.producto.ProductoUpdate;
import com.ecommerce.ecommerce.model.entity.Producto;
import com.ecommerce.ecommerce.model.repository.ProductoRepository;
import com.ecommerce.ecommerce.model.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public GenericResponse getProduct(String id) {

        Producto producto = productoRepository.getReferenceById(Long.valueOf(id));

        if (producto == null) return GenericResponse.productoNoEncontrado();

        return GenericResponse.ok(infoProduct(producto));
    }

    @Override
    public GenericResponse getAllProducts() {
        List<ProductoInfo> allProducts = productoRepository.findAll()
                .stream()
                .map(this::infoProduct)
                .collect(Collectors.toList());

        return  GenericResponse.ok(allProducts);
    }

    @Override
    public GenericResponse getActiveProducs() {
        List<ProductoInfo> allActiveProducts = productoRepository.findByEstado(true)
                .stream()
                .map(this::infoProduct)
                .collect(Collectors.toList());

        return  GenericResponse.ok(allActiveProducts);
    }

    @Override
    public GenericResponse createProduct(ProductoCreate productoCreate) {

        Producto producto = Producto
                .builder()
                .nombre(productoCreate.nombre())
                .descripcion(productoCreate.descripcion())
                .precio(productoCreate.precio())
                .cantidad(productoCreate.cantidad())
                .estado(true)
                .build();

        productoRepository.save(producto);

        return  GenericResponse.ok(producto);
    }

    @Override
    public GenericResponse updateProduct(ProductoUpdate productoUpdate) {

        Producto producto = productoRepository.getReferenceById(productoUpdate.id());

        if (producto == null) return GenericResponse.productoNoEncontrado();

        producto.setNombre(productoUpdate.nombre());
        producto.setDescripcion(productoUpdate.descripcion());
        producto.setPrecio(productoUpdate.precio());
        producto.setCantidad(productoUpdate.cantidad());

        productoRepository.save(producto);

        return  GenericResponse.ok(infoProduct(producto));
    }

    @Override
    public GenericResponse deactivateProduct(String id) {
        Producto producto = productoRepository.getReferenceById(Long.valueOf(id));

        if (producto == null) return GenericResponse.productoNoEncontrado();

        producto.setEstado(false);
        productoRepository.save(producto);


        return GenericResponse.ok(infoProduct(producto));
    }

    @Override
    public GenericResponse activateProduct(String id) {
        Producto producto = productoRepository.getReferenceById(Long.valueOf(id));

        if (producto == null) return GenericResponse.productoNoEncontrado();

        producto.setEstado(true);
        productoRepository.save(producto);

        return GenericResponse.ok(infoProduct(producto));
    }


    public ProductoInfo infoProduct(Producto producto) {
        ProductoInfo infoProd =
                new ProductoInfo(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getDescripcion(),
                        producto.getPrecio(),
                        producto.getCantidad()
                );
        return infoProd;
    }
}
