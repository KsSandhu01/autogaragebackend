package com.example.autogaragebackend.service.impl;

import com.example.autogaragebackend.dto.AuthenticationRequest;
import com.example.autogaragebackend.dto.TokenDto;
import com.example.autogaragebackend.security.JwtHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.autogaragebackend.security.configuration.SpringSecurityConfig.AUTHORITIES_CLAIM_NAME;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final JwtHelper jwtHelper;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public TokenDto login(AuthenticationRequest authenticationRequest) {
        {
            UserDetails userDetails;
            try {
                userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            } catch (UsernameNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
            }

            if (passwordEncoder.matches(authenticationRequest.getPassword(), userDetails.getPassword())) {
                Map<String, String> claims = new HashMap<>();
                claims.put("username", authenticationRequest.getUsername());

                String authorities = userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(" "));
                claims.put(AUTHORITIES_CLAIM_NAME, authorities);
                claims.put("userId", String.valueOf(1));

                String jwt = jwtHelper.createJwtForClaims(authenticationRequest.getUsername(), claims);
                return TokenDto.builder().token(jwt).build();
            }

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
    }
}