package test;


import org.gestionare_taskuri.repository.TaskRepository;
import org.gestionare_taskuri.servicii.TaskService;
import org.gestionare_taskuri.task.Task;
import org.gestionare_taskuri.task.TaskStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @Before
    public void setUp() {
        // Initializăm Mockito
        MockitoAnnotations.initMocks(this);

        // Creăm un obiect Task pentru teste
        task = new Task();
        task.setCod(1);
        task.setNume("Test Task");
        task.setDescriere("Descriere task");
        task.setTaskStatus(TaskStatus.TO_DO);
        task.setPrioritate("HIGH");
        task.setTimpEstimat(8);
        task.setTimpRamas(4);
        task.setRealTime(4);
    }

    @Test
    public void testCreateTask() {
        // Simulăm comportamentul repository-ului pentru salvarea unui task
        when(taskRepository.save(task)).thenReturn(task);

        // Apelăm metoda din serviciu
        Task createdTask = taskService.createTask(task);

        // Verificăm că task-ul a fost creat corect
        assertNotNull(createdTask);
        assertEquals("Test Task", createdTask.getNume());

        // Verificăm că repository-ul a fost apelat o dată
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testGetTaskById() {
        // Simulăm comportamentul repository-ului pentru a returna un task
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        // Apelăm metoda din serviciu
        Task foundTask = taskService.getTaskById(1);

        // Verificăm că task-ul a fost găsit corect
        assertNotNull(foundTask);
        assertEquals("Test Task", foundTask.getNume());

        // Verificăm că repository-ul a fost apelat o dată
        verify(taskRepository, times(1)).findById(1);
    }

    @Test
    public void testGetTaskByIdNotFound() {
        // Simulăm comportamentul repository-ului pentru a returna un Optional gol
        when(taskRepository.findById(1)).thenReturn(Optional.empty());

        // Apelăm metoda din serviciu
        Task foundTask = taskService.getTaskById(1);

        // Verificăm că task-ul nu a fost găsit
        assertNull(foundTask);

        // Verificăm că repository-ul a fost apelat o dată
        verify(taskRepository, times(1)).findById(1);
    }

    @Test
    public void testGetTasksByStatus() {
        // Creăm o listă de task-uri
        List<Task> tasks = List.of(task);

        // Simulăm comportamentul repository-ului pentru a returna task-uri cu un anumit status
        when(taskRepository.findByStatus(TaskStatus.TO_DO)).thenReturn(tasks);

        // Apelăm metoda din serviciu
        List<Task> foundTasks = taskService.getTasksByStatus(TaskStatus.TO_DO);

        // Verificăm că task-urile au fost găsite corect
        assertNotNull(foundTasks);
        assertEquals(1, foundTasks.size());
        assertEquals("Test Task", foundTasks.get(0).getNume());

        // Verificăm că repository-ul a fost apelat o dată
        verify(taskRepository, times(1)).findByStatus(TaskStatus.TO_DO);
    }

    @Test
    public void testGetTasksByManager() {
        // Creăm o listă de task-uri pentru un anumit manager
        List<Task> tasks = List.of(task);

        // Simulăm comportamentul repository-ului pentru a returna task-uri de un anumit manager
        when(taskRepository.findByProjectManagerId(1)).thenReturn(tasks);

        // Apelăm metoda din serviciu
        List<Task> foundTasks = taskService.getTasksByManager(1);

        // Verificăm că task-urile au fost găsite corect
        assertNotNull(foundTasks);
        assertEquals(1, foundTasks.size());
        assertEquals("Test Task", foundTasks.get(0).getNume());

        // Verificăm că repository-ul a fost apelat o dată
        verify(taskRepository, times(1)).findByProjectManagerId(1);
    }

    @Test
    public void testDeleteTask() {
        // Simulăm comportamentul repository-ului pentru a șterge un task
        doNothing().when(taskRepository).deleteById(1);

        // Apelăm metoda de ștergere
        taskService.deleteTask(1);

        // Verificăm că metoda delete a fost apelată o dată
        verify(taskRepository, times(1)).deleteById(1);
    }
}
