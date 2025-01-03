package org.gestionare_taskuri.controller;



import org.gestionare_taskuri.servicii.TaskService;
import org.gestionare_taskuri.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Validated
public class TaskController {
    private final TaskService taskService;
@Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{cod}")
    public Task getTaskById(@PathVariable Integer cod) {
        return taskService.getTaskById(cod);
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @PutMapping("/{cod}")
    public Task updateTask(@PathVariable Integer cod, @RequestBody Task task) {
        return taskService.updateTask(cod, task);
    }

    @DeleteMapping("/{cod}")
    public void deleteTask(@PathVariable Integer cod) {
        taskService.deleteTask(cod);
    }
}
