package com.example.autogaragebackend.controller;

import com.example.autogaragebackend.model.Medewerker;
import com.example.autogaragebackend.service.MedewerkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/medewerkers")
public class MedewerkerController {

    @Autowired
    private MedewerkerService medewerkerService;

    @GetMapping(value = "")
    public ResponseEntity<Collection<Medewerker>> getMedewerkers() {
        return ResponseEntity.ok().body(medewerkerService.getMedewerkers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Medewerker>> getMedewerker(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(medewerkerService.getMedewerkerMetId(id));
    }

    @PostMapping(value = "")
    public ResponseEntity<URI> createMedewerker(@RequestBody Medewerker medewerker) {
        long newId = medewerkerService.createMedewerker(medewerker);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateMedewerker(@PathVariable("id") long id, @RequestBody Medewerker medewerker) {
        medewerkerService.updateMedewerker(id, medewerker);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateDeelVanMedewerker(@PathVariable("id") long id, @RequestBody Map<String, String> fields) {
        medewerkerService.updateDeelVanMedewerker(id, fields);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteMedewerker(@PathVariable("id") long id) {
        medewerkerService.deleteMedewerker(id);
        return ResponseEntity.noContent().build();
    }

}
