package com.app.VinylMarket.security;

import com.app.VinylMarket.entities.UserEntity;
import com.app.VinylMarket.enums.Role;
import com.app.VinylMarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var userEntity = userRepository.findByUsername(username);

        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            System.out.println("Login successful!");
            return new CustomUserDetails(user.getId(),
                    user.getUsername(),
                    user.getPasswd(),
                    mapRoleToGrantedAuthority(user.getRole()));
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

    }

    private Collection<GrantedAuthority> mapRoleToGrantedAuthority(Role role) {
        var myRole = new ArrayList<Role>();
        myRole.add(role);
        return myRole.stream().map(elem -> new SimpleGrantedAuthority(elem.name())).collect(Collectors.toList());
    }
}
