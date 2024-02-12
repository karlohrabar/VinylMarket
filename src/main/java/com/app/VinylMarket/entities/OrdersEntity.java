package com.app.VinylMarket.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name="Orders")
@Entity
public class OrdersEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity userEntity;

    @OneToOne
    @JoinColumn
    private ItemEntity itemEntity;


}
