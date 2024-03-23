package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.dto.UserData;
import com.ecommerce.ecommerce.model.dto.UserInfo;
import com.ecommerce.ecommerce.model.dto.UserUpdate;
import com.ecommerce.ecommerce.model.entity.User;
import com.ecommerce.ecommerce.model.response.GenericResponse;

import java.util.List;

public interface UserService {

    UserInfo createUser(UserData userData);

    GenericResponse getUser(String id);

    UserInfo updateUser(UserUpdate userUpdate);

    String deactivateUser(String id);

    String activateUser(String id);

    List<UserInfo> listActiveUser();

    List<UserInfo> listAllUsers();

}
