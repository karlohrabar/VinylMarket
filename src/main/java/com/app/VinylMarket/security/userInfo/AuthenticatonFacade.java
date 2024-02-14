package com.app.VinylMarket.security.userInfo;

import org.springframework.security.core.Authentication;

public interface AuthenticatonFacade {
    Authentication getAuthentication();
}
