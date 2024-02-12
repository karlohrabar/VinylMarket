package com.app.VinylMarket.mappers;


import com.app.VinylMarket.dto.UserInfoDto;
import com.app.VinylMarket.dto.UserLoginDto;
import com.app.VinylMarket.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Bean;


@Mapper
public interface UserMapper {

    @Mapping(source = "passwd", target = "passwd", ignore = true)
    UserEntity toEntity(UserLoginDto userLoginDto);


    @Mapping(source = "username",target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "country", target = "country")
    UserInfoDto toDto(UserEntity userEntity);
}
