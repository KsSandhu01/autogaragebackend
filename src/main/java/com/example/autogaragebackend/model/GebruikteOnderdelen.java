package com.example.autogaragebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class GebruikteOnderdelen {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "afspraak_id")
    private Afspraak afspraak;


    @ManyToOne
    @JoinColumn(name = "part_id")
    private Onderdeel onderdeel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Afspraak getAfspraak() {
        return afspraak;
    }

    public void setAfspraak(Afspraak afspraak) {
        this.afspraak = afspraak;
    }

    public Onderdeel getOnderdeel() {
        return onderdeel;
    }

    public void setOnderdeel(Onderdeel onderdeel) {
        this.onderdeel = onderdeel;
    }
}
