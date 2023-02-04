package com.example.autogaragebackend.controller.service.unittest;

import com.example.autogaragebackend.enums.AfspraakStatus;
import com.example.autogaragebackend.model.Afspraak;
import com.example.autogaragebackend.model.GebruikteOnderdelen;
import com.example.autogaragebackend.model.Onderdeel;
import com.example.autogaragebackend.repository.AfspraakRepository;
import com.example.autogaragebackend.repository.GebruikteOnderdeelRepository;
import com.example.autogaragebackend.repository.UitgevoerdeHandelingRepository;
import com.example.autogaragebackend.service.HandelingService;
import com.example.autogaragebackend.service.OnderdeelService;
import com.example.autogaragebackend.service.impl.AfspraakServiceImpl;
import com.example.autogaragebackend.service.impl.AutoServiceImpl;
import com.example.autogaragebackend.service.impl.KlantServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AfspraakServiceImplTest {
    @Mock
    private AfspraakRepository afspraakRepository;
    @Mock
    UitgevoerdeHandelingRepository uitgevoerdeHandelingRepository;
    @Mock
    GebruikteOnderdeelRepository gebruikteOnderdeelRepository;
    @Mock
    KlantServiceImpl klantService;
    @Mock
    AutoServiceImpl autoservice;
    @Mock
    OnderdeelService onderdeelService;
    @Mock
    HandelingService handelingService;

    @InjectMocks
    private AfspraakServiceImpl afspraakService;


    @Test
    void updateStatusTest() {
        //Given
        Afspraak afspraak = Afspraak.builder().status(AfspraakStatus.GEPLAND.name()).build();
        AfspraakStatus newStatus = AfspraakStatus.NIETUITVOEREN;
        when(afspraakRepository.findById(any())).thenReturn(Optional.of(afspraak));

        //When
        afspraakService.updateStatus(1, newStatus);

        //Then
        assertEquals(newStatus.name(), afspraak.getStatus());
    }

    @Test
    void voegOnderdeelAanAfspraakTest() {
        //Given
        Afspraak afspraak = Afspraak.builder().status(AfspraakStatus.GEPLAND.name()).build();
        Onderdeel onderdeel = Onderdeel.builder().naam("Uitlaat").prijs("10").build();
        when(afspraakRepository.findById(1L)).thenReturn(Optional.of(afspraak));
        when(onderdeelService.getOnderdeelById(1)).thenReturn(Optional.of(onderdeel));
        when(gebruikteOnderdeelRepository.save(any())).thenReturn(null);

        //When
        GebruikteOnderdelen gebruikteOnderdelen = afspraakService.voegOnderdeelAanAfspraak(1L, 1L);

        //Then
        assertNotNull(afspraak.getGebruikteOnderdelen());

        gebruikteOnderdelen = afspraak.getGebruikteOnderdelen().stream().findFirst().get();

        assertEquals("Uitlaat", gebruikteOnderdelen.getOnderdeel().getNaam());
        assertEquals("10", gebruikteOnderdelen.getOnderdeel().getPrijs());
    }
}
