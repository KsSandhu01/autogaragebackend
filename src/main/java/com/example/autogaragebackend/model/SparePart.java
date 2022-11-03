package com.example.autogaragebackend.model;

import javax.persistence.*;

@Entity
@Table(name = "spareparts")
public class SparePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String price;

    @Column
    private String stock;


}
