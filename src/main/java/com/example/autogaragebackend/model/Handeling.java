package com.example.autogaragebackend.model;


import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
public class Handeling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String naam;

    @Column
    private String prijs;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getPrijs() {
        return prijs;
    }

    public void setPrijs(String prijs) {
        this.prijs = prijs;
    }


    public String getPrijsString() {
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String s = decimalFormat.format(Double.parseDouble(prijs));
            return "€"+String.valueOf(s).replace(".", ",");

    }
}
