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
        klantRepository.save(klant);
    }

    @Override
    public void deelUpdateKlant(long id, KlantDto dto) {
        if (!klantRepository.existsById(id)) throw new ResourceNotFoundException();
        Klant klant = klantRepository.findById(id).get();
        klantMapper.update(klant,dto);

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
