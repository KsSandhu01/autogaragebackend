package com.example.autogaragebackend.controller;


import com.example.autogaragebackend.dto.HandelingDto;
import com.example.autogaragebackend.model.Handeling;
import com.example.autogaragebackend.service.HandelingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/handelingen")
public class HandelingController {

    @Autowired
    private HandelingService handelingService;

    @GetMapping(value = "")
    public ResponseEntity<Collection<Handeling>> getHandelingen() {
        return ResponseEntity.ok().body(handelingService.getHandelingen());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Handeling>> getHandeling(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(handelingService.getHandelingById(id));
    }

    @PostMapping(value = "")
    public ResponseEntity<URI> createHandeling(@RequestBody HandelingDto handeling) {
        long newId = handelingService.createHandeling(handeling);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateHandeling(@PathVariable("id") long id, @RequestBody HandelingDto handeling) {
        handelingService.updateHandeling(id, handeling);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateDeelVanHandeling(@PathVariable("id") long id, @RequestBody HandelingDto velden) {
        handelingService.updateDeelVanHandeling(id, velden);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteHandeling(@PathVariable("id") long id) {
        handelingService.deleteHandeling(id);
        return ResponseEntity.noContent().build();
    }


}
