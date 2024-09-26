package com.app.VinylMarket.entities;

import com.app.VinylMarket.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "Orders")
public class OrderEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity item; // Reference to the ordered item

    @ManyToOne // Assuming this is a relationship to the UserEntity for the buyer
    @JoinColumn(name = "buyer_id")
    private UserEntity buyer;

    @ManyToOne // Relationship to the UserEntity for the seller
    @JoinColumn(name = "seller_id")
    private UserEntity seller; // Add this line

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "time_stamp", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime time_stamp;
}
