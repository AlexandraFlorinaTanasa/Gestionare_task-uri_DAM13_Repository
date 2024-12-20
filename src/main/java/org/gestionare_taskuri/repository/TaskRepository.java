package org.gestionare_taskuri.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.Task;
import task.TaskStatus;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByStatus(TaskStatus taskStatus); // Găsește task-uri după status.

    List<Task> findByProjectManagerId(Integer cod); // Găsește task-uri după codul managerului de proiect.

    List<Task> findByStartDateBetween(LocalDate start, LocalDate end); // Găsește task-uri după interval de date.
}
