package com.example.autogaragebackend.service.impl;

import com.example.autogaragebackend.exception.ResourceNotFoundException;
import com.example.autogaragebackend.model.Klant;
import com.example.autogaragebackend.repository.KlantRepository;
import com.example.autogaragebackend.service.KlantService;
import com.example.autogaragebackend.service.KlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class KlantServiceImpl implements KlantService {

    @Autowired
    private KlantRepository klantRepository;

    @Override
    public long createKlant(Klant klant) {
        Klant klant1 = klantRepository.save(klant);
        return klant1.getId();
    }

    @Override
    public void updateKlant(long id, Klant klant) {
        if (!klantRepository.existsById(id)) throw new ResourceNotFoundException();
        Klant bestaandeKlant = klantRepository.findById(id).get();
        bestaandeKlant.setKlantnummer(klant.getKlantnummer());
        bestaandeKlant.setNaam(klant.getNaam());
        bestaandeKlant.setTelNummer(klant.getTelNummer());

        klantRepository.save(bestaandeKlant);
    }

    @Override
    public void deelUpdateKlant(long id, Map<String, String> velden) {
        if (!klantRepository.existsById(id)) throw new ResourceNotFoundException();
        Klant klant = klantRepository.findById(id).get();
        for (String field : velden.keySet()) {
            switch (field.toLowerCase()) {
                case "customernumber":
                    klant.setKlantnummer((String) velden.get(field));
                    break;
                case "name":
                    klant.setNaam((String) velden.get(field));
                    break;
                case "telephone":
                    klant.setTelNummer((String) velden.get(field));
                    break;

            }
        }
        klantRepository.save(klant);
    }

    @Override
    public void deleteKlant(long id) {
        if (!klantRepository.existsById(id)) throw new ResourceNotFoundException();
        klantRepository.deleteById(id);
    }

    @Override
    public Collection<Klant> getKlanten() {
        return klantRepository.findAll();
    }

    @Override
    public Optional<Klant> getKlantById(long id) {
        return klantRepository.findById(id);
    }

    @Override
    public boolean klantExistsById(long id) {
        return klantRepository.existsById(id);
    }
}
