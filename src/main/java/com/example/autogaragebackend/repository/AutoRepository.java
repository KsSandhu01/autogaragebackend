package com.example.autogaragebackend.repository;

import com.example.autogaragebackend.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface AutoRepository extends JpaRepository<Auto, Long> {
    public Collection<Auto> findAllByModel(String model);
    public Collection<Auto> findAllByMerk(String merk);

 }
