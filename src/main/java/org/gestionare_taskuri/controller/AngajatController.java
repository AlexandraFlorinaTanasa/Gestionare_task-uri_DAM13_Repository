package org.gestionare_taskuri.controller;

import org.gestionare_taskuri.echipa.Angajat;
import org.gestionare_taskuri.servicii.AngajatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AngajatController {





import java.util.List;

    @RestController
    @RequestMapping("/api/members")
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
    }


}
