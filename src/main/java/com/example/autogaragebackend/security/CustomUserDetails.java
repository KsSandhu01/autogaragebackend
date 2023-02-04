package com.example.autogaragebackend.security;

import com.example.autogaragebackend.model.Medewerker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private Medewerker user;

    public CustomUserDetails(Medewerker user) {
        this.user=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority=new SimpleGrantedAuthority(this.user.getRole().toString());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return this.user.getWachtwoord();
    }

    @Override
    public String getUsername() {
        return this.user.getGebruikersnaam();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}
