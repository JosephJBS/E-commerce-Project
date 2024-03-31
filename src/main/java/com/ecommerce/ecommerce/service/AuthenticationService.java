package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.config.security.AuthenticationResponse;
import com.ecommerce.ecommerce.config.security.JwtService;
import com.ecommerce.ecommerce.model.constans.UserRol;
import com.ecommerce.ecommerce.model.dto.user.DatosAutenticacionUsuario;
import com.ecommerce.ecommerce.model.dto.user.UserData;
import com.ecommerce.ecommerce.model.entity.User;
import com.ecommerce.ecommerce.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserData request) {
        var user = User.builder()
                .password(request.password())
                .username(request.username())
                .userRol(UserRol.CLNT)
                .password(passwordEncoder.encode(request.password()))
                .estado(true)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(DatosAutenticacionUsuario request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        var user = userRepository.findByUsername(request.username())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        log.info("Auntenticacion correcta - Se genera token : {} ",jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


}
