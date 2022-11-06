package com.example.autogaragebackend.controller;

import com.example.autogaragebackend.model.Onderdeel;
import com.example.autogaragebackend.service.OnderdeelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/onderdelen")
public class OnderdeelController {

    @Autowired
    private OnderdeelService onderdeelService;

    @GetMapping(value = "")
    public ResponseEntity<Collection<Onderdeel>> getOnderdelen() {
        return ResponseEntity.ok().body(onderdeelService.getOnderdelen());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Onderdeel>> getOnderdeel(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(onderdeelService.getOnderdeelById(id));
    }

    @PostMapping(value = "")
    public ResponseEntity<URI> createOnderdeel(@RequestBody Onderdeel onderdeel) {
        long newId = onderdeelService.createOnderdeel(onderdeel);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateOnderdeel(@PathVariable("id") long id, @RequestBody Onderdeel onderdeel) {
        onderdeelService.updateOnderdeel(id, onderdeel);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateDeelVanOnderdeel(@PathVariable("id") long id, @RequestBody Map<String, String> velden) {
        onderdeelService.updateDeelVanOnderdeel(id, velden);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteOnderdeel(@PathVariable("id") long id) {
        onderdeelService.deleteOnderdeel(id);
        return ResponseEntity.noContent().build();
    }
    
    
}
