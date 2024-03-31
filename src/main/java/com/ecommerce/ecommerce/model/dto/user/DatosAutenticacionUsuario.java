package com.ecommerce.ecommerce.model.dto.user;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
        @NotBlank
        String username,
        @NotBlank
        String password){
}
