package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.dto.UserData;
import com.ecommerce.ecommerce.model.dto.UserUpdate;
import com.ecommerce.ecommerce.model.entity.User;

public interface UserService {

    User createUser(UserData userData);

    void getUser(Long id);

    User updateUser(UserUpdate userUpdate);

    void deleteUser(Long id);

}
