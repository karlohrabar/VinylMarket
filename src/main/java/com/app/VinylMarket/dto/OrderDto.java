package com.app.VinylMarket.dto;


import com.app.VinylMarket.entities.ItemEntity;
import com.app.VinylMarket.entities.UserEntity;
import com.app.VinylMarket.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private UUID id;                      // Order ID
    private String itemTitle;             // Item title
    private String buyerUsername;         // Buyer's username
    private String sellerUsername;
    private LocalDateTime timeStamp;      // Order timestamp
    private String orderStatus;
}
