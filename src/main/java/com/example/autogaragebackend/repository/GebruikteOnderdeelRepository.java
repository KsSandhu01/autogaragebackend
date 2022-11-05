package com.example.autogaragebackend.repository;

import com.example.autogaragebackend.model.GebruikteOnderdelen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GebruikteOnderdeelRepository extends JpaRepository<GebruikteOnderdelen, Long> {
}
