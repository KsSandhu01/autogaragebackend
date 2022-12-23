package com.example.autogaragebackend.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Data
@Builder
public class OnderdeelDto {


    private String naam;


    private String prijs;


    private String voorraad;
}
