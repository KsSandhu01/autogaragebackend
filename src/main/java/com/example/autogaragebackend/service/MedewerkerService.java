package com.example.autogaragebackend.service;

import com.example.autogaragebackend.dto.MedewerkerDto;
import com.example.autogaragebackend.model.Medewerker;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface MedewerkerService {

    long createMedewerker(MedewerkerDto medewerker);
    void updateMedewerker(long id, MedewerkerDto medewerker);
    void updateDeelVanMedewerker(MedewerkerDto velden);
    void deleteMedewerker(long id);
    Collection<Medewerker> getMedewerkers();
    Collection<Medewerker> getMedewerkerMetNaam(String naam);
    Collection<Medewerker> getMedewerkerMetRole(String role);
    Optional<Medewerker> getMedewerkerMetId(long id);
    boolean bestaatMedewerkerMetId(long id);
}
