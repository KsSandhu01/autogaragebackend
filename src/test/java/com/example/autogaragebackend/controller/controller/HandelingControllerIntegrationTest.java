package com.example.autogaragebackend.controller.controller;

import com.example.autogaragebackend.enums.Role;
import com.example.autogaragebackend.model.Medewerker;
import com.example.autogaragebackend.security.CustomUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class HandelingControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        CustomUserDetails userDetails = new CustomUserDetails(Medewerker.builder().naam("naam").role(Role.ROLE_ADMINISTRATIEFMEDEWERKER).id(1).build());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
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
