package com.example.autogaragebackend.service;

import com.example.autogaragebackend.model.Medewerker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public interface MedewerkerService  extends UserDetailsService {

    public abstract long createMedewerker(Medewerker medewerker);
    public abstract void updateMedewerker(long id, Medewerker medewerker);
    public abstract void updateDeelVanMedewerker(long id, Map<String, String> velden);
    public abstract void deleteMedewerker(long id);
    public abstract Collection<Medewerker> getMedewerkers();
    public abstract Collection<Medewerker> getMedewerkerMetNaam(String naam);
    public abstract Collection<Medewerker> getMedewerkerMetRole(String role);
    public abstract Optional<Medewerker> getMedewerkerMetId(long id);
    public abstract boolean bestaatMedewerkerMetId(long id);
}
