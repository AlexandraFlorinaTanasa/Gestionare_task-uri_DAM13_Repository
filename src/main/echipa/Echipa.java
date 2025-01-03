package org.gestionare_taskuri.echipa;



import jakarta.persistence.*;
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


    @Id @GeneratedValue @NonNull
    private Integer idEchipa;
    @NonNull private Specializare specializare;
    @NonNull  private String abilitati;
    @OneToMany(mappedBy = "echipa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Angajat> angajati = new ArrayList<Angajat>();

    @OneToOne(cascade = CascadeType.ALL)
    @NonNull private TeamLeader teamLeader;

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
        BACKEND, FRONTEND, DATABASE;
    }
}
