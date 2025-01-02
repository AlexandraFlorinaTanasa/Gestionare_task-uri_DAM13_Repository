package org.gestionare_taskuri.controller;

import jakarta.validation.Valid;
import org.gestionare_taskuri.DTO.AngajatDTO;
import org.gestionare_taskuri.echipa.Angajat;
import org.gestionare_taskuri.servicii.AngajatService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/members")
    @Validated
    public class AngajatController {
        private final AngajatService angajatService;

        public AngajatController(AngajatService angajatService) {
            this.angajatService = angajatService;
        }

        @GetMapping
        public List<Angajat> getAngajati() {
            return angajatService.getAngajati();
        }

        @PostMapping
        public Angajat addAngajat(@RequestBody Angajat angajat) {
            return angajatService.addAngajat(angajat);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Angajat addAngajat(@Valid @RequestBody AngajatDTO angajatDTO) {
            return angajatService.addAngajat(dtoToEntity(angajatDTO));
        }

        private Angajat dtoToEntity(AngajatDTO angajatDTO) {
            Angajat angajat = new Angajat();
            angajat.setNume(angajatDTO.getNume());
            angajat.setEmail(angajatDTO.getEmail());
            angajat.setRol(angajatDTO.getRol());
            angajat.setAbilitati(angajatDTO.getAbilitati());
            return angajat;
        }

    }
    



