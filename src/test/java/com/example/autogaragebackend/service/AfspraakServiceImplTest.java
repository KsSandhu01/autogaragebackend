package com.example.autogaragebackend.service;

import com.example.autogaragebackend.dto.HandelingDto;
import com.example.autogaragebackend.enums.AfspraakStatus;
import com.example.autogaragebackend.model.*;
import com.example.autogaragebackend.repository.AfspraakRepository;
import com.example.autogaragebackend.service.AfspraakService;
import com.example.autogaragebackend.service.KlantService;
import com.example.autogaragebackend.service.impl.KlantServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AfspraakServiceImplTest {

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
        Afspraak afspraak = afspraakRepository.findById(1l).get();
        assertNotNull(afspraak);
        assertTrue(afspraak.getStatus().equals(AfspraakStatus.GEPLAND.toString()));

        afspraakService.updateStatus(1,AfspraakStatus.NIETUITVOEREN);
        afspraak = afspraakRepository.findById(1l).get();
        assertTrue(afspraak.getStatus().equals(AfspraakStatus.NIETUITVOEREN.toString()));
    }

    @Test
    void getAfspraakTest(){
        Afspraak afspraken = afspraakService.getAfspraak(1);
        assertNotNull(afspraken);
        assertTrue(afspraken.getStatus().equals(AfspraakStatus.GEPLAND.toString()));

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

//    @Test
//    void genereerBonTest(){
//        Bon bon = afspraakService.genereerBon(1);
//        assertEquals("45",bon.getTotaal());
//        GebruikteOnderdelen gebruikteOnderdelen = afspraakService.voegOnderdeelAanAfspraak(1, 1);
//        UitgevoerdeHandelingen uitgevoerdeHandelingen = afspraakService.voegHandelingAanAfspraak(1, 1);
//        afspraakService.voegOnderdeelAanAfspraak(1,1);
//        afspraakService.voegHandelingAanAfspraak(1,1);
//        afspraakService.genereerBon(1);
//
//        assertEquals("45",bon.getTotaal());
//
//        assertNotNull(bon);
//    }
}
