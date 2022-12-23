package com.example.autogaragebackend.service;

import com.example.autogaragebackend.dto.HandelingDto;
import com.example.autogaragebackend.model.Handeling;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface HandelingService {

    public abstract long createHandeling(HandelingDto handeling);
    public abstract void updateHandeling(long id, HandelingDto handeling);
    public abstract void updateDeelVanHandeling(long id, HandelingDto fields);
    public abstract void deleteHandeling(long id);
    public abstract Collection<Handeling> getHandelingen();
    public abstract Optional<Handeling> getHandelingById(long id);
    public abstract boolean handelingExistsById(long id);
}
