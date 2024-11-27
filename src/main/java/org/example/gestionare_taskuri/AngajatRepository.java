package org.example.gestionare_taskuri;


import echipa.Angajat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AngajatRepository extends JpaRepository<Angajat,Integer> {


    // Găsește un angajat după numele său
    Optional<Angajat> findByNume(String nume);

    // Găsește angajați după rolul lor
    List<Angajat> findByRol(String rol);

    // Găsește angajați care fac parte dintr-o anumită echipă
    List<Angajat> findByIdEchipa(Integer idEchipa);

    // Găsește angajați activi care au fost adăugați recent
    List<Angajat> findByIsActiveTrueOrderByCreatedDateDesc();
}
