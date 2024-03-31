package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.dto.user.DatosAutenticacionUsuario;
import com.ecommerce.ecommerce.model.dto.user.UserData;
import com.ecommerce.ecommerce.service.AuthenticationService;
import com.ecommerce.ecommerce.config.security.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserData request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody DatosAutenticacionUsuario request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }


}