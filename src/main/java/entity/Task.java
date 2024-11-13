package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Task {

    private Integer codTask;
    private String numeTask;
    private String status; // to do,ready,in progress, blocked,review,complete, cancelled
    private Date dataInceput;
    private Date dataSfarsit;
    private String projectManagerAlocat;
    private String observatii;
    private String numeClient;
    private String prioritate;
    private Integer timpEstimat; // initial, exprimat in ore
    private Integer timpRamas; // actualizat, exprimat in ore
    private Integer realTime;

    private TaskStatus taskStatus;


    // assessment
    private Angajat responsabil;

    private TaskCategory taskCategory;

    // Burn down
    private Map<Date, Integer> burnDownRecords = new HashMap<>();

    public Task(Integer codTask, String numeTask, Date dataInceput, Integer timpEstimat) {
        super();
        this.codTask = codTask;
        this.numeTask = numeTask;
        this.dataInceput = dataInceput;
        this.timpEstimat = timpEstimat;
    }


    public void setTimpRamas(Integer timpRamas) {
        this.timpRamas = timpRamas;
        burnDownRecords.put(new Date(), timpRamas);
    }


    public Task(Integer codTask, String numeTask, String status, Date dataInceput, String projectManagerAlocat, String observatii, String numeClient, String prioritate, Integer timpEstimat, TaskStatus taskStatus, TaskCategory taskCategory) {
        super();
        this.codTask = codTask;
        this.numeTask = numeTask;
        this.status = status;
        this.dataInceput = dataInceput;
        this.projectManagerAlocat = projectManagerAlocat;
        this.observatii = observatii;
        this.numeClient = numeClient;
        this.prioritate = prioritate;
        this.timpEstimat = timpEstimat;
        this.taskStatus = taskStatus;
        this.taskCategory = taskCategory;
    }

    public Task(Integer codTask, String numeTask, Date dataInceput, Integer timpEstimat, Angajat responsabil) {
        this.codTask = codTask;
        this.numeTask = numeTask;
        this.dataInceput = dataInceput;
        this.timpEstimat = timpEstimat;
        this.responsabil = responsabil;
    }
}












