package org.gestionare_taskuri.repository;

import org.gestionare_taskuri.task.Backlog;
import org.gestionare_taskuri.task.SprintPlanningStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Integer> {

    // Găsește backlog-uri după numele lor
    List<Backlog> findByNume(String nume);

    // Găsește backlog-uri după status
    List<Backlog> findByStatus(SprintPlanningStatus status);

    // Găsește backlog-uri care conțin un anumit text în descriere
    List<Backlog> findByDescriptionContaining(String descriptionPart);
}
