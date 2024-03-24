package com.ecommerce.ecommerce.model.dto.producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductoCreate(
        @NotBlank
        String nombre,
        @NotBlank
        String descripcion,
        @NotNull
        BigDecimal precio,
        @NotNull
        int cantidad
) {
}
