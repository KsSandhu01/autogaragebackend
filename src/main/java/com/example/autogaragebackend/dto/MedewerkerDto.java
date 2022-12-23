package com.example.autogaragebackend.dto;

import com.example.autogaragebackend.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedewerkerDto {

    private String naam;

    private String gebruikersnaam;

    private String wachtwoord;

    private Role role;
}
