package com.app.VinylMarket.security.userInfo;

import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface AuthenticationFacade {
    Authentication getAuthentication();

    UUID getIdFromLoggedInUser();

    String getUsernameFromLoggedInUser();

}
