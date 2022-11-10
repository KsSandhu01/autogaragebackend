package com.example.autogaragebackend.util;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.autogaragebackend.model.Medewerker;

public class SpringUser implements UserDetails{
    private Medewerker user;

    public SpringUser(Medewerker user) {
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
