package com.example.autogaragebackend.controller.service.integration;

import com.example.autogaragebackend.dto.HandelingDto;
import com.example.autogaragebackend.enums.AfspraakStatus;
import com.example.autogaragebackend.model.Afspraak;
import com.example.autogaragebackend.model.GebruikteOnderdelen;
import com.example.autogaragebackend.model.UitgevoerdeHandelingen;
import com.example.autogaragebackend.repository.AfspraakRepository;
import com.example.autogaragebackend.service.AfspraakService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AfspraakServiceImplITest {
    @Autowired
    private AfspraakRepository afspraakRepository;
    @Autowired
    private AfspraakService afspraakService;


    @Test
    void createAfspraakTest(){
        Afspraak afspraak = afspraakService.createAfspraak(1, 1);
        assertNotNull(afspraak);
        assertEquals(1,afspraakService.fetchByKlant(1).size());
    }

    @Test
    void updateStatusTest(){
        Afspraak afspraak = afspraakRepository.findById(1L).get();
        assertNotNull(afspraak);
        assertEquals(afspraak.getStatus(), AfspraakStatus.GEPLAND.toString());

        afspraakService.updateStatus(1,AfspraakStatus.NIETUITVOEREN);
        afspraak = afspraakRepository.findById(1L).get();
        assertEquals(afspraak.getStatus(), AfspraakStatus.NIETUITVOEREN.toString());
    }

    @Test
    void getAfspraakTest(){
        Afspraak afspraken = afspraakService.getAfspraak(1);
        assertNotNull(afspraken);
        assertEquals(afspraken.getStatus(), AfspraakStatus.GEPLAND.toString());

    }
    @Test
    void getAllAfsprakenTest(){
        List<Afspraak> allAfspraken = afspraakService.getAllAfspraken();
        assertEquals(1,allAfspraken.size());
    }

    @Test
    void voegOnderdeelAanAfspraakTest(){
        GebruikteOnderdelen gebruikteOnderdelen = afspraakService.voegOnderdeelAanAfspraak(1, 1);
        assertEquals(gebruikteOnderdelen.getOnderdeel().getNaam(),"Uitlaat");
        assertNotNull(gebruikteOnderdelen);
    }
    @Test
    void voegHandelingAanAfspraakTest(){
        UitgevoerdeHandelingen uitgevoerdeHandelingen = afspraakService.voegHandelingAanAfspraak(1, 1);
        assertNotNull(uitgevoerdeHandelingen);
    }

    @Test
    void voegOverigeHandelingAanAfspraakTest(){
        HandelingDto dto = HandelingDto.builder()
                .naam("Waterpomp reparatie")
                .prijs("€ 350")
                .build();
        UitgevoerdeHandelingen uitgevoerdeHandelingen = afspraakService.voegOverigeHandelingAanAfspraak(1, dto);

        assertNotNull(uitgevoerdeHandelingen);
    }

    @Test
    void fetchByStatusTest(){
        List<Afspraak> list = afspraakService.fetchByStatus("GEPLAND");
        assertEquals(1,list.size());
    }

    @Test
    void fetchByKlantTest(){
        List<Afspraak> afspraken = afspraakService.fetchByKlant(1);
        assertEquals(afspraken.get(0).getKlant().getNaam(),"Dirk");
        assertEquals(1,afspraken.size());
    }

}
