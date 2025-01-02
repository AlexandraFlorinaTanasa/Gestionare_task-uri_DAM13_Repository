package org.gestionare_taskuri.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Getter @Setter
public class BacklogItemDTO {

        @NotNull private Integer codBacklogItem;
       @NotNull @NotBlank
       private String titlu;
      @NotNull @NotBlank
      private String descriere;
      @NotNull
      @Min(1)  // Prioritatea trebuie să fie cel puțin 1
      @Max(5)  // Prioritatea trebuie să fie cel mult 5
      private Integer prioritate;

       @NotNull @NotBlank private String status;  // De exemplu: "In progres", "Finalizat", "Planificat"
      @NotNull @Min(1)  // Estimarea în ore trebuie să fie cel puțin 1
      private Integer estimareOre;  // Estimarea în ore a realizării itemului
    @NotNull    private Integer sprintId; // (opțional) Referință la un sprint specific

}
