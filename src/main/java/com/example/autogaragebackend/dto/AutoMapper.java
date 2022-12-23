package com.example.autogaragebackend.dto;

import com.example.autogaragebackend.model.Auto;
import org.springframework.stereotype.Component;

@Component
public class AutoMapper {
    public Auto map(AutoDto dto){
        return Auto.builder()
                .kenteken(dto.getKenteken())
                .kleur(dto.getKleur())
                .merk(dto.getMerk())
                .bouwJaar(dto.getBouwJaar())
                .model(dto.getModel())
                .kmStand(dto.getKmStand())
                .kenteken(dto.getKenteken())
                .build();

    }

    public void update(Auto auto,AutoDto dot){
        auto.setKenteken(dot.getKenteken()!=null? dot.getKenteken(): auto.getKenteken());
        auto.setKleur(dot.getKleur()!=null?dot.getKleur():auto.getKleur());
        auto.setMerk(dot.getMerk()!=null?dot.getMerk():auto.getMerk());
        auto.setBouwJaar(dot.getBouwJaar()!=null? dot.getBouwJaar():auto.getBouwJaar());
        auto.setModel(dot.getModel()!=null? dot.getModel(): auto.getModel());
        auto.setKmStand(dot.getKmStand()!=null? dot.getKmStand(): auto.getKmStand());
        auto.setKenteken(dot.getKenteken()!=null? dot.getKenteken(): auto.getKenteken());
    }
}
