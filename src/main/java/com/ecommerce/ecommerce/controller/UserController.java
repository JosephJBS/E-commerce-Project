package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.dto.UserData;
import com.ecommerce.ecommerce.model.dto.UserUpdate;
import com.ecommerce.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/mensaje")
    public String mostrarMensaje(){
        return "Hola";
    }


    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserData userData){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(userData));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUser(@RequestParam long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
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
    public ResponseEntity<?> deactivateUSer (@RequestParam long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.deactivateUser(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @PutMapping("/activate")
    public ResponseEntity<?> activateUSer (@RequestParam long id){
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
