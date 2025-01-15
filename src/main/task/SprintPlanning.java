package org.gestionare_taskuri.task;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@Getter @Setter @Data @EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="sprintplanning", schema="public")
public class SprintPlanning extends Task {


    @Id
    @GeneratedValue
    @NotNull
    private Integer codSprint;
    @NotNull
    private String numeSprint;
    @NotNull
    private String obiectiv;
    @NotNull
    @Enumerated(EnumType.STRING)
    private SprintPlanningStatus sprintPlanningStatus; // ready, active,on hold,completed, cancelled


    public SprintPlanning(Integer codSprint, String numeSprint, String obiectiv, SprintPlanningStatus sprintPlanningStatus) {
        super();
        this.codSprint = codSprint;
        this.numeSprint = numeSprint;
        this.obiectiv = obiectiv;
        this.sprintPlanningStatus = sprintPlanningStatus;
    }

    @Override
    public String toString() {
        return "SprintPlanning{" +
                "codSprint=" + codSprint +
                ", numeSprint='" + numeSprint + '\'' +
                ", obiectiv='" + obiectiv + '\'' +
                ", status=" + sprintPlanningStatus +
                '}';
    }


}



