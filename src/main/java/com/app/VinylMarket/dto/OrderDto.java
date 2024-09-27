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

    private UUID id;
    private String itemTitle;
    private String buyerUsername;
    private String sellerUsername;
    private LocalDateTime timeStamp;
    private String orderStatus;
}
