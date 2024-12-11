package org.gestionare_taskuri.servicii;


import echipa.Angajat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
    public class AngajatService {
        @Autowired
        private AngajatRepository angajatRepository;

        public Optional<Angajat> getAngajatByName(String nume) {
            return angajatRepository.findByNume(nume);
        }

        public List<Angajat> getAngajatByRole(String rol) {
            return angajatRepository.findByRol(rol);
        }

        public List<Angajat> getAngajatByEchipa(Integer idEchipa) {
            return angajatRepository.findByIdEchipa(idEchipa);
        }

        public List<Angajat> getActivAngajat() {
            return angajatRepository.findByIsActiveTrueOrderByCreatedDateDesc();
        }

    public List<Angajat> getAngajatByRol(Angajat.Rol rol) {

        return List.of();
    }

    public void deleteAngajat(Angajat angajat) {

    }

   // public Optional<Angajat> getAngajatByNume(String johnDoe) {
    }

    //public Optional<Angajat> getAngajatById(int i) {
    //}

    //public Angajat createAngajat(Angajat angajat) {
   /// }



