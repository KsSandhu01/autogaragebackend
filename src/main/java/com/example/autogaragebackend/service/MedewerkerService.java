package com.example.autogaragebackend.service;

import com.example.autogaragebackend.dto.MedewerkerDto;
import com.example.autogaragebackend.model.Medewerker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public interface MedewerkerService {

    public abstract long createMedewerker(MedewerkerDto medewerker);
    public abstract void updateMedewerker(long id, MedewerkerDto medewerker);
    public abstract void updateDeelVanMedewerker( MedewerkerDto velden);
    public abstract void deleteMedewerker(long id);
    public abstract Collection<Medewerker> getMedewerkers();
    public abstract Collection<Medewerker> getMedewerkerMetNaam(String naam);
    public abstract Collection<Medewerker> getMedewerkerMetRole(String role);
    public abstract Optional<Medewerker> getMedewerkerMetId(long id);
    public abstract boolean bestaatMedewerkerMetId(long id);
}
