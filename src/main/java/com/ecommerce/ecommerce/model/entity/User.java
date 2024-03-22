package com.ecommerce.ecommerce.model.entity;

import com.ecommerce.ecommerce.model.UserRol;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private UserRol userRol;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "estado")
    private boolean estado;
}
