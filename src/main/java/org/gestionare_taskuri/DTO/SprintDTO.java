package org.gestionare_taskuri.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter @Setter
public class SprintDTO {

        @NotNull private Integer codSprint;
    @NotBlank
    private String nume;
       @NotNull @PastOrPresent
       private Date dataIncepere;
        @NotNull @Future
        private Date dataFinalizare;
       @NotBlank private String descriere;



}
