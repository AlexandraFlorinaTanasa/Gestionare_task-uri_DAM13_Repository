package org.gestionare_taskuri.repository;

import org.gestionare_taskuri.echipa.Angajat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
    public interface AngajatRepository extends JpaRepository<Angajat, Integer> {

        // Găsește un angajat după numele său
        Optional<Angajat> findByNume(String nume);

        // Găsește angajați după rolul lor
        List<Angajat> findByRole(Angajat.Rol rol);

        // Găsește angajați care fac parte dintr-o anumită echipă
        List<Angajat> findByIdEchipa(Integer echipa);

        // Găsește angajați activi care au fost adăugați recent
        List<Angajat> findByIsActiveTrueOrderByCreatedDateDesc();

        // Găsește un angajat după email
        List<Angajat> findByEmail(String email);
    }

    
