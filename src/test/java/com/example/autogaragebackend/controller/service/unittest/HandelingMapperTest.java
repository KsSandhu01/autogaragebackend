package com.example.autogaragebackend.controller.service.unittest;

import com.example.autogaragebackend.dto.HandelingDto;
import com.example.autogaragebackend.mapper.HandelingMapper;
import com.example.autogaragebackend.model.Handeling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandelingMapperTest {
    HandelingMapper handelingMapper;

    @BeforeEach
    void setUp() {
        handelingMapper = new HandelingMapper();
    }

    @Test
    void map_should_map_to_handeling_entity_from_dto() {
        //Given
        HandelingDto dto = HandelingDto.builder().naam("name").prijs("12").build();

        //When
        Handeling handeling = handelingMapper.map(dto);

        //Then
        assertEquals(handeling.getNaam(), dto.getNaam());
        assertEquals(handeling.getPrijs(), dto.getPrijs());
    }

    @Test
    void update_should_set_new_value_only_if_not_null() {
        //Given
        HandelingDto dto = HandelingDto.builder().prijs("12").build();
        Handeling handeling = Handeling.builder().naam("naam").prijs("10").build();

        //When
        handelingMapper.update(dto, handeling);

        //Then
        assertEquals(handeling.getNaam(), handeling.getNaam()); //no change
        assertEquals(handeling.getPrijs(), dto.getPrijs());
    }
}