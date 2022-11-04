package com.example.autogaragebackend.model;


import javax.persistence.*;

@Entity
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
