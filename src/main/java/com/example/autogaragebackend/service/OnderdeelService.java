package com.example.autogaragebackend.service;

import com.example.autogaragebackend.dto.OnderdeelDto;
import com.example.autogaragebackend.model.Onderdeel;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface OnderdeelService {

    long createOnderdeel(OnderdeelDto onderdeel);
    void updateOnderdeel(long id, OnderdeelDto onderdeel);
    void updateDeelVanOnderdeel(long id, OnderdeelDto fields);
    void deleteOnderdeel(long id);
    Collection<Onderdeel> getOnderdelen();
    Optional<Onderdeel> getOnderdeelById(long id);
    boolean OnderdeelExistsById(long id);
}
