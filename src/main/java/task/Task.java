package task;

import echipa.Angajat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.*;

import static jakarta.persistence.TemporalType.DATE;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode @Getter @Setter
@Entity
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
    @Temporal(DATE) @NotNull
    private Integer lansare;
     @NotNull
    private String projectManagerAlocat;
    @NotNull
    private String observatii;
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
    private TaskStatus taskStatus;
    @OneToMany
    private List<SprintPlanning> sprintPlannings = new ArrayList<>();

    // assessment
    @OneToOne
    private Angajat responsabil;

    private TaskCategory taskCategory;


    public Task(Integer cod, String nume, Date dataInceput, Integer timpEstimat, Integer lansare) {
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


    public Task(Integer cod, String nume, Date dataInceput, String projectManagerAlocat, String observatii, String numeClient, String prioritate, Integer timpEstimat, TaskStatus taskStatus, TaskCategory taskCategory) {
        super();
        this.cod = cod;
        this.nume = nume;
        this.dataInceput = dataInceput;
        this.projectManagerAlocat = projectManagerAlocat;
        this.observatii = observatii;
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

    private void adaugaSprintPlanning(SprintPlanning sprintPlanning) {
        if (!this.sprintPlannings.contains(sprintPlanning)) {
            this.sprintPlannings.add(sprintPlanning);

        }
    }

    private void stergeSprintPlanning(SprintPlanning sprintPlanning) {
        if (!this.sprintPlannings.contains(sprintPlanning)) {
            this.sprintPlannings.remove(sprintPlanning);

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










