package org.gestionare_taskuri.controller;




import org.gestionare_taskuri.servicii.SprintService;
import org.gestionare_taskuri.task.SprintPlanning;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
public class SprintController {
    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping
    public List<SprintPlanning> getAllSprints() {
        return sprintService.getAllSprints();
    }

    @GetMapping("/{id}")
    public SprintPlanning getSprintById(@PathVariable Long id) {
        return sprintService.getSprintById(id);
    }

    @PostMapping
    public SprintPlanning addSprint(@RequestBody SprintPlanning sprint) {
        return sprintService.addSprint(sprint);
    }

    @PutMapping("/{id}")
    public SprintPlanning updateSprint(@PathVariable Integer id, @RequestBody SprintPlanning sprintPlanning) {
        return sprintService.updateSprint(id, sprintPlanning);
    }

    @DeleteMapping("/{id}")
    public void deleteSprint(@PathVariable Integer id) {
        sprintService.deleteSprint(id);
    }
}
