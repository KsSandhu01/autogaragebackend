package com.example.autogaragebackend;

import com.example.autogaragebackend.dto.HandelingDto;
import com.example.autogaragebackend.model.Handeling;
import com.example.autogaragebackend.repository.HandelingRepository;
import com.example.autogaragebackend.service.HandelingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HandelingServiceTest {

    @Autowired
    private HandelingService handelingService;

    @Autowired
    private HandelingRepository handelingRepository;
    @Test
    void createHandelingTest(){
        HandelingDto dto = HandelingDto.builder().naam("name").prijs("12").build();

        long handeling = handelingService.createHandeling(dto);

        assertEquals(4,handeling);

    }

    @Test
    void updateHandelingTest(){
        Handeling handeling = handelingRepository.findById(1l).get();
        assertNotNull(handeling);
        HandelingDto dto = HandelingDto.builder().naam("name").prijs("12").build();
        handelingService.updateHandeling(1,dto);
        handeling = handelingRepository.findById(1l).get();
        assertTrue(handeling.getNaam().equals("name"));
    }

    @Test
    void updateDeelVanHandelingTest(){
        HandelingDto dto = HandelingDto.builder().naam("name").prijs("12").build();
        handelingService.updateDeelVanHandeling(1,dto);
        Handeling handeling = handelingRepository.findById(1l).get();
        assertTrue(handeling.getPrijs().equals("12"));
    }

    @Test
    void deleteHandelingTest(){
        handelingService.deleteHandeling(1);
        List<Handeling> list = handelingRepository.findAll();
        assertEquals(2,list.size());
    }

    @Test
    void getHandelingenTest(){
        Collection<Handeling> list = handelingService.getHandelingen();
        assertEquals(3,list.size());
    }

    @Test
    void getHandelingByIdTest(){
        Handeling handeling = handelingService.getHandelingById(1).get();
        assertEquals("Uitlaat vervangen",handeling.getNaam());
    }

    @Test
    void handelingExistsByIdTest(){
        boolean test = handelingService.handelingExistsById(1);
        assertTrue(test);
        boolean test1 = handelingService.handelingExistsById(5);
        assertFalse(test1);
    }

}
