package org.gestionare_taskuri.DTO;



import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import lombok.Setter;

import java.util.List;

@Data @Getter @Setter
public class BacklogDTO {

       @NotNull
       private Integer codBacklog;
       @NotNull
       private String titlu;
       @NotNull private String descriere;
      @NotNull  private Integer sprintId; // sau referință la un SprintDTO
       @NotNull
       @Size(min = 1)
       @OneToMany
       private List<TaskDTO> taskList; // o listă de sarcini asociate backlog-ului


    }


