package org.gestionare_taskuri.echipa;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "projectmanager", schema = "public")
@Getter @Setter  @EqualsAndHashCode(callSuper = true)

public class ProjectManager extends Angajat {

    private Integer experientaManageriala; // in ani
    private String abilitatiManageriale; // agile, cascade, RUP

    public ProjectManager() {
        super();
        super.setRol(Rol.MANAGER);
    }

    public ProjectManager(Integer id, String nume,
                          Integer experientaManageriala, String abilitatiManageriale) {
        super(id, nume, Rol.MANAGER);
        this.experientaManageriala = experientaManageriala;
        this.abilitatiManageriale = abilitatiManageriale;
    }


    // caz supra-incarcare
    public String getAbilitati(TeamLeader.TipAbilitati type) {
        if (TeamLeader.TipAbilitati.MANAGERIALE.equals(type)) {
            return "manageriale: " + abilitatiManageriale;
        }
        if (TeamLeader.TipAbilitati.TEHNICE.equals(type)) {
            return "tehnice: " + super.getAbilitati();
        }
        return "Tip de abilitate necunoscut";
    }

    // caz supra-scriere
    @Override
    public void setRol(Rol rol) {
        throw new Error("Rolul unui ProjectManager nu poate fi schimbat!");
    }

    public enum TipAbilitati
    {MANAGERIALE, TEHNICE};
}

