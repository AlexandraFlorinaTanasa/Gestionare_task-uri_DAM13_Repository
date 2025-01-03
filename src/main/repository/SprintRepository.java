package org.gestionare_taskuri.repository;


import org.gestionare_taskuri.task.SprintPlanning;
import org.gestionare_taskuri.task.SprintPlanningStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface SprintRepository extends JpaRepository<SprintPlanning, Integer> {

    // Găsește sprint-uri asociate unui anumit task
   Optional<SprintPlanning> findById(Integer codSprint);

    // Găsește sprint-uri după status
    List<SprintPlanning> findByStatus(SprintPlanningStatus status);


    void deleteByCodSprint(Integer  codSprint);
}
