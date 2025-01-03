package org.gestionare_taskuri.servicii;


import org.gestionare_taskuri.repository.TaskRepository;
import org.gestionare_taskuri.task.Task;
import org.gestionare_taskuri.task.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    // Obține task-urile după status
    public List<Task> getTasksByStatus(TaskStatus taskStatus) {
        return taskRepository.findByStatus(taskStatus);
    }

    // Obține task-urile după ID-ul managerului
    public List<Task> getTasksByManager(Integer cod) {
        return taskRepository.findByProjectManagerId(cod);
    }

    // Obține task-urile care se încadrează într-un interval de date
    public List<Task> getTasksByDateRange(Date dataInceput, Date dataSfarsit) {
        return taskRepository.findByStartDateBetween(dataInceput, dataSfarsit);
    }

    // Creează un task nou
    public Task createTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task-ul nu poate fi null.");
        }
        return taskRepository.save(task);
    }

    // Obține un task după ID
    public Task getTaskById(Integer cod) {
        return taskRepository.findById(cod)
                .orElseThrow(() -> new RuntimeException("Task-ul cu ID-ul " + cod + " nu a fost găsit."));
    }

    // Obține toate task-urile
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Adaugă un task
    public Task addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task-ul nu poate fi null.");
        }
        return taskRepository.save(task);
    }

    // Actualizează un task existent
    public Task updateTask(Integer cod, Task task) {
        Task existingTask = taskRepository.findById(cod)
                .orElseThrow(() -> new RuntimeException("Task-ul cu ID-ul " + cod + " nu a fost găsit."));

        // Actualizează task-ul cu noile date
        existingTask.setNume(task.getNume());
        existingTask.setDescriere(task.getDescriere());
        existingTask.setTaskStatus(task.getTaskStatus());
        existingTask.setDataInceput(task.getDataInceput());
        existingTask.setDataSfarsit(task.getDataSfarsit());

        return taskRepository.save(existingTask);
    }

    // Șterge un task după ID
    public void deleteTask(Integer cod) {
        if (!taskRepository.existsById(cod)) {
            throw new RuntimeException("Task-ul cu ID-ul " + cod + " nu există.");
        }
        taskRepository.deleteById(cod);
    }
}
