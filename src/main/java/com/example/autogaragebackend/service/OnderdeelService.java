package com.example.autogaragebackend.service;

import com.example.autogaragebackend.dto.OnderdeelDto;
import com.example.autogaragebackend.model.Onderdeel;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public interface OnderdeelService {

    public abstract long createOnderdeel(OnderdeelDto onderdeel);
    public abstract void updateOnderdeel(long id, OnderdeelDto onderdeel);
    public abstract void updateDeelVanOnderdeel(long id, OnderdeelDto fields);
    public abstract void deleteOnderdeel(long id);
    public abstract Collection<Onderdeel> getOnderdelen();
    public abstract Optional<Onderdeel> getOnderdeelById(long id);
    public abstract boolean OnderdeelExistsById(long id);
}
