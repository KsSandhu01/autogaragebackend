package com.example.autogaragebackend.controller.service.unittest;

import com.example.autogaragebackend.dto.HandelingDto;
import com.example.autogaragebackend.mapper.HandelingMapper;
import com.example.autogaragebackend.model.Handeling;
import com.example.autogaragebackend.repository.HandelingRepository;
import com.example.autogaragebackend.service.impl.HandelingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HandelingServiceTest {
    private Handeling handeling;

    @Mock
    private HandelingRepository handelingRepository;

    @Mock
    private HandelingMapper handelingMapper;

    @InjectMocks
    private HandelingServiceImpl handelingService;

    @BeforeEach
    void setUp() {
        handeling = buildHandeling("username", "10");

        when(handelingRepository.existsById(1L)).thenReturn(true);
    }

    /**
     * deze test methode test de update functie
     * van de naam en prijs van de handeling
     */
    @Test
    void updateHandeling() {
        //Given
        HandelingDto dto = HandelingDto.builder().naam("name").prijs("12").build();

        //When
        when(handelingRepository.findById(1L)).thenReturn(Optional.of(handeling));
        when(handelingRepository.save(any())).thenReturn(handeling);
        handelingService.updateHandeling(1, dto);

        //Then
        assertEquals("name", handeling.getNaam());
        assertEquals("12", handeling.getPrijs());
    }

    /**
     * deze test methode test de deel update functie handeling
     */
    @Test
    void updateDeelVanHandeling() {
        //Given
        HandelingDto dto = HandelingDto.builder().naam("new_name").prijs("5").build();

        //When
        when(handelingRepository.findById(1L)).thenReturn(Optional.of(handeling));
        when(handelingRepository.save(any())).thenReturn(handeling);
        doCallRealMethod().when(handelingMapper).update(any(), any());
        handelingService.updateDeelVanHandeling(1, dto);

        //Then
        assertEquals("5", handeling.getPrijs());
        assertEquals("new_name", handeling.getNaam());
    }

    private Handeling buildHandeling(String naam, String prijs) {
        return Handeling.builder()
                .id(2)
                .naam(naam)
                .prijs(prijs)
                .build();
    }

}
