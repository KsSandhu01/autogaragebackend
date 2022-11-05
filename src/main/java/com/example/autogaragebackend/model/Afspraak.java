package com.example.autogaragebackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Afspraak  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp tijd;
    private String status;

    @ManyToOne
    @JoinColumn(name="klant_id")
    private Klant klant;

    @OneToOne
    @JoinColumn(name="auto_id")
    private Auto auto;

    @JsonManagedReference
    @OneToMany(mappedBy = "afspraak", fetch = FetchType.LAZY)
    private Set<UitgevoerdeHandelingen> uitgevoerdeHandelingen;

    @JsonManagedReference
    @OneToMany(mappedBy = "afspraak", fetch = FetchType.LAZY)
    private Set<GebruikteOnderdelen> gebruikteOnderdelen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTijd() {
        return tijd;
    }

    public void setTijd(Timestamp tijd) {
        this.tijd = tijd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Set<UitgevoerdeHandelingen> getUitgevoerdeHandelingen() {
        return uitgevoerdeHandelingen;
    }

    public void setUitgevoerdeHandelingen(Set<UitgevoerdeHandelingen> uitgevoerdeHandelingen) {
        this.uitgevoerdeHandelingen = uitgevoerdeHandelingen;
    }

    public Set<GebruikteOnderdelen> getGebruikteOnderdelen() {
        return gebruikteOnderdelen;
    }

    public void setGebruikteOnderdelen(Set<GebruikteOnderdelen> gebruikteOnderdelen) {
        this.gebruikteOnderdelen = gebruikteOnderdelen;
    }

    public void voegAfspraakHandeling(UitgevoerdeHandelingen uitgevoerdeHandeling) {
        if(this.uitgevoerdeHandelingen==null) {
            this.uitgevoerdeHandelingen = new HashSet<UitgevoerdeHandelingen>();
        }
        this.uitgevoerdeHandelingen.add(uitgevoerdeHandeling);

    }

    public void voegAfspraakOnderdeel(GebruikteOnderdelen gebruikteOnderdeel) {
        if(this.gebruikteOnderdelen==null) {
            this.gebruikteOnderdelen = new HashSet<GebruikteOnderdelen>();
        }
        this.gebruikteOnderdelen.add(gebruikteOnderdeel);

    }
}
