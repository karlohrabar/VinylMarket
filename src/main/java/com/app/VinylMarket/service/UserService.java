package com.app.VinylMarket.service;


import com.app.VinylMarket.dto.UserInfoDto;
import com.app.VinylMarket.dto.UserLoginDto;
import com.app.VinylMarket.entities.UserEntity;
import com.app.VinylMarket.enums.Role;
import com.app.VinylMarket.mappers.UserMapper;
import com.app.VinylMarket.mappers.UserMapperImpl;
import com.app.VinylMarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public void saveUser(UserLoginDto userLoginDto){
        UserMapper mapper = new UserMapperImpl();

        var user = mapper.toEntity(userLoginDto);
        user.setRole(Role.USER);
        user.setPasswd(passwordEncoder.encode(userLoginDto.getPasswd()));

        userRepository.save(user);
    }

    public Optional<UserEntity> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<UserEntity> findUserByUsername(String username){return userRepository.findByUsername(username);}

    public List<UserInfoDto> findAllUsers(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream().
                map((user)-> userMapper.toDto(user))
                .collect(Collectors.toList());
    }


}
