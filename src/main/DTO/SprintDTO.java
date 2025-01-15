package org.gestionare_taskuri.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.gestionare_taskuri.task.SprintPlanning;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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


    public List<SprintPlanning> toDTOList(List<SprintPlanning> sprints) {
    // Maparea listei de entități în lista de DTO-uri folosind stream
        return sprints.stream()
                .map(sprint -> ModelMapper.modelMapper.map(sprint, SprintDTO.class))
                .collect(Collectors.toList());
    }
}
