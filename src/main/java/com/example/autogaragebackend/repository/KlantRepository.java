package com.example.autogaragebackend.repository;

import com.example.autogaragebackend.model.Klant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KlantRepository extends JpaRepository<Klant, Long> {
}
