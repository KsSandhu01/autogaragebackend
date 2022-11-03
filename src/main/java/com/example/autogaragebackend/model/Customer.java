package com.example.autogaragebackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String customerNumber;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String telephone_number;
}
