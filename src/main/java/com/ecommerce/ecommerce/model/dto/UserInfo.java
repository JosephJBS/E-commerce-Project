package com.ecommerce.ecommerce.model.dto;

import com.ecommerce.ecommerce.model.UserRol;

public record UserInfo(
        UserRol userRol,
        String username,
        boolean estado
) {
}
