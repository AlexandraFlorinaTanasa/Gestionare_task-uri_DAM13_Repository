package org.gestionare_taskuri.servicii;

import org.gestionare_taskuri.echipa.Angajat;
import org.gestionare_taskuri.repository.AngajatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
    public class AngajatService {
    @Autowired
    private AngajatRepository angajatRepository;

    public List<Angajat> getAngajatByNume(String nume) {

        return angajatRepository.findByNume(nume);
    }

    public List<Angajat> getAngajatByRole(Angajat.Rol rol) {

        return angajatRepository.findByRole(rol);
    }

    public List<Angajat> getAngajatByIdEchipa(Integer idEchipa) {
        List<Angajat> angajati = angajatRepository.findByIdEchipa(idEchipa);
        if (angajati.isEmpty()) {
            throw new RuntimeException("Nu există angajați pentru echipa cu ID-ul: " + idEchipa);
        }
        return angajati;


    }

    public List<Angajat> getActivAngajat() {

        return angajatRepository.findByIsActiveTrueOrderByCreatedDateDesc();
    }



    public void deleteAngajat(Integer id) {
        if (!angajatRepository.existsById(id)) {
            throw new RuntimeException("Angajatul cu ID-ul " + id + " nu există.");
        }
        angajatRepository.deleteById(id);
    }


    public List<Angajat> getAngajati() {
        return angajatRepository.findAll();
    }

    public Angajat addAngajat(Angajat angajat) {
        if (angajat.getNume().isEmpty()) {
            throw new RuntimeException("Numele angajatului este obligatoriu.");
        }
        return angajatRepository.save(angajat);
    }



    public Optional<Angajat> getAngajatById(Integer id) {
    return angajatRepository.findById(id);
    }

    public Angajat createdAngajat(Angajat angajat) {

        return angajatRepository.save(angajat);
    }




}


