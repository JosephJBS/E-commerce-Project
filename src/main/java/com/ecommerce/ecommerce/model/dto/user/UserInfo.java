package com.ecommerce.ecommerce.model.dto.user;

import com.ecommerce.ecommerce.model.constans.UserRol;

public record UserInfo(
        UserRol userRol,
        String username,
        boolean estado
) {
}
