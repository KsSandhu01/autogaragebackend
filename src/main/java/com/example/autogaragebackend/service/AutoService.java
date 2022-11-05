package com.example.garagebackend.service;

import com.example.autogaragebackend.model.Auto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public interface AutoService {

    public abstract long createAuto(Auto auto);
    public long createAutoMetKlant(Auto auto, long customerid);
    public long createAutometBestandEnKlant(Auto auto, MultipartFile file, long customerid);
    public abstract String getBestandPath(long Autoid);
    public long createAutoMetBestand(Auto auto, MultipartFile file);
    public long createAutoMetBestandIndb(Auto auto, MultipartFile file);
    public abstract void updateAuto(long id, Auto auto);
    public abstract void deelUpdateAuto(long id, Map<String, String> fields);
    public abstract void deleteAuto(long id);
    public abstract Collection<Auto> getAutos();
    public abstract Collection<Auto> getAutosVanMerk(String brand);
    public abstract Collection<Auto> getAutosByModel(String model);
    public abstract Optional<Auto> getAutoById(long id);
    public abstract boolean AutoExistsById(long id);
}
