package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.dto.UserData;
import com.ecommerce.ecommerce.model.dto.UserUpdate;
import com.ecommerce.ecommerce.model.entity.User;
import com.ecommerce.ecommerce.model.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public User createUser(UserData userData) {
        User auxUser = User.builder()
                .userRol(userData.userRol())
                .username(userData.username())
                .password(userData.password())
                .build();

        userRepository.save(auxUser);

        return null;
    }

    @Override
    public void getUser (Long id) {

    }

    @Override
    public User updateUser(UserUpdate userUpdate) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
