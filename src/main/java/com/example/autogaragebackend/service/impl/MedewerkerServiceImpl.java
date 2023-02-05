package com.example.autogaragebackend.service.impl;

import com.example.autogaragebackend.dto.MedewerkerDto;
import com.example.autogaragebackend.exception.ResourceNotFoundException;
import com.example.autogaragebackend.mapper.MedewerkerMapper;
import com.example.autogaragebackend.model.Medewerker;
import com.example.autogaragebackend.repository.MedewerkerRepository;
import com.example.autogaragebackend.service.MedewerkerService;

import com.example.autogaragebackend.util.SpringUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class MedewerkerServiceImpl implements MedewerkerService {

    @Autowired
    private MedewerkerRepository medewerkerRepository;
    @Autowired
    private MedewerkerMapper medewerkerMapper;
    @Override
    public long createMedewerker(MedewerkerDto medewerker) {
        medewerker.setWachtwoord(getEncryptedWachtwoord(medewerker.getWachtwoord()));
        Medewerker medewerker1=  medewerkerMapper.map(medewerker);
        Medewerker nieuweMedewerker = medewerkerRepository.save(medewerker1);
        return nieuweMedewerker.getId();
    }


    @Override
    public void updateMedewerker(long id, MedewerkerDto medewerker) {
        if (!medewerkerRepository.existsById(id))
            throw new ResourceNotFoundException();
        Medewerker bestaandeMedewerker = medewerkerRepository.findById(id).get();
        bestaandeMedewerker.setNaam(medewerker.getNaam());
        bestaandeMedewerker.setGebruikersnaam(medewerker.getGebruikersnaam());
        bestaandeMedewerker.setWachtwoord(medewerker.getWachtwoord());
        medewerker.setWachtwoord(getEncryptedWachtwoord(medewerker.getWachtwoord()));
        bestaandeMedewerker.setRole(medewerker.getRole());
        medewerkerRepository.save(bestaandeMedewerker);
    }

    @Override
    public void updateDeelVanMedewerker(MedewerkerDto velden) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Medewerker medewerker1 =medewerkerRepository.findByGebruikersnaam(email).get();
        medewerkerMapper.update(medewerker1,velden);
        medewerkerRepository.save(medewerker1);
    }

    @Override
    public void deleteMedewerker(long id) {
        if (!medewerkerRepository.existsById(id)) throw new ResourceNotFoundException();
        medewerkerRepository.deleteById(id);
    }

    @Override
    public Collection<Medewerker> getMedewerkers() {
        return medewerkerRepository.findAll();
    }

    @Override
    public Collection<Medewerker> getMedewerkerMetNaam(String naam) {
        return medewerkerRepository.findAllByNaam(naam);
    }

    @Override
    public Collection<Medewerker> getMedewerkerMetRole(String role) {
        return medewerkerRepository.findAllByRole(role);
    }

    @Override
    public Optional<Medewerker> getMedewerkerMetId(long id) {
        if (!medewerkerRepository.existsById(id)) throw new ResourceNotFoundException();
        return medewerkerRepository.findById(id);
    }

    @Override
    public boolean bestaatMedewerkerMetId(long id) {
        return medewerkerRepository.existsById(id);
    }


    private String getEncryptedWachtwoord(String wachtwoord) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(wachtwoord);
    }

}
