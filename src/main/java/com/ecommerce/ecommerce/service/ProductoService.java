package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.dto.producto.ProductoCreate;
import com.ecommerce.ecommerce.model.dto.producto.ProductoUpdate;
import com.ecommerce.ecommerce.model.response.GenericResponse;

public interface ProductoService {

    GenericResponse getProduct(String id);

    GenericResponse getAllProducts();

    GenericResponse getActiveProducs();

    GenericResponse createProduct(ProductoCreate productoCreate);

    GenericResponse updateProduct(ProductoUpdate productoUpdate);

    GenericResponse deactivateProduct(String id);

    GenericResponse activateProduct(String id);

}
