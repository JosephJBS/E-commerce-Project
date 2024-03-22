package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.dto.UserData;
import com.ecommerce.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/mensaje")
    public String mostrarMensaje(){
        return "Hola";
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserData userData){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(userData));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }

    }


}
