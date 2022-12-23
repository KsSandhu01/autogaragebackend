package com.example.autogaragebackend.mapper;

import com.example.autogaragebackend.dto.KlantDto;
import com.example.autogaragebackend.model.Klant;
import org.springframework.stereotype.Component;

@Component
public class KlantMapper {

    public Klant map(KlantDto dto){
        return Klant.builder()
                .email(dto.getEmail())
                .naam(dto.getNaam())
                .klantnummer(dto.getKlantnummer())
                .telNummer(dto.getTelNummer())
                .build();
    }

    public void update(Klant klant, KlantDto dto) {
        klant.setEmail(dto.getEmail()!=null?dto.getEmail():klant.getEmail());
        klant.setNaam(dto.getNaam()!=null?dto.getNaam():klant.getNaam());
        klant.setTelNummer(dto.getTelNummer()!=null?dto.getTelNummer():klant.getTelNummer());
        klant.setKlantnummer(dto.getKlantnummer()!=null?dto.getKlantnummer():klant.getKlantnummer());
    }
}
