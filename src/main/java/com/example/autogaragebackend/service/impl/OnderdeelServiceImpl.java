package com.example.autogaragebackend.service.impl;

import com.example.autogaragebackend.exception.ResourceNotFoundException;
import com.example.autogaragebackend.model.Onderdeel;
import com.example.autogaragebackend.repository.OnderdeelRepository;
import com.example.autogaragebackend.service.OnderdeelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class OnderdeelServiceImpl implements OnderdeelService {

    @Autowired
    private OnderdeelRepository onderdeelRepository;

    @Override
    public long createOnderdeel(Onderdeel onderdeel) {
        Onderdeel onderdeel1 = onderdeelRepository.save(onderdeel);
        return onderdeel1.getId();
    }

    @Override
    public void updateOnderdeel(long id, Onderdeel onderdeel) {
        if (!onderdeelRepository.existsById(id)) throw new ResourceNotFoundException();
        Onderdeel bestaandeOnderdeel = onderdeelRepository.findById(id).get();
        bestaandeOnderdeel.setNaam(onderdeel.getNaam());
        bestaandeOnderdeel.setPrijs(onderdeel.getPrijs());
        bestaandeOnderdeel.setVoorraad(onderdeel.getVoorraad());

        onderdeelRepository.save(bestaandeOnderdeel);
    }

    @Override
    public void updateDeelVanOnderdeel(long id, Map<String, String> fields) {
        if (!onderdeelRepository.existsById(id)) throw new ResourceNotFoundException();
        Onderdeel onderdeel = onderdeelRepository.findById(id).get();
        for (String field : fields.keySet()) {
            switch (field.toLowerCase()) {
                case "name":
                    onderdeel.setNaam((String) fields.get(field));
                    break;
                case "price":
                    onderdeel.setPrijs((String) fields.get(field));
                    break;
                case "stock":
                    onderdeel.setVoorraad((String) fields.get(field));
                    break;

            }
        }
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
