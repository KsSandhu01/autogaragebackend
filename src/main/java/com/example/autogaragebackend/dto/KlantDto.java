package com.example.autogaragebackend.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Data
@Builder
public class KlantDto {


    private String klantnummer;


    private String naam;


    private String email;


    private String telNummer;
}
