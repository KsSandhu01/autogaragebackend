package com.example.autogaragebackend.service.impl;

import com.example.autogaragebackend.exception.ResourceNotFoundException;
import com.example.autogaragebackend.model.Handeling;
import com.example.autogaragebackend.repository.HandelingRepository;
import com.example.autogaragebackend.service.HandelingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class HandelingServiceImpl implements HandelingService {

    @Autowired
    private HandelingRepository handelingRepository;

    @Override
    public long createHandeling(Handeling handeling) {
        Handeling handeling1 = handelingRepository.save(handeling);
        return handeling1.getId();
    }

    @Override
    public void updateHandeling(long id, Handeling handeling) {
        if (!handelingRepository.existsById(id)) throw new ResourceNotFoundException();
        Handeling bestaandeHandeling = handelingRepository.findById(id).get();
        bestaandeHandeling.setNaam(handeling.getNaam());
        bestaandeHandeling.setPrijs(handeling.getPrijs());

        handelingRepository.save(bestaandeHandeling);
    }

    @Override
    public void partialUpdateHandeling(long id, Map<String, String> velden) {
        if (!handelingRepository.existsById(id)) throw new ResourceNotFoundException();
        Handeling handeling = handelingRepository.findById(id).get();
        for (String veld : velden.keySet()) {
            switch (veld.toLowerCase()) {
                case "naam":
                    handeling.setNaam((String) velden.get(veld));
                    break;
                case "price":
                    handeling.setPrijs((String) velden.get(veld));
                    break;


            }
        }
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
