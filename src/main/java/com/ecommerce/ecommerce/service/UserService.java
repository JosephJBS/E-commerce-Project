package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.dto.UserData;
import com.ecommerce.ecommerce.model.dto.UserInfo;
import com.ecommerce.ecommerce.model.dto.UserUpdate;
import com.ecommerce.ecommerce.model.entity.User;

import java.util.List;

public interface UserService {

    UserInfo createUser(UserData userData);

    UserInfo getUser(Long id);

    UserInfo updateUser(UserUpdate userUpdate);

    String deactivateUser(Long id);

    String activateUser(Long id);

    List<UserInfo> listActiveUser();

    List<UserInfo> listAllUsers();

}
