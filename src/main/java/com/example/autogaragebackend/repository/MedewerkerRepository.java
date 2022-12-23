package com.example.autogaragebackend.repository;

import com.example.autogaragebackend.model.Medewerker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface MedewerkerRepository extends JpaRepository<Medewerker, Long> {

     Collection<Medewerker> findAllByNaam(String naam);
     Collection<Medewerker> findAllByRole(String role);
     Optional<Medewerker> findByGebruikersnaam(String gebruikersnaam);

}
