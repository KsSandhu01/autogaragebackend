package com.example.autogaragebackend.service.impl;

import com.example.autogaragebackend.dto.HandelingDto;
import com.example.autogaragebackend.exception.ResourceNotFoundException;
import com.example.autogaragebackend.mapper.HandelingMapper;
import com.example.autogaragebackend.model.Handeling;
import com.example.autogaragebackend.repository.HandelingRepository;
import com.example.autogaragebackend.service.HandelingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class HandelingServiceImpl implements HandelingService {

    @Autowired
    private HandelingRepository handelingRepository;
    @Autowired
    private HandelingMapper handelingMapper;


    @Override
    public long createHandeling(HandelingDto handeling) {

        Handeling handeling1 = handelingMapper.map(handeling);

        return handelingRepository.save(handeling1).getId();
    }

    @Override
    public void updateHandeling(long id, HandelingDto handeling) {
        if (!handelingRepository.existsById(id)) throw new ResourceNotFoundException();
        Handeling bestaandeHandeling = handelingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Handeling niet gevonden met deze id : " + id));
        bestaandeHandeling.setNaam(handeling.getNaam());
        bestaandeHandeling.setPrijs(handeling.getPrijs());

        handelingRepository.save(bestaandeHandeling);
    }

    @Override
    public void updateDeelVanHandeling(long id, HandelingDto velden) {

        if (!handelingRepository.existsById(id)) throw new ResourceNotFoundException();
        Handeling handeling = handelingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Handeling niet gevonden met deze id : " + id));
        handelingMapper.update(velden,handeling);

        handelingRepository.save(handeling);
    }

    @Override
    public void deleteHandeling(long id) {
        if (!handelingRepository.existsById(id)) throw new ResourceNotFoundException();
        handelingRepository.deleteById(id);
    }

    @Override
    public Collection<Handeling> getHandelingen() {
        return handelingRepository.findAll();
    }

    @Override
    public Optional<Handeling> getHandelingById(long id) {
        return handelingRepository.findById(id);
    }

    @Override
    public boolean handelingExistsById(long id) {
        return handelingRepository.existsById(id);
    }
}
