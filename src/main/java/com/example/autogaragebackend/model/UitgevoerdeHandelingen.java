package com.example.autogaragebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UitgevoerdeHandelingen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "afspraak_id")
    private Afspraak afspraak;

    @ManyToOne
    @JoinColumn(name = "handeling_id")
    private Handeling handeling;

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

    public Handeling getHandeling() {
        return handeling;
    }

    public void setHandeling(Handeling handeling) {
        this.handeling = handeling;
    }
}
