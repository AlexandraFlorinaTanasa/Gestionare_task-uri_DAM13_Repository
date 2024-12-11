package org.gestionare_taskuri.servicii;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.Task;
import task.TaskStatus;

import java.time.LocalDate;
import java.util.List;

@Service
    public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasksByStatus(TaskStatus taskStatus) {
        return taskRepository.findByStatus(taskStatus);
    }

    public List<Task> getTasksByManager(Integer cod) {
        return taskRepository.findByProjectManagerId(cod);
    }

    public List<Task> getTasksByDateRange(LocalDate start, LocalDate end) {
        return taskRepository.findByStartDateBetween(start, end);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task); // Salvarea unui task
    }


    public Task getTaskById(Integer cod) {
        return taskRepository.findById(cod).orElseThrow();
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll(); // Returnează toate task-urile
    }

}



