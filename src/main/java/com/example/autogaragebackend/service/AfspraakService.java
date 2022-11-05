package com.example.autogaragebackend.service;

import com.example.autogaragebackend.enums.AfspraakStatus;
import com.example.autogaragebackend.model.*;

import java.util.List;

public interface AfspraakService {
    public Afspraak createAfspraak(long klantId, long autoid);
    public Afspraak updateStatus(long afspraakid, AfspraakStatus afspraakStatus);
    public Afspraak getAfspraak(long afspraakid);
    public List<Afspraak> getAllAfspraken();
    public UitgevoerdeHandelingen voegHandelingAanAfspraak(long afspraakid, long handelingid);
    public UitgevoerdeHandelingen voegOverigeHandelingAanAfspraak(long afspraakid, Handeling handeling);
    public GebruikteOnderdelen voegOnderdeelAanAfspraak(long afspraakid, long onderdeelid);
    public List<Afspraak> fetchByStatus(String status);
    public List<Afspraak> fetchByKlant(long klantid);
    public List<Afspraak> fetchByKlantEnStatus(long klantid, String status);
    public Bon genereerBon(long afspraakid);
}
