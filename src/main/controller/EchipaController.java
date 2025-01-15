package org.gestionare_taskuri.controller;
import org.gestionare_taskuri.echipa.Echipa;
import org.gestionare_taskuri.servicii.EchipaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/echipe")
public class EchipaController {

    @Autowired(required = false)
    private EchipaService echipaService;

    @GetMapping
    public List<Echipa> getAllEchipe() {
        return echipaService.getAllEchipe();
    }

    @PostMapping
    public ResponseEntity<Echipa> createEchipa(@RequestBody Echipa echipa) {
        Echipa savedEchipa = echipaService.saveEchipa(echipa);
        return ResponseEntity.ok(savedEchipa);
    }


}
