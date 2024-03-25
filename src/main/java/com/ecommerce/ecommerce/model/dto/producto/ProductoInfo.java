package com.ecommerce.ecommerce.model.dto.producto;

import java.math.BigDecimal;

public record ProductoInfo(
        Long id,
        String nombre,
        String descripcion,
        BigDecimal precio,
        int cantidad,
        boolean estado
) {
}
