package com.example.autogaragebackend.model;

import javax.persistence.*;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String vehicle_number;

    @Column
    private String make;

    @Column
    private String model;

    @Column
    private String year;

    @Column
    private String color;

    @Column
    private String kilometers;



}
