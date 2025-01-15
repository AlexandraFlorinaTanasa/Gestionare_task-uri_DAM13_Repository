package org.gestionare_taskuri.echipa;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "echipa", schema = "public")
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Getter @Setter
public class Echipa {


    @Id @GeneratedValue @NotNull
    private Integer idEchipa;
    @NotNull
    private Specializare specializare;
    @NotNull  private String abilitati;
    @OneToMany(mappedBy = "echipa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Angajat> angajati = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull private TeamLeader teamLeader;

    // properties from bean accessors
    public Echipa(Integer idEchipa, Specializare specializare, String competente) {
        super();
        this.idEchipa = idEchipa;
        this.specializare = specializare;
        this.abilitati = competente;
    }
    //
    public Echipa(Integer idEchipa, Specializare specializare) {
        super();
        this.idEchipa = idEchipa;
        this.specializare = specializare;
    }
    // polimorfism parametrizare
    public void addAngajat(Angajat angajat){
        this.angajati.add(angajat);
    }

    public enum Specializare {
        BACKEND, FRONTEND, DATABASE
    }

    @Override
    public String toString() {
        return "Echipa{" +
                "idEchipa=" + idEchipa +
                ", specializare=" + specializare +
                ", abilitati='" + abilitati + '\'' +
                ", angajati=" + angajati +
                ", teamLeader=" + teamLeader +
                '}';
    }
}

