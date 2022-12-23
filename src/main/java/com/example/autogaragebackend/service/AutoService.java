package com.example.autogaragebackend.service;

import com.example.autogaragebackend.dto.AutoDto;
import com.example.autogaragebackend.model.Auto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public interface AutoService {

    public abstract long createAuto(AutoDto auto);
    public long createAutoMetKlant(AutoDto auto, long klantid);
    public long createAutometBestandEnKlant(AutoDto auto, MultipartFile file, long klantid);
    public abstract String getBestandPath(long autoid);
    public long createAutoMetBestand(AutoDto auto, MultipartFile file);
    public long createAutoMetBestandIndb(AutoDto auto, MultipartFile file);
    public abstract void updateAuto(long id, AutoDto auto);
    public abstract void deelUpdateAuto(long id, AutoDto fields);
    public abstract void deleteAuto(long id);
    public abstract Collection<Auto> getAutos();
    public abstract Collection<Auto> getAutosVanMerk(String merk);
    public abstract Collection<Auto> getAutosByModel(String model);
    public abstract Optional<Auto> getAutoById(long id);
    public abstract boolean autoExistsById(long id);
}
