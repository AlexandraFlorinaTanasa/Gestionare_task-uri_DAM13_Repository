package org.gestionare_taskuri.controller;

import org.gestionare_taskuri.servicii.BacklogItemService;
import org.gestionare_taskuri.task.BacklogItem;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backlog-items")
@Validated
public class BacklogItemController {
    private final BacklogItemService backlogItemService;
    @Autowired(required = false)
    public BacklogItemController(BacklogItemService backlogItemService) {
        this.backlogItemService = backlogItemService;
    }

    @GetMapping
    public List<BacklogItem> getAllBacklogItems() {
        return backlogItemService.getAllBacklogItems();
    }

    @GetMapping("/{id}")
    public BacklogItem getBacklogItemById(@PathVariable Integer id) {
        return backlogItemService.getBacklogItemById(id);
    }

    @PostMapping
    public BacklogItem addBacklogItem(@RequestBody BacklogItem backlogItem) {
        return backlogItemService.addBacklogItem(backlogItem);
    }

    @PutMapping("/{id}")
    public BacklogItem updateBacklogItem(@PathVariable Integer id, @RequestBody BacklogItem backlogItem) {
        return backlogItemService.updateBacklogItem(id, backlogItem);
    }

    @DeleteMapping("/{id}")
    public void deleteBacklogItem(@PathVariable Integer id) {
        backlogItemService.deleteBacklogItem(id);
    }
}
