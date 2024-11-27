package org.example.gestionare_taskuri;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import task.Task;
import task.TaskStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
public class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testFindByStatus() {
        Task task = new Task();
        task.setNume("Test Task");
        task.setTaskStatus(TaskStatus.IN_PROGRESS);
        taskRepository.save(task);

        List<Task> tasks = taskRepository.findByStatus(TaskStatus.IN_PROGRESS);
        assertFalse(tasks.isEmpty());
        assertEquals("Test Task", tasks.get(0).getNume());
    }
}
