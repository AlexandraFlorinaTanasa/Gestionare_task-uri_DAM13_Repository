package org.gestionare_taskuri.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Getter @Setter
public class TaskDTO {


    @NotNull
    private Integer cod;
      @NotBlank  private String titlu;
      @NotBlank  private String descriere;
       @NotNull
       @Pattern(regexp = "^(In progres|Finalizat|Planificat)$", message = "Starea trebuie să fie 'In progres', 'Finalizat' sau 'Planificat'")
       private String stare;
     @NotNull @Min(1)  // Prioritatea trebuie să fie cel puțin 1
     @Max(5)  // Prioritatea trebuie să fie cel mult 5
     private Integer prioritate;



}



