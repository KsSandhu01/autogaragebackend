package com.example.autogaragebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String kenteken;

    @Column
    private String merk;

    @Column
    private String model;

    @Column
    private String bouwJaar;

    @Column
    private String kleur;

    @Column
    private String kmStand;

    @Column(name = "content_type")
    private String contentType;

    @Column
    private String bestandLocatie;

    @Column(name = "filename")
    private String bestandNaam;

    @Column
    @Lob
    private Blob pdfBestand;

    @OneToOne
    @JoinColumn(name = "klant_id")
    private Klant klant;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKenteken() {
        return kenteken;
    }

    public void setKenteken(String kenteken) {
        this.kenteken = kenteken;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBouwJaar() {
        return bouwJaar;
    }

    public void setBouwJaar(String bouwJaar) {
        this.bouwJaar = bouwJaar;
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
    }

    public String getKmStand() {
        return kmStand;
    }

    public void setKmStand(String kmStand) {
        this.kmStand = kmStand;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBestandLocatie() {
        return bestandLocatie;
    }

    public void setBestandLocatie(String bestandLocatie) {
        this.bestandLocatie = bestandLocatie;
    }

    public String getBestandNaam() {
        return bestandNaam;
    }

    public void setBestandNaam(String bestandNaam) {
        this.bestandNaam = bestandNaam;
    }

    public Blob getPdfBestand() {
        return pdfBestand;
    }

    public void setPdfBestand(Blob pdfBestand) {
        this.pdfBestand = pdfBestand;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }
}
