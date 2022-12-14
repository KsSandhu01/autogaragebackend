package com.example.autogaragebackend.controller;

import com.example.autogaragebackend.dto.AutoDto;
import com.example.autogaragebackend.model.Auto;
import com.example.autogaragebackend.service.AutoService;
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
    private AutoService autoService;

    @GetMapping(value = "")
    public ResponseEntity<Collection<Auto>> getAutos() {
        return ResponseEntity.ok().body(autoService.getAutos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Auto> getAuto(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(autoService.getAutoById(id).get());
    }

    @PostMapping(value = "")
    public ResponseEntity<Auto> createAuto(@RequestBody AutoDto auto) {
        Auto auto1= autoService.createAuto(auto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(auto1).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).body(auto1);
    }

    @PostMapping(value = "/createAutoMetKlant/{klantid}")
    public ResponseEntity<Auto> createAutoMetKlant(@RequestBody AutoDto auto,
                                                  @PathVariable("klantid") long klantid) {

       Auto auto1= autoService.createAutoMetKlant(auto, klantid);

//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(newId).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).body(auto1);
    }

@PostMapping(value = "/createAutometBestandEnKlant/{klantid}")
public ResponseEntity<Auto> createAutometBestandEnKlant(@PathVariable("klantid") long klantid, @RequestBody AutoDto dto,
                                                       @RequestParam(value = "bestand", required = false) MultipartFile bestand) {

        Auto auto = autoService.createAutometBestandEnKlant(dto, bestand, klantid);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(newId).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).body(auto);
    }

    //local opslaan
    @PostMapping(value = "/createAutoMetBestand")
    public ResponseEntity<Auto> createAutoMetBestand(@RequestBody AutoDto dto,
                                                    @RequestParam(value = "bestand", required = false) MultipartFile bestand) {

        Auto auto =autoService.createAutoMetBestand(dto, bestand);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(newId).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).body(auto);
    }

    //database opslaan
    @PostMapping(value = "/createAutoMetBestandIndb")
    public ResponseEntity<Auto> createAutoMetBestandIndb(@RequestBody AutoDto dto,
                                                        @RequestParam(value = "bestand", required = false) MultipartFile bestand) {
        Auto auto =autoService.createAutoMetBestandIndb(dto, bestand);

//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(newId).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).body(auto);
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
    public ResponseEntity<Auto> updateAuto(@PathVariable("id") long id, @RequestBody AutoDto auto) {
        Auto  auto1 = autoService.updateAuto(id, auto);
        return ResponseEntity.ok(auto1);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Auto> deelUpdateAuto(@PathVariable("id") long id, @RequestBody AutoDto velden) {
        Auto auto =autoService.deelUpdateAuto(id, velden);
        return ResponseEntity.ok(auto);
    }




    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteAuto(@PathVariable("id") long id) {
        autoService.deleteAuto(id);
        return ResponseEntity.noContent().build();
    }


}
