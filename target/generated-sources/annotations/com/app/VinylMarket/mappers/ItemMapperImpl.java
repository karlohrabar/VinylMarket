package com.app.VinylMarket.mappers;

import com.app.VinylMarket.dto.ItemDto;
import com.app.VinylMarket.entities.ItemEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-05T16:37:59+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Ubuntu)"
)
public class ItemMapperImpl implements ItemMapper {

    @Override
    public ItemEntity toEntity(ItemDto itemDto) {
        if ( itemDto == null ) {
            return null;
        }

        ItemEntity itemEntity = new ItemEntity();

        itemEntity.setTitle( itemDto.getTitle() );
        itemEntity.setFormat( itemDto.getFormat() );
        itemEntity.setRpm( itemDto.getRpm() );
        itemEntity.setGenre( itemDto.getGenre() );
        itemEntity.setYear_of_release( itemDto.getYear_of_release() );
        itemEntity.setCondition_of_item( itemDto.getCondition_of_item() );
        itemEntity.setOther_info( itemDto.getOther_info() );
        itemEntity.setCountry( itemDto.getCountry() );
        itemEntity.setLabel( itemDto.getLabel() );
        itemEntity.setPrice( itemDto.getPrice() );
        itemEntity.setPhoto( itemDto.getPhoto() );

        return itemEntity;
    }

    @Override
    public ItemDto toDto(ItemEntity itemEntity) {
        if ( itemEntity == null ) {
            return null;
        }

        ItemDto itemDto = new ItemDto();

        itemDto.setTitle( itemEntity.getTitle() );
        itemDto.setFormat( itemEntity.getFormat() );
        itemDto.setRpm( itemEntity.getRpm() );
        itemDto.setGenre( itemEntity.getGenre() );
        itemDto.setYear_of_release( itemEntity.getYear_of_release() );
        itemDto.setCondition_of_item( itemEntity.getCondition_of_item() );
        itemDto.setOther_info( itemEntity.getOther_info() );
        itemDto.setCountry( itemEntity.getCountry() );
        itemDto.setLabel( itemEntity.getLabel() );
        itemDto.setPrice( itemEntity.getPrice() );
        itemDto.setPhoto( itemEntity.getPhoto() );

        return itemDto;
    }
}
