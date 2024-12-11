package org.gestionare_taskuri.controller;



import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backlogs")
public class BacklogController {
    private final BacklogService backlogService;

    public BacklogController(BacklogService backlogService) {
        this.backlogService = backlogService;
    }

    @GetMapping
    public List<Backlog> getAllBacklogs() {
        return backlogService.getAllBacklogs();
    }

    @GetMapping("/{id}")
    public Backlog getBacklogById(@PathVariable Long id) {
        return backlogService.getBacklogById(id);
    }

    @PostMapping
    public Backlog addBacklog(@RequestBody Backlog backlog) {
        return backlogService.addBacklog(backlog);
    }

    @PutMapping("/{id}")
    public Backlog updateBacklog(@PathVariable Long id, @RequestBody Backlog backlog) {
        return backlogService.updateBacklog(id, backlog);
    }

    @DeleteMapping("/{id}")
    public void deleteBacklog(@PathVariable Long id) {
        backlogService.deleteBacklog(id);
    }
}
