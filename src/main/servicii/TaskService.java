package org.gestionare_taskuri.servicii;



import org.gestionare_taskuri.repository.TaskRepository;
import org.gestionare_taskuri.task.Task;
import org.gestionare_taskuri.task.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public static Integer planNewTask(String nume, String startDateFromString) {
        // Definirea formatului pentru data
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;

        try {
            // Convertirea șirului de caractere într-un obiect Date
            startDate = dateFormat.parse(startDateFromString);
        } catch (Exception e) {
            // Tratarea erorilor de conversie a datei
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }

        // Crearea unui task nou
        Task newTask = new Task();
        newTask.setName(nume);
        newTask.setStartDate(startDate);

        // Salvarea task-ului în repository
        Task savedTask = taskRepository.save(newTask);

        // Returnarea ID-ului task-ului
        return savedTask.getId();
    }
}

// Obține task-urile după status
public List<Task> getTasksByStatus(TaskStatus taskStatus) {
    return taskRepository.findByStatus(taskStatus);
}

// Obține task-urile după ID-ul managerului
public List<Task> getTasksByManager(Integer cod) {
    return taskRepository.findByProjectManagerId(cod);
}

// Obține task-urile care se încadrează într-un interval de date
public List<Task> getTasksByDateRange(Date dataInceput, LocalDate dataSfarsit) {
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

// Șterge un task după cod
public void deleteTask(Task cod) {
    if (!taskRepository.existsByCod(cod)) {
        throw new RuntimeException("Task-ul cu codul " + cod + " nu există.");
    }
    taskRepository.deleteByCod(cod);
}

public Integer createWorkflowTask() {


    // Crearea unui task nou
    Task newTask = new Task();

    // Setarea unor proprietăți pentru task-ul nou (de exemplu, un nume și o descriere)
    newTask.setNume("New Workflow Task");
    newTask.setDescriere("This is a new task created in the workflow.");

    // Salvarea task-ului într-un repository (presupunem că există un repository pentru entitățile Task)
    Task savedTask = taskRepository.save(newTask);

    // Returnarea ID-ului task-ului creat
    return savedTask.getCod();
}
}

