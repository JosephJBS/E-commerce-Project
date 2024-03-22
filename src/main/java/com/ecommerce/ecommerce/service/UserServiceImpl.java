package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.dto.UserData;
import com.ecommerce.ecommerce.model.dto.UserInfo;
import com.ecommerce.ecommerce.model.dto.UserUpdate;
import com.ecommerce.ecommerce.model.entity.User;
import com.ecommerce.ecommerce.model.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserInfo createUser(UserData userData) {
        User auxUser =
                User.builder()
                .userRol(userData.userRol())
                .username(userData.username())
                .password(userData.password())
                .build();

        userRepository.save(auxUser);

        log.info("UserService - Create User : {}",infoUser(auxUser));

        return infoUser(auxUser);
    }

    @Override
    public UserInfo getUser (Long id) {
        User auxUser = userRepository.getReferenceById(id);
        UserInfo auxInfoUser = infoUser(auxUser);

        log.info("UserService - Get User : {}",auxInfoUser);

        return auxInfoUser;
    }

    @Override
    public UserInfo updateUser(UserUpdate userUpdate) {
        User auxUser = userRepository.getReferenceById(userUpdate.id());

        if (auxUser == null) return null;

        auxUser.setUserRol(userUpdate.userRol());
        auxUser.setUsername(userUpdate.username());
        auxUser.setPassword(userUpdate.password());

        userRepository.save(auxUser);

        log.info("UserService - Update User : {}",infoUser(auxUser));

        return infoUser(auxUser);

    }

    @Override
    public String deactivateUser(Long id) {
        if (userRepository.getReferenceById(id) == null) return  "Usuario con id : " + id + " no existe";


        User auxUser = userRepository.getReferenceById(id);
        auxUser.setEstado(false);
        userRepository.save(auxUser);

        log.info("UserService - Deactivate User whit id : {}",id);

        return  "Usuario con id : " + id + " de ha desactivado";

    }

    @Override
    public String activateUser(Long id) {
        if (userRepository.getReferenceById(id) == null) return  "Usuario con id : " + id + " no existe";

        User auxUser = userRepository.getReferenceById(id);
        auxUser.setEstado(true);
        userRepository.save(auxUser);

        log.info("UserService - Activate User : {}",infoUser(auxUser));

        return  "Usuario con id : " + id + " de ha activado";
    }

    @Override
    public List<UserInfo> listActiveUser() {
        List<UserInfo> activeUsers = userRepository.findByEstado(true)
                .stream()
                .map(this::infoUser)
                .collect(Collectors.toList());

        return activeUsers;
    }

    @Override
    public List<UserInfo> listAllUsers() {
        List<UserInfo> allUsers = userRepository.findAll()
                .stream()
                .map(this::infoUser)
                .collect(Collectors.toList());

        return allUsers;
    }

    public UserInfo infoUser (User user){
        UserInfo auxInfoUser =
                new UserInfo(
                        user.getUserRol(),
                        user.getUsername(),
                        user.isEstado()
                );
        return auxInfoUser;
    }
}
