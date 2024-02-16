package com.app.VinylMarket.mappers;

import com.app.VinylMarket.dto.ItemDto;
import com.app.VinylMarket.entities.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper
public interface ItemMapper {

    ItemEntity toEntity(ItemDto itemDto);


    ItemDto toDto(ItemEntity itemEntity);

}
