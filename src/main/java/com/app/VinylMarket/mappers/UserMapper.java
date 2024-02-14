package com.app.VinylMarket.mappers;


import com.app.VinylMarket.dto.UserAuthDto;
import com.app.VinylMarket.dto.UserInfoDto;
import com.app.VinylMarket.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface UserMapper {

    @Mapping(source = "passwd", target = "passwd", ignore = true)
    UserEntity toEntity(UserAuthDto userAuthDto);


    @Mapping(source = "username",target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "country", target = "country")
    UserInfoDto toDto(UserEntity userEntity);
}
