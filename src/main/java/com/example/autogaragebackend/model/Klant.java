package com.example.autogaragebackend.model;


import javax.persistence.*;

@Entity
public class Klant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String klantnummer;

    @Column
    private String naam;

    @Column
    private String email;

    @Column
    private String telNummer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKlantnummer() {
        return klantnummer;
    }

    public void setKlantnummer(String klantnummer) {
        this.klantnummer = klantnummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNummer() {
        return telNummer;
    }

    public void setTelNummer(String telNummer) {
        this.telNummer = telNummer;
    }
}
