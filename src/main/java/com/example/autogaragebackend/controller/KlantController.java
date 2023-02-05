package com.example.autogaragebackend.controller;

import com.example.autogaragebackend.dto.KlantDto;
import com.example.autogaragebackend.model.Klant;
import com.example.autogaragebackend.service.KlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/klanten")
@PreAuthorize("hasAuthority('ROLE_ADMINISTRATIEFMEDEWERKER')")
public class KlantController {

    @Autowired
    private KlantService klantService;

    @GetMapping(value = "")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATIEFMEDEWERKER', 'ROLE_MONTEUR')")
    public ResponseEntity<Collection<Klant>> getKlanten() {
        return ResponseEntity.ok().body(klantService.getKlanten());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Klant>> getKlant(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(klantService.getKlantById(id));
    }
    @PostMapping(value = "")
    public ResponseEntity<URI> createKlant(@RequestBody KlantDto klant) {
        long newId = klantService.createKlant(klant);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateKlant(@PathVariable("id") long id, @RequestBody KlantDto klant) {
        klantService.updateKlant(id, klant);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> deelUpdateKlant(@PathVariable("id") long id, @RequestBody KlantDto fields) {
        klantService.deelUpdateKlant(id, fields);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteKlant(@PathVariable("id") long id) {
        klantService.deleteKlant(id);
        return ResponseEntity.noContent().build();
    }
}
