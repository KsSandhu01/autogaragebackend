package com.example.autogaragebackend.service.impl;

import com.example.autogaragebackend.dto.OnderdeelDto;
import com.example.autogaragebackend.exception.ResourceNotFoundException;
import com.example.autogaragebackend.mapper.OnderdeelMapper;
import com.example.autogaragebackend.model.Onderdeel;
import com.example.autogaragebackend.repository.OnderdeelRepository;
import com.example.autogaragebackend.service.OnderdeelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OnderdeelServiceImpl implements OnderdeelService {

    @Autowired
    private OnderdeelRepository onderdeelRepository;

    @Autowired
    private OnderdeelMapper onderdeelMapper;

    @Override
    public long createOnderdeel(OnderdeelDto onderdeel) {
        Onderdeel entity = onderdeelMapper.map(onderdeel);
        Onderdeel onderdeel1 = onderdeelRepository.save(entity);
        return onderdeel1.getId();
    }

    @Override
    public void updateOnderdeel(long id, OnderdeelDto onderdeel) {
        if (!onderdeelRepository.existsById(id)) throw new ResourceNotFoundException();
        Onderdeel bestaandeOnderdeel = onderdeelRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Onderdeel niet gevonden met deze id : " + id));
        bestaandeOnderdeel.setNaam(onderdeel.getNaam());
        bestaandeOnderdeel.setPrijs(onderdeel.getPrijs());
        bestaandeOnderdeel.setVoorraad(onderdeel.getVoorraad());

        onderdeelRepository.save(bestaandeOnderdeel);
    }

    @Override
    public void updateDeelVanOnderdeel(long id, OnderdeelDto velden) {
        if (!onderdeelRepository.existsById(id)) throw new ResourceNotFoundException();
        Onderdeel onderdeel = onderdeelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Onderdeel niet gevonden met deze id : " + id));
        onderdeelMapper.update(onderdeel, velden);

        onderdeelRepository.save(onderdeel);
    }

    @Override
    public void deleteOnderdeel(long id) {
        if (!onderdeelRepository.existsById(id)) throw new ResourceNotFoundException();
        onderdeelRepository.deleteById(id);
    }

    @Override
    public Collection<Onderdeel> getOnderdelen() {
        return onderdeelRepository.findAll();
    }

    @Override
    public Optional<Onderdeel> getOnderdeelById(long id) {
        return onderdeelRepository.findById(id);
    }

    @Override
    public boolean OnderdeelExistsById(long id) {
        return onderdeelRepository.existsById(id);
    }
}
