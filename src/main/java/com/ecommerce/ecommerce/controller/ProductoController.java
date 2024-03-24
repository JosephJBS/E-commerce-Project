package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.dto.producto.ProductoCreate;
import com.ecommerce.ecommerce.model.dto.producto.ProductoUpdate;
import com.ecommerce.ecommerce.model.response.GenericResponse;
import com.ecommerce.ecommerce.service.ProductoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/producto")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @PostMapping("/create")
    public ResponseEntity<GenericResponse> createProduct (@RequestBody @Valid ProductoCreate productoCreate){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productoService.createProduct(productoCreate));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenericResponse.errorDatosInvalidos(e.getMessage()));
        }
    }

    @GetMapping("/get")
    public ResponseEntity<GenericResponse> getProduct(@RequestParam @Pattern(regexp = "\\d+") String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productoService.getProduct(id));
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GenericResponse.errorDatosInvalidos("El ID debe ser un número válido"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenericResponse.errorDatosInvalidos(e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<GenericResponse> updateProduct(@RequestBody @Valid ProductoUpdate productUpdate){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productoService.updateProduct(productUpdate));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenericResponse.errorDatosInvalidos(e.getMessage()));
        }
    }

    @PutMapping("/delete")
    public ResponseEntity<GenericResponse> deactivateProduct (@RequestParam @Pattern(regexp = "\\d+") String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productoService.deactivateProduct(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenericResponse.errorDatosInvalidos(e.getMessage()));
        }
    }

    @PutMapping("/activate")
    public ResponseEntity<GenericResponse> activateProduct (@RequestParam @Pattern(regexp = "\\d+") String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productoService.activateProduct(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenericResponse.errorDatosInvalidos(e.getMessage()));
        }
    }

    @GetMapping("/active-products")
    public ResponseEntity<GenericResponse> getListActiveProduct (){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productoService.getActiveProducs());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenericResponse.errorDatosInvalidos(e.getMessage()));
        }
    }

    @GetMapping("/all-products")
    public ResponseEntity<GenericResponse> getListAllProduct (){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productoService.getAllProducts());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenericResponse.errorDatosInvalidos(e.getMessage()));
        }
    }

}
