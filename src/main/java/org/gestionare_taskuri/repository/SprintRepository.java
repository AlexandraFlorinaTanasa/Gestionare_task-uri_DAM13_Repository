package org.gestionare_taskuri.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.SprintPlanning;
import task.SprintPlanningStatus;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<SprintPlanning, Integer> {

    // Găsește sprint-uri asociate unui anumit task
    List<SprintPlanning> findById(Integer cod);

    // Găsește sprint-uri după status
    List<SprintPlanning> findByStatus(SprintPlanningStatus sprintPlanningStatus);

    // Găsește sprint-uri cu data de început într-un anumit interval
    List<SprintPlanning> findByStartDateBetween(LocalDate start, LocalDate end);

    // Găsește sprint-uri finalizate (status = "completed")
    List<SprintPlanning> findByStatusOrderByEndDateDesc(SprintPlanningStatus sprintPlanningStatus);
}
