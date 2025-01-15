package org.gestionare_taskuri.task;


import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.gestionare_taskuri.echipa.Angajat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;


import java.util.List;




@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode @Getter @Setter
@Entity
@Table(name = "task", schema = "public")
public class Task {
    @Id
    @GeneratedValue
    @NotNull
    private Integer cod;
    @NotNull
    private String nume;
    @CreationTimestamp
    @NotNull
    @Past
    private LocalDate dataInceput;
    @UpdateTimestamp
    @NotNull
    @Future
    private LocalDate dataSfarsit;
    @NotNull
    private LocalDate lansare;
    @NotNull
    private String projectManagerAlocat;
    @NotNull
    private String descriere;
    @NotNull
    private String numeClient;
    @NotNull
    private String prioritate;
    @NotNull
    private Integer timpEstimat; // initial, exprimat in ore
    @NotNull
    private Integer timpRamas; // actualizat, exprimat in ore
    @NotNull
    private Integer realTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SprintPlanning> sprintPlannings = new ArrayList<>();

    // assessment
    @OneToOne
    private Angajat responsabil;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskCategory taskCategory;


    public Task(Integer cod, String nume, LocalDate dataInceput, Integer timpEstimat, LocalDate lansare) {
        super();
        this.cod = cod;
        this.nume = nume;
        this.dataInceput = dataInceput;
        this.timpEstimat = timpEstimat;
        this.lansare = lansare;
    }


    public Task(Integer cod, String nume, LocalDate dataInceput, String projectManagerAlocat, String descriere, String numeClient, String prioritate, Integer timpEstimat, TaskStatus taskStatus, TaskCategory taskCategory) {
        super();
        this.cod = cod;
        this.nume = nume;
        this.dataInceput = dataInceput;
        this.projectManagerAlocat = projectManagerAlocat;
        this.descriere = descriere;
        this.numeClient = numeClient;
        this.prioritate = prioritate;
        this.timpEstimat = timpEstimat;
        this.taskStatus = taskStatus;
        this.taskCategory = taskCategory;
    }

    public Task(Integer cod, String nume, LocalDate dataInceput, Integer timpEstimat, Angajat responsabil) {
        this.cod = cod;
        this.nume = nume;
        this.dataInceput = dataInceput;
        this.timpEstimat = timpEstimat;
        this.responsabil = responsabil;
    }

    public Task(int i, String writeDocumentation, int i1) {
    }


    public void addSprint(SprintPlanning sprintPlanning) {
        if (!this.sprintPlannings.contains(sprintPlanning)) {
            this.sprintPlannings.add(sprintPlanning);
            sprintPlanning.setTask(this);
        }
    }

    public void setTask(Task task) {

        if (task != null && !task.getSprintPlannings().contains(this)) {
            // Adăugăm această entitate în lista sprintPlannings a noului task
            task.getSprintPlannings().add((SprintPlanning) this);
        }
    }


    public void deleteSprint(SprintPlanning sprintPlanning) {
        if (!this.sprintPlannings.contains(sprintPlanning)) {
            this.sprintPlannings.remove(sprintPlanning);
            sprintPlanning.setTask(null);
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "codTask=" + cod +
                ", numeTask='" + nume + '\'' +
                ", dataInceput=" + dataInceput +
                ", projectManagerAlocat='" + projectManagerAlocat + '\'' +
                ", numeClient='" + numeClient + '\'' +
                ", prioritate='" + prioritate + '\'' +
                ", realTime=" + realTime +
                ", taskStatus=" + taskStatus +
                ", responsabil=" + responsabil +
                ", lansare=" + lansare +
                '}';
    }

    public boolean contains(Task task) {

        return false;

    }


    public Comparable<Object> getTask() {
        // Creează un obiect Task
        Task task = new Task(1, "Write documentation", 3);

        // Returnează task-ul
        return (Comparable<Object>) task;
    }
}








