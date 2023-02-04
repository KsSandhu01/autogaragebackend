package com.example.autogaragebackend.security;


import com.example.autogaragebackend.model.Medewerker;
import com.example.autogaragebackend.repository.MedewerkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MedewerkerRepository medewerkerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Medewerker> user = medewerkerRepository.findByGebruikersnaam(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user.get());
    }
}
