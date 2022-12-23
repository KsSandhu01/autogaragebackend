package com.example.autogaragebackend.service.impl;

import com.example.autogaragebackend.dto.AuthenticationRequest;
import com.example.autogaragebackend.dto.TokenDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenCreationService tokenCreationService;

    public TokenDto login(AuthenticationRequest authenticationRequest){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        authenticationToken=(UsernamePasswordAuthenticationToken)authenticationManager.authenticate(authenticationToken);
        return tokenCreationService.generateToken(authenticationToken);
    }
}
