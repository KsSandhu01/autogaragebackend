package com.example.autogaragebackend;

import com.example.autogaragebackend.enums.AfspraakStatus;
import com.example.autogaragebackend.model.Afspraak;
import com.example.autogaragebackend.model.Klant;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AfspraakServiceImplTest {

    @Autowired
    private AfspraakRepository afspraakRepository;
    @Autowired
    private AfspraakService afspraakService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void before(){
//        jdbcTemplate.execute("insert into `klant` (id,email, klantnummer, naam, tel_nummer)\n" +
//                "values (1,'kawal@mail.nl', '123456', 'Dirk', '0612345678')");
//        jdbcTemplate.execute("insert into `auto` (id,merk, model, kenteken, km_stand, bouw_jaar, kleur, klant_id)\n" +
//                "values (12,'Audi', 'RS7', '74-056-KP', '152633', '2016', 'Rood', '1')");
    }
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
       // assertTrue(afspraak.getStatus().equals(AfspraakStatus.NIETUITVOEREN.toString()));
    }
}
