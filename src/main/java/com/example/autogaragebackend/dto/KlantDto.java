package com.example.autogaragebackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class KlantDto {


    private String klantnummer;


    private String naam;


    private String email;


    private String telNummer;
}
