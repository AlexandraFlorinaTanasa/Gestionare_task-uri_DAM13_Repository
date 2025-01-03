package org.gestionare_taskuri.task;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.gestionare_taskuri.echipa.Angajat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.TemporalType.DATE;

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
    @Temporal(DATE)
    @NotNull
    private Date dataInceput;
    @Temporal(DATE)
    @NotNull
    private Date dataSfarsit;
    @Temporal(DATE)
    private Date lansare;
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

    @NotNull @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SprintPlanning> sprintPlannings = new ArrayList<>();

    // assessment
    @OneToOne
    private Angajat responsabil;
    @NotNull  @Enumerated(EnumType.STRING)
    private TaskCategory taskCategory;



    public Task(Integer cod, String nume, Date dataInceput, Integer timpEstimat, Date lansare) {
        super();
        this.cod = cod;
        this.nume = nume;
        this.dataInceput = dataInceput;
        this.timpEstimat = timpEstimat;
        this.lansare = lansare;
    }




    public void setTimpRamas() {
        this.timpRamas = timpRamas;
    }


    public Task(Integer cod, String nume, Date dataInceput, String projectManagerAlocat, String descriere, String numeClient, String prioritate, Integer timpEstimat, TaskStatus taskStatus, TaskCategory taskCategory) {
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

    public Task(Integer cod, String nume, Date dataInceput, Integer timpEstimat, Angajat responsabil) {
        this.cod= cod;
        this.nume = nume;
        this.dataInceput = dataInceput;
        this.timpEstimat = timpEstimat;
        this.responsabil = responsabil;
    }

    public void adaugaSprintPlanning(SprintPlanning sprintPlanning) {
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

    public void stergeSprintPlanning(SprintPlanning sprintPlanning) {
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


}










