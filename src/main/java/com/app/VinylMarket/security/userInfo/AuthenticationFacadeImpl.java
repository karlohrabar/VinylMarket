package com.app.VinylMarket.security.userInfo;

import com.app.VinylMarket.entities.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade{
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public UUID getIdFromLoggedInUser() {
        UserEntity user = (UserEntity) getAuthentication().getPrincipal();
        return user.getId();
    }

    @Override
    public String getUsernameFromLoggedInUser() {
        UserEntity user = (UserEntity) getAuthentication().getPrincipal();
        return user.getUsername();
    }
}
