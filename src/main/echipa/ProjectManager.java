package org.gestionare_taskuri.echipa;


import jakarta.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity @Getter @Setter  @EqualsAndHashCode(callSuper=false)

public class ProjectManager extends Angajat {

    private Integer experientaManageriala; // in ani
    private String abilitatiManageriale; // agile, cascade, RUP

    public ProjectManager() {
        super();
        super.setRol(Rol.MANAGER);
    }
    //
    public ProjectManager(Integer id, String nume,
                          Integer experientaManageriala, String abilitatiManageriale) {
        super(id, nume, Rol.MANAGER);
        this.experientaManageriala = experientaManageriala;
        this.abilitatiManageriale = abilitatiManageriale;
    }


    // caz supra-incarcare
    public String getAbilitati(TipAbilitati type) {
        if (TipAbilitati.MANAGERIALE.equals(type))
            return "manageriale: " + abilitatiManageriale;
        if (TipAbilitati.TEHNICE.equals(type))
            return "tehnice: " + this.getAbilitati();
        return null;
    }

    // caz supra-scriere
    @Override
    public void setRol( Rol rol) {
        throw new Error("Proprietatea rol nu poate fi schimbata!");
    }

    public enum TipAbilitati
    {MANAGERIALE, TEHNICE}
}