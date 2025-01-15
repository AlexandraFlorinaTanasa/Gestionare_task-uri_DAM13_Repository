package org.gestionare_taskuri.repository;


import org.gestionare_taskuri.task.Task;
import org.gestionare_taskuri.task.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByStatus(TaskStatus taskStatus); // Găsește task-uri după status.

    List<Task> findByProjectManagerId(Integer cod); // Găsește task-uri după codul managerului de proiect.

    List<Task> findByStartDate(Date dataInceput); // Găsește task-uri după data.

    List<Task> findByStartDateBetween(Date dataInceput, LocalDate dataSfarsit);

    Task findByTask(Integer cod);

    boolean existsByCod(Task cod);

    void deleteByCod(Task cod);
}
