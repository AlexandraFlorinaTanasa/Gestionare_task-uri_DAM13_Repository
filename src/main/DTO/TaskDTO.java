package org.gestionare_taskuri.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data @Getter @Setter
public class TaskDTO {


    @NotNull
    private Integer cod;
      @NotBlank  private String nume;
      @NotBlank  private String descriere;
       @NotNull
       @Pattern(regexp = "^(In progres|Finalizat|Planificat)$", message = "Starea trebuie să fie 'In progres', 'Finalizat' sau 'Planificat'")
       private String stare;
     @NotNull @Min(1)  // Prioritatea trebuie să fie cel puțin 1
     @Max(5)  // Prioritatea trebuie să fie cel mult 5
     private Integer prioritate;
     @CreationTimestamp
     private LocalDate dataInceput;
     @UpdateTimestamp
     private LocalDate dataFinal;
     @UpdateTimestamp
     private LocalDate lansare;



}



