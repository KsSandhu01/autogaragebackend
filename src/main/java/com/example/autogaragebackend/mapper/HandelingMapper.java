package com.example.autogaragebackend.mapper;

import com.example.autogaragebackend.dto.HandelingDto;
import com.example.autogaragebackend.model.Handeling;
import org.springframework.stereotype.Component;

@Component
public class HandelingMapper {

    public Handeling map(HandelingDto handelingDto){
        return Handeling.builder()
                .naam(handelingDto.getNaam())
                .prijs(handelingDto.getPrijs())
                .build();
    }

    public void update(HandelingDto velden, Handeling handeling) {
        handeling.setPrijs(velden.getPrijs()!=null?velden.getPrijs():handeling.getPrijs());
        handeling.setNaam(velden.getNaam()!=null?velden.getNaam():handeling.getNaam());

    }
}
