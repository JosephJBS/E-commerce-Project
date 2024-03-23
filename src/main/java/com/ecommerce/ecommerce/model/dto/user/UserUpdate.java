package com.ecommerce.ecommerce.model.dto.user;

import com.ecommerce.ecommerce.model.UserRol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserUpdate(
        @NotNull
        long id,
        @NotNull
        UserRol userRol,
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
