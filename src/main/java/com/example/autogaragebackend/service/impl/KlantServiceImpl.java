package com.example.autogaragebackend.service.impl;

import com.example.autogaragebackend.dto.KlantDto;
import com.example.autogaragebackend.exception.ResourceNotFoundException;
import com.example.autogaragebackend.mapper.KlantMapper;
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
    @Autowired
    private KlantMapper klantMapper;
    @Override
    public long createKlant(KlantDto dto) {
        Klant klant1 = klantMapper.map(dto)         ;
        klantRepository.save(klant1);
        return klant1.getId();
    }

    @Override
    public void updateKlant(long id, KlantDto dto) {
        if (!klantRepository.existsById(id)) throw new ResourceNotFoundException();
        Klant klant = klantRepository.findById(id).get();
        klantMapper.update(klant,dto);
//        klant.setKlantnummer(klant.getKlantnummer());
//        klant.setNaam(klant.getNaam());
//        klant.setTelNummer(klant.getTelNummer());

        klantRepository.save(klant);
    }

    @Override
    public void deelUpdateKlant(long id, KlantDto dto) {
        if (!klantRepository.existsById(id)) throw new ResourceNotFoundException();
        Klant klant = klantRepository.findById(id).get();
        klantMapper.update(klant,dto);
//        for (String field : velden.keySet()) {
//            switch (field.toLowerCase()) {
//                case "klantnummer":
//                    klant.setKlantnummer((String) velden.get(field));
//                    break;
//                case "naam":
//                    klant.setNaam((String) velden.get(field));
//                    break;
//                case "telNummer":
//                    klant.setTelNummer((String) velden.get(field));
//                    break;
//                case "email":
//                    klant.setEmail((String) velden.get(field));
//                    break;
//
//            }
//        }
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
