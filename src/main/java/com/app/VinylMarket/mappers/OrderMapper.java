package com.app.VinylMarket.mappers;

import com.app.VinylMarket.dto.OrderDto;
import com.app.VinylMarket.entities.OrderEntity;

public abstract class OrderMapper {

    public static OrderDto toDto(OrderEntity orderEntity){
        var dto = new OrderDto();
        dto.setId(orderEntity.getId());
        dto.setOrderStatus(orderEntity.getStatus().toString());
        dto.setBuyerUsername(orderEntity.getBuyer().getUsername());
        dto.setItemTitle(orderEntity.getItem().getTitle());
        dto.setTimeStamp(orderEntity.getTime_stamp());

        return dto;
    }

}
