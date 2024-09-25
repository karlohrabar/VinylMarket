package com.app.VinylMarket.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

public class CustomUserDetails implements UserDetails {

    // Getter for ID
    @Getter
    private final UUID id; // Add this field
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    // Constructor
    public CustomUserDetails(UUID id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id; // Initialize user ID
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    // Other overridden methods from UserDetails...
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
