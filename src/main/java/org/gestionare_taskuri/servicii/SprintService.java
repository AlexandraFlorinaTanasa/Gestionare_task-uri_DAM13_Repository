package org.gestionare_taskuri.servicii;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.SprintPlanning;
import task.SprintPlanningStatus;

import java.time.LocalDate;
import java.util.List;

import static task.SprintPlanningStatus.COMPLETED;

@Service
public class SprintService {

    @Autowired
    private SprintRepository sprintRepository;

    public List<SprintPlanning> getSprintsById(Integer cod) {
        return sprintRepository.findById(cod);
    }

    public List<SprintPlanning> getSprintsByStatus(SprintPlanningStatus sprintPlanningStatus) {
        return sprintRepository.findByStatus(sprintPlanningStatus);
    }

    public List<SprintPlanning> getSprintsByDateRange(LocalDate start, LocalDate end) {
        return sprintRepository.findByStartDateBetween(start, end);
    }

    public List<SprintPlanning> getCompletedSprints() {
        return sprintRepository.findByStatusOrderByEndDateDesc(COMPLETED);
    }

    public SprintPlanning createSprint(SprintPlanning sprintPlanning) {
        return sprintRepository.save(sprintPlanning); // Salvarea unui sprint
    }

    public void deleteSprint(SprintPlanning sprintPlanning) {
        sprintRepository.delete(sprintPlanning);
    }

    //public Optional<SprintPlanning> getSprintPlanningById(Integer codSprint) {
    }

    // public Optional<SprintPlanning> getSprintPlanningById(SprintPlanning sprintPlanning) {

   // }
//}
