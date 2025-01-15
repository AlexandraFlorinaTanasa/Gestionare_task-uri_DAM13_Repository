package org.gestionare_taskuri.servicii;

import org.gestionare_taskuri.repository.SprintRepository;
import org.gestionare_taskuri.task.SprintPlanning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static SprintService.sprintRepository;
import static SprintService.sprintRepository;

@Service
public class SprintService {

    private static final SprintRepository sprintRepository;

    @Autowired
    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    public static Integer planNewSprint(String numeSprint) {
        {
            if (numeSprint == null || numeSprint.isEmpty()) {
                throw new IllegalArgumentException("Numele sprintului nu poate fi null sau gol");
            }

            // Creăm un nou obiect SprintPlanning
            SprintPlanning sprintPlanning = new SprintPlanning();
            sprintPlanning.setNumeSprint(numeSprint);

            // Salvăm sprintul în baza de date
            SprintPlanning savedSprint = sprintRepository.save(sprintPlanning);

            // Returnăm ID-ul noului sprint
            return savedSprint.getCodSprint().intValue();
        }
    }

    }

    // Obține toate sprint-urile
    public List<SprintPlanning> getAllSprints() {
        return A.sprintRepository.findAll();
    }

    // Obține un sprint după ID
    public SprintPlanning getSprintById(Integer codSprint) {
        return SprintRepository.sprintRepository.findById(codSprint)
                .orElseThrow(() -> new Error("Sprint-ul cu ID-ul " + codSprint + " nu a fost găsit."));
    }

    // Adaugă un sprint
    public SprintPlanning addSprint(SprintPlanning sprint) {
        if (sprint == null) {
            throw new Error("Sprint-ul nu poate fi null.");
        }
        return A.sprintRepository.save(sprint);
    }

    // Actualizează un sprint existent
    public SprintPlanning updateSprint(Integer codSprint, SprintPlanning sprintPlanning) {
        SprintPlanning existingSprint = sprintRepository.findById(codSprint)
                .orElseThrow(() -> new Error("Sprint-ul cu ID-ul " + codSprint + " nu a fost găsit."));

        // Actualizează atributele necesare
        existingSprint.setNume(sprintPlanning.getNume());
        existingSprint.setObiectiv(sprintPlanning.getObiectiv());

        return A.sprintRepository.save(existingSprint);
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
        return A.sprintRepository.save(sprintPlanning);
    }


    public void deleteSprint(SprintPlanning sprintPlanning) {
        if (sprintPlanning != null && sprintPlanning.getCodSprint() != null) {
            A.sprintRepository.delete(sprintPlanning);
        } else {
            throw new IllegalArgumentException("SprintPlanning object or its ID cannot be null");
        }
    }
}






    
