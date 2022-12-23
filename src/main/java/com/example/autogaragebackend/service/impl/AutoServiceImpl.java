package com.example.autogaragebackend.service.impl;

import com.example.autogaragebackend.dto.AutoDto;
import com.example.autogaragebackend.dto.AutoMapper;
import com.example.autogaragebackend.exception.ResourceNotFoundException;
import com.example.autogaragebackend.model.Klant;
import com.example.autogaragebackend.model.Auto;
import com.example.autogaragebackend.repository.AutoRepository;
import com.example.autogaragebackend.service.AutoService;
import com.example.autogaragebackend.service.KlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static org.apache.naming.SelectorContext.prefix;

@Service
public class AutoServiceImpl implements AutoService {


    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private KlantService klantService;

    @Autowired
    private AutoMapper autoMapper;
    @Override
    public long createAuto(AutoDto dto) {
        Auto auto= autoMapper.map(dto);
        Auto auto1 = autoRepository.save(auto);
        return auto.getId();
    }

    @Override
    public long createAutoMetKlant(AutoDto dto, long klantId) {
        Klant klant = klantService.getKlantById(klantId).get();
        Auto auto= autoMapper.map(dto);
        auto.setKlant(klant);
        Auto auto1 = autoRepository.save(auto);
        return auto1.getId();
    }

    @Override
    public long createAutometBestandEnKlant(AutoDto dto, MultipartFile file, long klantId) {
        Klant klant = klantService.getKlantById(klantId).get();
        Auto auto = autoMapper.map(dto);
        auto.setKlant(klant);

        Blob blob = null;
        byte[] by = null;
        try {
            by = file.getBytes();
            blob = new SerialBlob(by);
        } catch (Exception e) {
            e.printStackTrace();
        }
        auto.setBestandNaam(file.getOriginalFilename());
        auto.setContentType(file.getContentType());
        auto.setPdfBestand(blob);
        auto = autoRepository.save(auto);
        return auto.getId();
    }

    @Override
    public String getBestandPath(long Autoid) {
        return autoRepository.findById(Autoid).get().getBestandLocatie();
    }

    static String bestandLocatie = "C:" + File.separator + "Autogarage" + File.separator;

    @Override
    public long createAutoMetBestand(AutoDto dto, MultipartFile file) {
        Auto auto = autoMapper.map(dto);
        Auto auto1 = autoRepository.save(auto);
        auto1.setBestandLocatie(bestandLocatie + auto1.getId() + "_" + file.getOriginalFilename());
        try {
            bestandOpslaan(file, auto1.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return auto1.getId();
    }


    @Override
    public long createAutoMetBestandIndb(AutoDto dto, MultipartFile bestand) {
        Auto auto = autoMapper.map(dto);
        Blob blob = null;
        byte[] by = null;
        try {
            by = bestand.getBytes();
            blob = new SerialBlob(by);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        auto.setBestandNaam(bestand.getOriginalFilename());
        auto.setContentType(auto.getContentType());
        auto.setPdfBestand(blob);
        auto = autoRepository.save(auto);
        return auto.getId();

    }

    @Override
    public void updateAuto(long id, AutoDto auto) {
        if (!autoRepository.existsById(id)) throw new ResourceNotFoundException();
        Auto bestaandeAuto = autoRepository.findById(id).get();
        bestaandeAuto.setKenteken(auto.getKenteken());
        bestaandeAuto.setMerk(auto.getMerk());
        bestaandeAuto.setModel(auto.getModel());
        bestaandeAuto.setKmStand(auto.getKmStand());
        bestaandeAuto.setBouwJaar(auto.getBouwJaar());
        bestaandeAuto.setKleur(auto.getKleur());

        autoRepository.save(bestaandeAuto);
    }

    @Override
    public void deelUpdateAuto(long id, AutoDto velden) {
        if (!autoRepository.existsById(id)) throw new ResourceNotFoundException();
        Auto auto = autoRepository.findById(id).get();
        autoMapper.update(auto,velden);
//        for (String field : velden.keySet()) {
//            switch (field.toLowerCase()) {
//                case "kenteken":
//                    auto.setKenteken((String) velden.get(field));
//                    break;
//                case "merk":
//                    auto.setMerk((String) velden.get(field));
//                    break;
//                case "model":
//                    auto.setModel((String) velden.get(field));
//                    break;
//                case "kmstand":
//                    auto.setKmStand((String) velden.get(field));
//                    break;
//                case "bouwjaar":
//                    auto.setBouwJaar((String) velden.get(field));
//                    break;
//                case "kleur":
//                    auto.setKleur((String) velden.get(field));
//                    break;
//            }
//        }
        autoRepository.save(auto);
    }

    @Override
    public void deleteAuto(long id) {
        if (!autoRepository.existsById(id)) throw new ResourceNotFoundException();
        autoRepository.deleteById(id);

    }

    @Override
    public Collection<Auto> getAutos() {
        return autoRepository.findAll();

    }

    @Override
    public Collection<Auto> getAutosVanMerk(String brand) {
        return autoRepository.findAllByMerk(brand);

    }

    @Override
    public Collection<Auto> getAutosByModel(String model) {
        return autoRepository.findAllByModel(model);
    }

    @Override
    public Optional<Auto> getAutoById(long id) {
        if (!autoRepository.existsById(id)) throw new ResourceNotFoundException();
        return autoRepository.findById(id);
    }

    @Override
    public boolean autoExistsById(long id) {
        return autoRepository.existsById(id);
    }

    private void bestandOpslaan(MultipartFile bestand, long id) throws IOException {
        if (!bestand.isEmpty()) {
            byte[] bytes = bestand.getBytes();
            Path path = Paths.get(bestandLocatie + prefix + "_" + bestand.getOriginalFilename());
            Files.write(path, bytes);
        }
    }
}
