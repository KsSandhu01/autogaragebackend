package com.example.autogaragebackend.service;

import com.example.autogaragebackend.model.Klant;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public interface KlantService {
    public abstract long createKlant(Klant klant);

    public abstract void updateKlant(long id, Klant klant);

    public abstract void deelUpdateKlant(long id, Map<String, String> fields);

    public abstract void deleteKlant(long id);

    public abstract Collection<Klant> getKlanten();

    public abstract Optional<Klant> getKlantById(long id);

    public abstract boolean klantExistsById(long id);
}
