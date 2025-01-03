
package org.gestionare_taskuri.repository;


import org.gestionare_taskuri.task.BacklogItem;
import org.gestionare_taskuri.task.SprintPlanningStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BacklogItemRepository extends JpaRepository<BacklogItem, Long> {



    // Găsește toate elementele backlog asociate unui anumit sprint
    List<BacklogItem> findByCodSprint(Integer codSprint);

    // Găsește toate elementele backlog de un anumit tip sau status
    List<BacklogItem> findByStatus(SprintPlanningStatus sprintPlanningStatus);


}
