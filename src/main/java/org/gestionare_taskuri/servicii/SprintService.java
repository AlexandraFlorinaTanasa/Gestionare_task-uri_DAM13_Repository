package org.gestionare_taskuri.servicii;

import org.gestionare_taskuri.repository.SprintRepository;
import org.gestionare_taskuri.task.SprintPlanning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SprintService {

    private final SprintRepository sprintRepository;

    @Autowired
    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    // Obține toate sprint-urile
    public List<SprintPlanning> getAllSprints() {
        return sprintRepository.findAll();
    }

    // Obține un sprint după ID
    public SprintPlanning getSprintById(Integer codSprint) {
        return sprintRepository.findById(codSprint)
                .orElseThrow(() -> new Error("Sprint-ul cu ID-ul " + codSprint + " nu a fost găsit."));
    }

    // Adaugă un sprint
    public SprintPlanning addSprint(SprintPlanning sprint) {
        if (sprint == null) {
            throw new Error("Sprint-ul nu poate fi null.");
        }
        return sprintRepository.save(sprint);
    }

    // Actualizează un sprint existent
    public SprintPlanning updateSprint(Integer codSprint, SprintPlanning sprintPlanning) {
        SprintPlanning existingSprint = sprintRepository.findById(codSprint)
                .orElseThrow(() -> new Error("Sprint-ul cu ID-ul " + codSprint + " nu a fost găsit."));

        // Actualizează atributele necesare
        existingSprint.setNume(sprintPlanning.getNume());
        existingSprint.setObiectiv(sprintPlanning.getObiectiv());

        return sprintRepository.save(existingSprint);
    }

    // Șterge un sprint după ID
    public void removeSprint(Integer codSprint) {
        SprintPlanning existingSprint = sprintRepository.findById(codSprint)
                .orElseThrow(() -> new Error("Sprint-ul cu ID-ul " + codSprint + " nu a fost găsit."));

        sprintRepository.delete(existingSprint);
    }

    public SprintPlanning createSprint(SprintPlanning sprintPlanning) {
        // Validăm obiectul primit (opțional, dacă există cerințe suplimentare).
        if (sprintPlanning == null) {
            throw new Error("Sprint planning cannot be null.");
        }

        // Salvăm sprint-ul în baza de date utilizând repository-ul.
        return sprintRepository.save(sprintPlanning);
    }


    }


    
