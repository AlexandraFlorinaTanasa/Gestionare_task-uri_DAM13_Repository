package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data

@NoArgsConstructor
@EqualsAndHashCode
public class Echipa {



    private Integer idEchipa;
    private Specializare specializare;
    private String abilitati;

    private List<Angajat> angajati = new ArrayList<Angajat>();

    private TeamLeader teamLeader;

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


