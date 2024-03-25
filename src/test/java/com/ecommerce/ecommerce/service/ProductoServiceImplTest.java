package com.ecommerce.ecommerce.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ecommerce.ecommerce.model.dto.producto.ProductoCreate;
import com.ecommerce.ecommerce.model.dto.producto.ProductoInfo;
import com.ecommerce.ecommerce.model.entity.Producto;
import com.ecommerce.ecommerce.model.repository.ProductoRepository;
import com.ecommerce.ecommerce.model.response.GenericResponse;

class ProductoServiceImplTest {
    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProductById() {
        // Arrange
        Producto mockProducto = new Producto();
        mockProducto.setId(1L);
        mockProducto.setNombre("Producto de prueba");
        mockProducto.setDescripcion("Descripción de prueba");
        mockProducto.setPrecio(new BigDecimal("10.00"));
        mockProducto.setCantidad(5);

        when(productoRepository.getReferenceById(1L)).thenReturn(mockProducto);

        // Act
        GenericResponse response = productoService.getProduct("1");

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getCodigo());
        assertEquals("Producto de prueba", ((ProductoInfo) response.getData()).nombre());
    }

    @Test
    public void testGetAllProducts() {
        // Arrange
        Producto mockProducto1 = new Producto();
        mockProducto1.setId(1L);
        mockProducto1.setNombre("Producto 1");

        Producto mockProducto2 = new Producto();
        mockProducto2.setId(2L);
        mockProducto2.setNombre("Producto 2");

        when(productoRepository.findAll()).thenReturn(Arrays.asList(mockProducto1, mockProducto2));

        // Act
        GenericResponse response = productoService.getAllProducts();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getCodigo());
        assertEquals(2, ((List<ProductoInfo>) response.getData()).size());
    }

    @Test
    public void testCreateProduct() {
        // Arrange
        ProductoCreate productoCreate =
                new ProductoCreate(
                        "Nuevo Producto",
                        "Descripción del producto",
                        new BigDecimal("20.00"),
                        10);

        Producto mockProducto = new Producto();
        mockProducto.setId(1L);
        mockProducto.setNombre(productoCreate.nombre());
        mockProducto.setDescripcion(productoCreate.descripcion());
        mockProducto.setPrecio(productoCreate.precio());
        mockProducto.setCantidad(productoCreate.cantidad());

        when(productoRepository.save(any(Producto.class))).thenReturn(mockProducto);

        // Act
        GenericResponse response = productoService.createProduct(productoCreate);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getCodigo());
        assertEquals("Nuevo Producto", ((Producto) response.getData()).getNombre());
    }
}