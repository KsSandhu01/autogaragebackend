package com.example.autogaragebackend.controller;

import com.example.autogaragebackend.model.Bon;
import com.example.autogaragebackend.service.AfspraakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/v1/bon")
@PreAuthorize("hasAuthority('ROLE_KASSAMEDEWERKER')")
public class BonController {

    @Autowired
    AfspraakService afspraakService;

    @GetMapping("/genereerBon/{afspraakid}")

    public ResponseEntity<Bon> genereerBon(@PathVariable("afspraakid") long afspraakid) {
        return ResponseEntity.ok().body(afspraakService.genereerBon(afspraakid));
    }
}
