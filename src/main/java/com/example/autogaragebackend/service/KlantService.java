package com.example.autogaragebackend.service;

import com.example.autogaragebackend.dto.KlantDto;
import com.example.autogaragebackend.model.Klant;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface KlantService {
    long createKlant(KlantDto klant);

    void updateKlant(long id, KlantDto klant);

    void deelUpdateKlant(long id, KlantDto fields);

    void deleteKlant(long id);

    Collection<Klant> getKlanten();

    Optional<Klant> getKlantById(long id);

    boolean klantExistsById(long id);
}
