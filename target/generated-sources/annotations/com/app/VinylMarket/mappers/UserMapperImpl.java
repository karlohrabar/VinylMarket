package com.app.VinylMarket.mappers;

import com.app.VinylMarket.dto.UserInfoDto;
import com.app.VinylMarket.dto.UserLoginDto;
import com.app.VinylMarket.entities.UserEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-12T23:04:57+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (N/A)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toEntity(UserLoginDto userLoginDto) {
        if ( userLoginDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( userLoginDto.getUsername() );
        userEntity.setEmail( userLoginDto.getEmail() );
        userEntity.setCountry( userLoginDto.getCountry() );

        return userEntity;
    }

    @Override
    public UserInfoDto toDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserInfoDto userInfoDto = new UserInfoDto();

        userInfoDto.setUsername( userEntity.getUsername() );
        userInfoDto.setEmail( userEntity.getEmail() );
        userInfoDto.setCountry( userEntity.getCountry() );

        return userInfoDto;
    }
}
