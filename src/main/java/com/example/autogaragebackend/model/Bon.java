package com.example.autogaragebackend.model;

import java.util.ArrayList;
import java.util.List;

public class Bon {

    private long afspraakid;
    private String apkKosten;
    List<String> handelingen = new ArrayList<String>();
    List<String> onderdelen = new ArrayList<String>();
    private Klant klant;
    private String subTotaal;
    private String totaal;

    public long getAfspraakid() {
        return afspraakid;
    }

    public void setAfspraakid(long afspraakid) {
        this.afspraakid = afspraakid;
    }

    public String getApkKosten() {
        return apkKosten;
    }

    public void setApkKosten(String apkKosten) {
        this.apkKosten = apkKosten;
    }

    public List<String> getHandelingen() {
        return handelingen;
    }

    public void setHandelingen(List<String> handelingen) {
        this.handelingen = handelingen;
    }

    public List<String> getOnderdelen() {
        return onderdelen;
    }

    public void setOnderdelen(List<String> onderdelen) {
        this.onderdelen = onderdelen;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public String getSubTotaal() {
        return subTotaal;
    }

    public void setSubTotaal(String subTotaal) {
        this.subTotaal = subTotaal;
    }

    public String getTotaal() {
        return totaal;
    }

    public void setTotaal(String totaal) {
        this.totaal = totaal;
    }
}
