package com.ecommerce.ecommerce.model.dto.user;

import com.ecommerce.ecommerce.model.constans.UserRol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserData(
        @NotNull
        UserRol userRol,
        @NotBlank
        String username,
        @NotBlank
        String password

) {
}
