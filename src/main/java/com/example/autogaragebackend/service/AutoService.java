package com.example.autogaragebackend.service;

import com.example.autogaragebackend.dto.AutoDto;
import com.example.autogaragebackend.model.Auto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Optional;

@Service
public interface AutoService {

    Auto createAuto(AutoDto auto);
    Auto createAutoMetKlant(AutoDto auto, long klantid);
    Auto createAutometBestandEnKlant(AutoDto auto, MultipartFile file, long klantid);
    String getBestandPath(long autoid);
    Auto createAutoMetBestand(AutoDto auto, MultipartFile file);
    Auto createAutoMetBestandIndb(AutoDto auto, MultipartFile file);
    Auto updateAuto(long id, AutoDto auto);
    Auto deelUpdateAuto(long id, AutoDto fields);
    void deleteAuto(long id);
    Collection<Auto> getAutos();
    Collection<Auto> getAutosVanMerk(String merk);
    Collection<Auto> getAutosByModel(String model);
    Optional<Auto> getAutoById(long id);
    boolean autoExistsById(long id);
}
