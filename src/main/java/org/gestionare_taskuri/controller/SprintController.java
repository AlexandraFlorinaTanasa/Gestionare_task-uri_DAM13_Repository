package org.gestionare_taskuri.controller;





import org.gestionare_taskuri.servicii.SprintService;
import org.gestionare_taskuri.task.SprintPlanning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
@Validated
public class SprintController {
    private final SprintService sprintService;
    @Autowired(required = false)
    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping
    public List<SprintPlanning> getAllSprints() {
        return sprintService.getAllSprints();
    }

    @GetMapping("/{codSprint}")
    public SprintPlanning getSprintById(@PathVariable Integer codSprint) {
        return sprintService.getSprintById(codSprint.longValue());
    }

    @PostMapping
    public SprintPlanning addSprint(@RequestBody SprintPlanning sprint) {
        return sprintService.addSprint(sprint);
    }

    @PutMapping("/{id}")
    public SprintPlanning updateSprint(@PathVariable Integer codSprint, @RequestBody SprintPlanning sprintPlanning) {
        return sprintService.updateSprint(codSprint, sprintPlanning);
    }

    @DeleteMapping("/{id}")
    public void deleteSprint(@PathVariable Integer codSprint) {
        sprintService.removeSprint(codSprint);
    }
}
