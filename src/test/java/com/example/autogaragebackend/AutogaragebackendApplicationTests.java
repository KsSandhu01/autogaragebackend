package com.example.autogaragebackend;

import com.example.autogaragebackend.enums.Role;
import com.example.autogaragebackend.model.Medewerker;
import com.example.autogaragebackend.repository.MedewerkerRepository;
import com.example.autogaragebackend.service.MedewerkerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@AutoConfigureMockMvc
class AutogaragebackendApplicationTests {

    @Autowired
    private MedewerkerRepository medewerkerRepository;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() throws Exception {
        mockMvc.perform(get("/v1/medewerkers/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    void testcreateMedewerker() throws Exception {
        mockMvc.perform(post("/v1/medewerkers")
                        .contentType(MediaType.APPLICATION_JSON)
                .param("naam","name")
                .param("wachtwoord","wachtwoord")
                .param("gebruikersnaam","gebruikersnaam")
                .param("role", Role.ROLE_ADMINISTRATIEFMEDEWERKER.toString()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        List<Medewerker> medewerkerList= medewerkerRepository.findAll();
        assertEquals(4,medewerkerList.size());
    }

}
