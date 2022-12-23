package com.example.autogaragebackend.service;

import com.example.autogaragebackend.dto.KlantDto;
import com.example.autogaragebackend.model.Klant;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public interface KlantService {
    public abstract long createKlant(KlantDto klant);

    public abstract void updateKlant(long id, KlantDto klant);

    public abstract void deelUpdateKlant(long id, KlantDto fields);

    public abstract void deleteKlant(long id);

    public abstract Collection<Klant> getKlanten();

    public abstract Optional<Klant> getKlantById(long id);

    public abstract boolean klantExistsById(long id);
}
