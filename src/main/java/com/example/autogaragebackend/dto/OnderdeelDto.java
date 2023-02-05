package com.example.autogaragebackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OnderdeelDto {


    private String naam;


    private String prijs;


    private String voorraad;
}
