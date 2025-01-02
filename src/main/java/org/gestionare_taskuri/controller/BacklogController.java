package org.gestionare_taskuri.controller;



import jakarta.validation.Valid;
import org.gestionare_taskuri.servicii.BacklogService;
import org.gestionare_taskuri.task.Backlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/backlogs")
@Validated  // Enables validation for the controller's input
public class BacklogController {

    private final BacklogService backlogService;

    @Autowired(required = false)
    public BacklogController(BacklogService backlogService) {
        this.backlogService = backlogService;
    }

    @GetMapping
    public ResponseEntity<List<Backlog>> getAllBacklogs() {
        List<Backlog> backlogs = backlogService.getAllBacklogs();
        return ResponseEntity.ok(backlogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Backlog> getBacklogById(@PathVariable Integer id) {
        Backlog backlog = backlogService.getBacklogById(id);
        if (backlog == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(backlog);
    }

    @PostMapping
    public ResponseEntity<Backlog> addBacklog(@Valid @RequestBody Backlog backlog) {
        Backlog createdBacklog = backlogService.addBacklog(backlog);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBacklog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Backlog> updateBacklog(@PathVariable Integer id, @Valid @RequestBody Backlog backlog) {
        Backlog updatedBacklog = backlogService.updateBacklog(id, backlog);
        if (updatedBacklog == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedBacklog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBacklog(@PathVariable Integer id) {
        boolean isDeleted = backlogService.deleteBacklog(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleGenericException(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Eroare: " + e.getMessage());
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<String> handleBadRequest(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}


