package com.example.autogaragebackend.mapper;

import com.example.autogaragebackend.dto.OnderdeelDto;
import com.example.autogaragebackend.model.Onderdeel;
import org.springframework.stereotype.Component;

@Component
public class OnderdeelMapper {

    public Onderdeel map(OnderdeelDto dto){
        return Onderdeel.builder()
                .prijs(dto.getPrijs())
                .naam(dto.getNaam())
                .voorraad(dto.getVoorraad())
                .build();
    }

    public void update(Onderdeel entity,OnderdeelDto dto){
        entity.setVoorraad(dto.getVoorraad()!=null?dto.getVoorraad():entity.getVoorraad());
        entity.setNaam(dto.getNaam()!=null?dto.getNaam():entity.getNaam());
        entity.setPrijs(dto.getPrijs()!=null?dto.getPrijs():entity.getPrijs());
    }
}
