import org.example.gestionare_taskuri.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import servicii.TaskService;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

public class TaskServiceTest {

    private TaskRepository taskRepository;


    private TaskService taskService;

    @Test
    public void testCreateTask() {
        Task task = new Task();
        task.setNume("New Task");

        Task result = taskService.createTask(task);

        assertEquals("New Task", result.getNume());

    }
}
