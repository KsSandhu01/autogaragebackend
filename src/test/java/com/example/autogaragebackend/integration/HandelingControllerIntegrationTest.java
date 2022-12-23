package com.example.autogaragebackend.integration;

import com.example.autogaragebackend.enums.Role;
import com.example.autogaragebackend.model.Medewerker;
import com.example.autogaragebackend.repository.MedewerkerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
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
@AutoConfigureMockMvc(addFilters = false)
class HandelingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getHandelingenTest() throws Exception {
        mockMvc.perform(get("/v1/handelingen"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    void getHandelingTest() throws Exception {
        mockMvc.perform(get("/v1/handelingen/{id}", 1))
                .andExpect(status().isOk())
                .andReturn();
    }


}
