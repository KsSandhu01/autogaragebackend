package com.example.autogaragebackend.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Data
@Builder
public class AutoDto {

    private String kenteken;


    private String merk;


    private String model;


    private String bouwJaar;


    private String kleur;


    private String kmStand;


    private String contentType;


    private String bestandLocatie;


    private String bestandNaam;
}
