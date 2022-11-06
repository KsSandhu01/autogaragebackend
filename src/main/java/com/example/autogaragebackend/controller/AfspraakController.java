package com.example.autogaragebackend.controller;


import com.example.autogaragebackend.enums.AfspraakStatus;
import com.example.autogaragebackend.model.*;
import com.example.autogaragebackend.service.AfspraakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/v1/afspraak")
public class AfspraakController {


    @Autowired
    AfspraakService afspraakService;

    @PostMapping("/createAfspraak/{klantid}/{autoid}")
    public ResponseEntity<Afspraak> createAfspraak(@PathVariable("klantid") long id, @PathVariable("autoid") long autoid) {
        return ResponseEntity.ok().body(afspraakService.createAfspraak(id, autoid));
    }

    @GetMapping("/{afspraakid}")
    public ResponseEntity<Afspraak> getAfspraak(@PathVariable("afspraakid") long id) {
        return ResponseEntity.ok().body(afspraakService.getAfspraak(id));
    }

    @GetMapping("")
    public ResponseEntity<List<Afspraak>> getAfspraken() {
        return ResponseEntity.ok().body(afspraakService.getAllAfspraken());
    }

    @PostMapping("/voegHandeling/{afspraakid}/{handelingid}")
    public ResponseEntity<UitgevoerdeHandelingen> addHandeling(@PathVariable("afspraakid") long id, @PathVariable("handelingid") long handelingid) {
        return ResponseEntity.ok().body(afspraakService.voegHandelingAanAfspraak(id, handelingid));
    }

    @PostMapping("/voegOverigeHandeling/{afspraakid}")
    public ResponseEntity<UitgevoerdeHandelingen> addOverigeHandeling(@PathVariable("afspraakid") long id, @RequestBody Handeling handeling) {
        return ResponseEntity.ok().body(afspraakService.voegOverigeHandelingAanAfspraak(id, handeling));
    }

    @PostMapping("/voegOnderdeel/{afspraakid}/{onderdeelid}")
    public ResponseEntity<GebruikteOnderdelen> addOnderdeel(@PathVariable("afspraakid") long id, @PathVariable("onderdeelid") long onderdeelid) {
        return ResponseEntity.ok().body(afspraakService.voegOnderdeelAanAfspraak(id, onderdeelid));
    }

    @PutMapping("/updatestatus/{afspraakid}/{status}")
    public ResponseEntity<Afspraak> updateAfspraak(@PathVariable("afspraakid") long id, @PathVariable("status") AfspraakStatus status) {
        return ResponseEntity.ok().body(afspraakService.updateStatus(id, status));
    }

    @GetMapping("/fetchByStatus/{status}")
    public ResponseEntity<List<Afspraak>> fetchByStatus(@PathVariable("status") String status) {
        return ResponseEntity.ok().body(afspraakService.fetchByStatus(status));
    }

    @GetMapping("/fetchByKlant/{klantid}")
    public ResponseEntity<List<Afspraak>> fetchByKlant(@PathVariable("klantid") long id) {
        return ResponseEntity.ok().body(afspraakService.fetchByKlant(id));
    }

    @GetMapping("/fetchByKlantenStatus/{klantid}/{status}")
    public ResponseEntity<List<Afspraak>> fetchByKlantEnStatus(@PathVariable("klantid") long id, @PathVariable("status") String status) {
        return ResponseEntity.ok().body(afspraakService.fetchByKlantEnStatus(id, status));
    }


}
