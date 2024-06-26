package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.dto.user.UserData;
import com.ecommerce.ecommerce.model.dto.user.UserUpdate;
import com.ecommerce.ecommerce.model.response.GenericResponse;
import com.ecommerce.ecommerce.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserData userData){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(userData));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/get")
    public ResponseEntity<GenericResponse> getUser(@RequestParam @Pattern(regexp = "\\d+") String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GenericResponse.errorDatosInvalidos("El ID debe ser un número válido"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenericResponse.errorDatosInvalidos(e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserUpdate userUpdate){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userUpdate));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @PutMapping("/delete")
    public ResponseEntity<?> deactivateUSer (@RequestParam @Pattern(regexp = "\\d+") String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.deactivateUser(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @PutMapping("/activate")
    public ResponseEntity<?> activateUSer (@RequestParam @Pattern(regexp = "\\d+") String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.activateUser(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/active-users")
    public ResponseEntity<?> getListActiveUser (){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.listActiveUser());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/all-users")
    public ResponseEntity<?> getListAllUser (){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.listAllUsers());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }


}
