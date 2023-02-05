package com.example.autogaragebackend.service;

import com.example.autogaragebackend.dto.HandelingDto;
import com.example.autogaragebackend.model.Handeling;

import java.util.Collection;
import java.util.Optional;

public interface HandelingService {

    long createHandeling(HandelingDto handeling);
    void updateHandeling(long id, HandelingDto handeling);
    void updateDeelVanHandeling(long id, HandelingDto fields);
    void deleteHandeling(long id);
    Collection<Handeling> getHandelingen();
    Optional<Handeling> getHandelingById(long id);
    boolean handelingExistsById(long id);
}
