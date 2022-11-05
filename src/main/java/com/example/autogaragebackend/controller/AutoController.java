package com.example.autogaragebackend.controller;

import com.example.autogaragebackend.model.Auto;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/autos")
public class AutoController {

    @Autowired
    private com.example.garagebackend.service.AutoService autoService;

    @GetMapping(value = "")
    public ResponseEntity<Collection<Auto>> getAutos() {
        return ResponseEntity.ok().body(autoService.getAutos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Auto> getAuto(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(autoService.getAutoById(id).get());
    }

    @PostMapping(value = "")
    public ResponseEntity<URI> createAuto(@RequestBody Auto auto) {
        long newId = autoService.createAuto(auto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PostMapping(value = "/createAutoMetKlant/{klantid}")
    public ResponseEntity<URI> createAutoMetKlant(@RequestBody Auto auto,
                                                  @PathVariable("klantid") long klantid) {

        long newId = autoService.createAutoMetKlant(auto, klantid);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PostMapping(value = "/createAutometBestandEnKlant/{klantid}")
    public ResponseEntity<URI> createAutometBestandEnKlant(@PathVariable("klantid") long klantid, @RequestParam("kenteken") String kenteken,
                                                           @RequestParam("merk") String merk, @RequestParam("model") String model,
                                                           @RequestParam("kmstand") String kmstand, @RequestParam("bouwjaar") String bouwjaar,
                                                           @RequestParam(value = "bestand", required = false) MultipartFile bestand) {
        Auto auto = new Auto();
        auto.setMerk(merk);
        auto.setKmStand(kmstand);
        auto.setModel(model);
        auto.setKenteken(kenteken);
        auto.setBouwJaar(bouwjaar);
        long newId = autoService.createAutometBestandEnKlant(auto, bestand, klantid);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    //local opslaan
    @PostMapping(value = "/createAutoMetBestand")
    public ResponseEntity<URI> createAutoMetBestand(@RequestParam("kenteken") String kenteken,
                                                    @RequestParam("merk") String merk, @RequestParam("model") String model,
                                                    @RequestParam("kmstand") String kmstand, @RequestParam("bouwjaar") String bouwjaar,
                                                    @RequestParam(value = "bestand", required = false) MultipartFile bestand) {
        Auto auto = new Auto();
        auto.setMerk(merk);
        auto.setKmStand(kmstand);
        auto.setModel(model);
        auto.setKenteken(kenteken);
        auto.setBouwJaar(bouwjaar);
        long newId = autoService.createAutoMetBestand(auto, bestand);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    //database opslaan
    @PostMapping(value = "/createAutoMetBestandIndb")
    public ResponseEntity<URI> createAutoMetBestandIndb(@RequestParam("kenteken") String kenteken,
                                                        @RequestParam("merk") String merk, @RequestParam("model") String model,
                                                        @RequestParam("kmstand") String kmstand, @RequestParam("bouwjaar") String bouwjaar,
                                                        @RequestParam(value = "bestand", required = false) MultipartFile bestand) {
        Auto auto = new Auto();
        auto.setMerk(merk);
        auto.setKmStand(kmstand);
        auto.setModel(model);
        auto.setKenteken(kenteken);
        auto.setBouwJaar(bouwjaar);

        long newId = autoService.createAutoMetBestandIndb(auto, bestand);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @RequestMapping("/download/{autoid}")
    public Auto download(@PathVariable("autoid")
                         long autoid, HttpServletResponse response) {

        Auto bestand = autoService.getAutoById(autoid).get();
        try {
            response.setHeader("Content-Disposition", "inline;filename=\"" + bestand.getBestandNaam() + "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(bestand.getContentType());
            IOUtils.copy(bestand.getPdfBestand().getBinaryStream(), out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bestand;
    }

    @RequestMapping(value = "/pdf/{id}", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<InputStreamResource> download(@PathVariable("id") long id) throws IOException {
        String fileName = autoService.getBestandPath(id);
        System.out.println("Calling Download:- " + fileName);
        File initialFile = new File(fileName);
        InputStream targetStream = new FileInputStream(initialFile);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "filename=" + "Auto");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        //headers.setContentLength(targetStream.contentLength());
        ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
                new InputStreamResource(targetStream), headers, HttpStatus.OK);
        return response;

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateAuto(@PathVariable("id") long id, @RequestBody Auto auto) {
        autoService.updateAuto(id, auto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> deelUpdateAuto(@PathVariable("id") long id, @RequestBody Map<String, String> velden) {
        autoService.deelUpdateAuto(id, velden);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteAuto(@PathVariable("id") long id) {
        autoService.deleteAuto(id);
        return ResponseEntity.noContent().build();
    }


}
