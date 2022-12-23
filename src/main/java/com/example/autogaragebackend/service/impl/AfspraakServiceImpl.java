package com.example.autogaragebackend.service.impl;

import com.example.autogaragebackend.dto.HandelingDto;
import com.example.autogaragebackend.enums.AfspraakStatus;
import com.example.autogaragebackend.model.*;
import com.example.autogaragebackend.repository.AfspraakRepository;
import com.example.autogaragebackend.repository.GebruikteOnderdeelRepository;
import com.example.autogaragebackend.repository.UitgevoerdeHandelingRepository;
import com.example.autogaragebackend.service.AfspraakService;
import com.example.autogaragebackend.service.HandelingService;
import com.example.autogaragebackend.service.OnderdeelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class AfspraakServiceImpl implements AfspraakService {

    @Autowired
    AfspraakRepository afspraakRepository;

    @Autowired
    UitgevoerdeHandelingRepository uitgevoerdeHandelingRepository;

    @Autowired
    GebruikteOnderdeelRepository gebruikteOnderdeelRepository;

    @Autowired
    KlantServiceImpl klantService;

    @Autowired
    AutoServiceImpl autoservice;

    @Autowired
    OnderdeelService onderdeelService;

    @Autowired
    HandelingService handelingService;


    @Override
    public Afspraak createAfspraak(long klantId, long autoid) {
        Afspraak afspraak = new Afspraak();

        Klant klant = klantService.getKlantById(klantId).get();
        afspraak.setKlant(klant);
        afspraak.setStatus(AfspraakStatus.GEPLAND.toString());
        afspraak.setTijd(new Timestamp(new Date().getTime()));
        afspraak.setAuto(autoservice.getAutoById(autoid).get());

        return afspraakRepository.save(afspraak);
    }

    @Override
    public Afspraak updateStatus(long afspraakid, AfspraakStatus afspraakStatus) {
        Afspraak afspraak = afspraakRepository.findById(afspraakid).get();
        afspraak.setStatus(afspraakStatus.toString());
        return afspraakRepository.save(afspraak);
    }

    @Override
    public Afspraak getAfspraak(long afspraakid) {
        return afspraakRepository.findById(afspraakid).get();
    }

    @Override
    public List<Afspraak> getAllAfspraken() {
        return afspraakRepository.findAll();
    }

    @Override
    public UitgevoerdeHandelingen voegHandelingAanAfspraak(long afspraakid, long handelingid) {
        Afspraak afspraak = afspraakRepository.findById(afspraakid).get();
        Handeling handeling = handelingService.getHandelingById(handelingid).get();

        UitgevoerdeHandelingen uitgevoerdeHandeling = new UitgevoerdeHandelingen();
        uitgevoerdeHandeling.setHandeling(handeling);
        afspraak.voegAfspraakHandeling(uitgevoerdeHandeling);
        uitgevoerdeHandeling.setAfspraak(afspraak);
        uitgevoerdeHandeling = uitgevoerdeHandelingRepository.save(uitgevoerdeHandeling);

        return uitgevoerdeHandeling;
    }

    @Override
    public UitgevoerdeHandelingen voegOverigeHandelingAanAfspraak(long afspraakid, HandelingDto handeling) {

        long handelingid = handelingService.createHandeling(handeling);

        return voegHandelingAanAfspraak(afspraakid, handelingid);
    }

    @Override
    public GebruikteOnderdelen voegOnderdeelAanAfspraak(long afspraakid, long onderdeelid) {
        Afspraak afspraak = afspraakRepository.findById(afspraakid).get();
        Onderdeel onderdeel = onderdeelService.getOnderdeelById(onderdeelid).get();

        GebruikteOnderdelen gebruikteOnderdeel = new GebruikteOnderdelen();
        gebruikteOnderdeel.setOnderdeel(onderdeel);
        afspraak.voegAfspraakOnderdeel(gebruikteOnderdeel);
        gebruikteOnderdeel.setAfspraak(afspraak);
        gebruikteOnderdeel = gebruikteOnderdeelRepository.save(gebruikteOnderdeel);

        return gebruikteOnderdeel;
    }

    @Override
    public List<Afspraak> fetchByStatus(String status) {
        return afspraakRepository.findAllByStatus(status);
    }

    @Override
    public List<Afspraak> fetchByKlant(long klantid) {
        return afspraakRepository.fetchByKlant(klantid);

    }

    @Override
    public List<Afspraak> fetchByKlantEnStatus(long klantid, String status) {
        return afspraakRepository.fetchByKlantEnStatus(klantid, status);

    }

    @Override
    public Bon genereerBon(long afspraakid) {
        Afspraak afspraak = afspraakRepository.findById(afspraakid).get();
        Bon bon = new Bon();
        double totalenKosten = 0.00;
        bon.setAfspraakid(afspraakid);
        bon.setKlant(afspraak.getKlant());
        bon.setApkKosten("€45,00");
        totalenKosten += 45.00;
        List<String> list = new ArrayList<String>();
        if (afspraak.getUitgevoerdeHandelingen() != null) {
            Set<UitgevoerdeHandelingen> handelingen = afspraak.getUitgevoerdeHandelingen();
            for (UitgevoerdeHandelingen uitgevoerdeHandelingen : handelingen) {
                list.add("Handeling : " + uitgevoerdeHandelingen.getHandeling().getNaam() + " Prijs : " + uitgevoerdeHandelingen.getHandeling().getPrijsString());
                totalenKosten += Double.parseDouble(uitgevoerdeHandelingen.getHandeling().getPrijs());
            }
        }
        bon.setHandelingen(list);
        list = new ArrayList<String>();

        if (afspraak.getGebruikteOnderdelen() != null) {

            Set<GebruikteOnderdelen> onderdelen = afspraak.getGebruikteOnderdelen();
            for (GebruikteOnderdelen onderdeel : onderdelen) {
                list.add("Onderdeel : " + onderdeel.getOnderdeel().getNaam() + " Prijs : " + onderdeel.getOnderdeel().getPrijs());
                totalenKosten += Double.parseDouble(onderdeel.getOnderdeel().getPrijs());
            }
        }
        bon.setOnderdelen(list);
        bon.setSubTotaal(String.valueOf(totalenKosten));
        totalenKosten += ((totalenKosten * 21) / 100);
        bon.setTotaal(String.valueOf(totalenKosten));
        return bon;
    }
}
