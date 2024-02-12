package com.app.VinylMarket.entities;

import com.app.VinylMarket.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="User")
public class UserEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String passwd;

    @Column
    private String country;

    @OneToMany(mappedBy = "userEntity")
    List<OrdersEntity> ordersEntities;
}
