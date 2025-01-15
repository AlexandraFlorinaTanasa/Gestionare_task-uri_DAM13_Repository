package org.gestionare_taskuri.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.gestionare_taskuri.echipa.Angajat;
import org.gestionare_taskuri.echipa.Echipa;
import org.gestionare_taskuri.echipa.TeamLeader;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor @EqualsAndHashCode(callSuper=false)
@RequiredArgsConstructor
@Getter @Setter
public class EchipaDTO {



    @NotNull
    private Integer idEchipa;
    @NotNull
    private Echipa.Specializare specializare;
    @NotNull
    private String abilitati;
    @OneToMany(mappedBy = "echipa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Angajat> angajati = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    private TeamLeader teamLeader;

}

