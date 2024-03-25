package com.ecommerce.ecommerce.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.ecommerce.ecommerce.model.constans.RespuestasEnum;
import com.ecommerce.ecommerce.model.dto.producto.ProductoUpdate;
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
import org.springframework.http.HttpStatus;

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

    @Test
    public void productoNoEncontradoTest(){
        String idNotRegister = "2";

        Producto mockProducto = new Producto();
        mockProducto.setId(1L);
        mockProducto.setNombre("Producto de prueba");
        mockProducto.setDescripcion("Descripción de prueba");
        mockProducto.setPrecio(new BigDecimal("10.00"));
        mockProducto.setCantidad(5);

        when(productoRepository.getReferenceById(1L)).thenReturn(mockProducto);

        // Act
        GenericResponse response = productoService.getProduct(idNotRegister);

        // Assert
        assertNull(response.getData());
        assertEquals(405, response.getCodigo());
    }

    @Test
    public void getActiveProductsList(){
        // Arrange
        Producto mockProducto1 = new Producto();
        mockProducto1.setId(1L);
        mockProducto1.setNombre("Producto 1");
        mockProducto1.setEstado(true);

        Producto mockProducto2 = new Producto();
        mockProducto2.setId(2L);
        mockProducto2.setNombre("Producto 2");
        mockProducto2.setEstado(true);


        when(productoRepository.findByEstado(true))
                .thenReturn(Arrays.asList(mockProducto1, mockProducto2));

        GenericResponse response = productoService.getActiveProducs();
        assertTrue(response.getData() instanceof List);
        assertEquals(2, ((List<ProductoInfo>) response.getData()).size());

    }

    @Test
    public void updateProductTest(){

        Producto mockProducto = new Producto();
        mockProducto.setId(1L);
        mockProducto.setNombre("Producto de prueba");
        mockProducto.setDescripcion("Descripción de prueba");
        mockProducto.setPrecio(new BigDecimal("10.00"));
        mockProducto.setCantidad(5);

        ProductoUpdate updateProd =
                new ProductoUpdate(
                        1,
                        "Producto modificado",
                        "Desc modificiada",
                        new BigDecimal(45.5),
                        45);


        when(productoRepository.getReferenceById(1L)).thenReturn(mockProducto);

        // Act
        GenericResponse response = productoService.updateProduct(updateProd);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getCodigo());

        ProductoInfo updatedInfo = (ProductoInfo) response.getData();

        assertEquals(updateProd.nombre(), updatedInfo.nombre());
        assertEquals(updateProd.descripcion(), updatedInfo.descripcion());
        assertEquals(updateProd.precio(), updatedInfo.precio());
        assertEquals(updateProd.cantidad(), updatedInfo.cantidad());

    }

    @Test
    public void productoInactivoTest(){
        Producto mockProducto = new Producto();
        mockProducto.setId(1L);
        mockProducto.setNombre("Producto de prueba");
        mockProducto.setDescripcion("Descripción de prueba");
        mockProducto.setPrecio(new BigDecimal("10.00"));
        mockProducto.setCantidad(5);
        mockProducto.setEstado(true);

        when(productoRepository.getReferenceById(1L)).thenReturn(mockProducto);

        GenericResponse response = productoService.deactivateProduct(String.valueOf(mockProducto.getId()));

        assertEquals(200, response.getCodigo());
        assertFalse(((ProductoInfo)response.getData()).estado());
    }

    @Test
    public void productoYaInactivoTest(){
        Producto mockProducto = new Producto();
        mockProducto.setId(1L);
        mockProducto.setNombre("Producto de prueba");
        mockProducto.setDescripcion("Descripción de prueba");
        mockProducto.setPrecio(new BigDecimal("10.00"));
        mockProducto.setCantidad(5);
        mockProducto.setEstado(false);

        when(productoRepository.getReferenceById(1L)).thenReturn(mockProducto);

        GenericResponse response = productoService.deactivateProduct(String.valueOf(mockProducto.getId()));

        assertEquals(409, response.getCodigo());
        assertEquals(RespuestasEnum.PRODUCTO_YA_INACTIVO.mensaje(), response.getMensaje());
    }

    @Test
    public void productoActivoTest(){
        Producto mockProducto = new Producto();
        mockProducto.setId(1L);
        mockProducto.setNombre("Producto de prueba");
        mockProducto.setDescripcion("Descripción de prueba");
        mockProducto.setPrecio(new BigDecimal("10.00"));
        mockProducto.setCantidad(5);
        mockProducto.setEstado(false);

        when(productoRepository.getReferenceById(1L)).thenReturn(mockProducto);

        GenericResponse response = productoService.activateProduct(String.valueOf(mockProducto.getId()));

        assertEquals(200, response.getCodigo());
        assertTrue(((ProductoInfo)response.getData()).estado());
    }


    @Test
    public void productoYaActivoTest(){
        Producto mockProducto = new Producto();
        mockProducto.setId(1L);
        mockProducto.setNombre("Producto de prueba");
        mockProducto.setDescripcion("Descripción de prueba");
        mockProducto.setPrecio(new BigDecimal("10.00"));
        mockProducto.setCantidad(5);
        mockProducto.setEstado(true);

        when(productoRepository.getReferenceById(1L)).thenReturn(mockProducto);

        GenericResponse response = productoService.activateProduct(String.valueOf(mockProducto.getId()));

        assertEquals(409, response.getCodigo());
        assertEquals(RespuestasEnum.PRODUCTO_YA_ACTIVO.mensaje(), response.getMensaje());
    }


}