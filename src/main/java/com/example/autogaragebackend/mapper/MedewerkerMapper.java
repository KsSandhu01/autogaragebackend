package com.example.autogaragebackend.mapper;

import com.example.autogaragebackend.dto.MedewerkerDto;
import com.example.autogaragebackend.model.Medewerker;
import org.springframework.stereotype.Component;

@Component
public class MedewerkerMapper {

    public Medewerker map(MedewerkerDto medewerkerDto){
        return  Medewerker.builder()
                .gebruikersnaam(medewerkerDto.getGebruikersnaam())
                .role(medewerkerDto.getRole())
                .wachtwoord(medewerkerDto.getWachtwoord())
                .naam(medewerkerDto.getNaam())
                .build();
    }

    public void update(Medewerker entity, MedewerkerDto dto){
        entity.setGebruikersnaam(dto.getGebruikersnaam()!=null? dto.getGebruikersnaam():entity.getGebruikersnaam());
        entity.setNaam(dto.getNaam()!=null?dto.getNaam():entity.getNaam());
        entity.setRole(dto.getRole()!=null?dto.getRole():entity.getRole());
        entity.setWachtwoord(dto.getWachtwoord()!=null?dto.getWachtwoord():entity.getWachtwoord());
    }
}
