package com.example.autogaragebackend.controller;


import com.example.autogaragebackend.dto.HandelingDto;
import com.example.autogaragebackend.enums.AfspraakStatus;
import com.example.autogaragebackend.model.*;
import com.example.autogaragebackend.service.AfspraakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/v1/afspraak")
public class AfspraakController {

    @Autowired
    private AfspraakService afspraakService;

    @GetMapping("/createAfspraak/{klantid}/{autoid}")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATIEFMEDEWERKER')")
    public ResponseEntity<Afspraak> createAfspraak(@PathVariable("klantid") long id, @PathVariable("autoid") long autoid) {
        return ResponseEntity.ok().body(afspraakService.createAfspraak(id, autoid));
    }

    @GetMapping("/{afspraakid}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATIEFMEDEWERKER', 'ROLE_MONTEUR')")
    public ResponseEntity<Afspraak> getAfspraak(@PathVariable("afspraakid") long id) {
        return ResponseEntity.ok().body(afspraakService.getAfspraak(id));
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATIEFMEDEWERKER', 'ROLE_MONTEUR')")
    public ResponseEntity<List<Afspraak>> getAfspraken() {
        return ResponseEntity.ok().body(afspraakService.getAllAfspraken());
    }

    @GetMapping("/voegHandeling/{afspraakid}/{handelingid}")
    @PreAuthorize("hasAnyAuthority('ROLE_MONTEUR')")
    public ResponseEntity<UitgevoerdeHandelingen> addHandeling(@PathVariable("afspraakid") long id, @PathVariable("handelingid") long handelingid) {
        return ResponseEntity.ok().body(afspraakService.voegHandelingAanAfspraak(id, handelingid));
    }

    @PostMapping("/voegOverigeHandeling/{afspraakid}")
    @PreAuthorize("hasAnyAuthority('ROLE_MONTEUR')")
    public ResponseEntity<UitgevoerdeHandelingen> addOverigeHandeling(@PathVariable("afspraakid") long id, @RequestBody HandelingDto handeling) {
        return ResponseEntity.ok().body(afspraakService.voegOverigeHandelingAanAfspraak(id, handeling));
    }

    @PostMapping("/voegOnderdeel/{afspraakid}/{onderdeelid}")
    @PreAuthorize("hasAnyAuthority('ROLE_MONTEUR')")
    public ResponseEntity<GebruikteOnderdelen> addOnderdeel(@PathVariable("afspraakid") long id, @PathVariable("onderdeelid") long onderdeelid) {
        return ResponseEntity.ok().body(afspraakService.voegOnderdeelAanAfspraak(id, onderdeelid));
    }

    @PutMapping("/updatestatus/{afspraakid}/{status}")
    @PreAuthorize("hasAnyAuthority('ROLE_MONTEUR', 'ROLE_KASSAMEDEWERKER')")
    public ResponseEntity<Afspraak> updateAfspraak(@PathVariable("afspraakid") long id, @PathVariable("status") AfspraakStatus status) {
        return ResponseEntity.ok().body(afspraakService.updateStatus(id, status));
    }

    @GetMapping("/fetchByStatus/{status}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATIEFMEDEWERKER', 'ROLE_MONTEUR')")
    public ResponseEntity<List<Afspraak>> fetchByStatus(@PathVariable("status") String status) {
        return ResponseEntity.ok().body(afspraakService.fetchByStatus(status));
    }

    @GetMapping("/fetchByKlant/{klantid}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATIEFMEDEWERKER', 'ROLE_MONTEUR')")
    public ResponseEntity<List<Afspraak>> fetchByKlant(@PathVariable("klantid") long id) {
        return ResponseEntity.ok().body(afspraakService.fetchByKlant(id));
    }

    @GetMapping("/fetchByKlantenStatus/{klantid}/{status}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATIEFMEDEWERKER', 'ROLE_MONTEUR')")
    public ResponseEntity<List<Afspraak>> fetchByKlantEnStatus(@PathVariable("klantid") long id, @PathVariable("status") String status) {
        return ResponseEntity.ok().body(afspraakService.fetchByKlantEnStatus(id, status));
    }
}