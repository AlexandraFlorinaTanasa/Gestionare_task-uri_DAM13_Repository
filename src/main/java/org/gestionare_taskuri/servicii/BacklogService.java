package org.gestionare_taskuri.servicii;

import org.gestionare_taskuri.repository.BacklogRepository;
import org.gestionare_taskuri.task.Backlog;

import java.util.List;

public class BacklogService {

    private BacklogRepository backlogRepository;

    public List<Backlog> getAllBacklogs() {
        return backlogRepository.findAll();
    }


    public Backlog getBacklogById(Integer id) {
        return backlogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Backlog-ul cu ID-ul " + id + " nu a fost găsit."));
    }



    public Backlog addBacklog(Backlog backlog) {
        return backlogRepository.save(backlog);
    }

    public Backlog updateBacklog(Integer id, Backlog backlog) {
        Backlog existingBacklog = backlogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Backlog-ul cu ID-ul " + id + " nu a fost găsit."));

        // Actualizează atributele necesare
        existingBacklog.setNume(backlog.getNume());
        existingBacklog.setDescriere(backlog.getDescriere());
        // Orice alte câmpuri necesare

        return backlogRepository.save(existingBacklog);
    }

    public boolean deleteBacklog(Integer id) {
        if (!backlogRepository.existsById(id)) {
            throw new RuntimeException("Backlog-ul cu ID-ul " + id + " nu a fost găsit.");
        }
        backlogRepository.deleteById(id);
        return false;
    }
    }

