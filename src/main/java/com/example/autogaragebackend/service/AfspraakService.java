package com.example.autogaragebackend.service;

import com.example.autogaragebackend.dto.HandelingDto;
import com.example.autogaragebackend.enums.AfspraakStatus;
import com.example.autogaragebackend.model.*;

import java.util.List;


public interface AfspraakService {
    Afspraak createAfspraak(long klantId, long autoid);
    Afspraak updateStatus(long afspraakid, AfspraakStatus afspraakStatus);
    Afspraak getAfspraak(long afspraakid);
    List<Afspraak> getAllAfspraken();
    UitgevoerdeHandelingen voegHandelingAanAfspraak(long afspraakid, long handelingid);
    UitgevoerdeHandelingen voegOverigeHandelingAanAfspraak(long afspraakid, HandelingDto handeling);
    GebruikteOnderdelen voegOnderdeelAanAfspraak(long afspraakid, long onderdeelid);
    List<Afspraak> fetchByStatus(String status);
    List<Afspraak> fetchByKlant(long klantid);
    List<Afspraak> fetchByKlantEnStatus(long klantid, String status);
    Bon genereerBon(long afspraakid);
}
