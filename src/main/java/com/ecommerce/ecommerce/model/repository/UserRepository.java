package com.ecommerce.ecommerce.model.repository;

import com.ecommerce.ecommerce.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEstado(boolean estado);

    Optional<User> findByUsername(String username);

}
