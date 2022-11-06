package com.example.autogaragebackend.repository;

import com.example.autogaragebackend.model.Medewerker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface MedewerkerRepository extends JpaRepository<Medewerker, Long> {

    public Collection<Medewerker> findAllByNaam(String naam);
    public Collection<Medewerker> findAllByRole(String role);
}
