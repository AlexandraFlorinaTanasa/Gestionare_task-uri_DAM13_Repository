package org.gestionare_taskuri.echipa;


import jakarta.persistence.Entity;
import lombok.*;

@Data @NoArgsConstructor
@Getter @Setter   @EqualsAndHashCode(callSuper = false)
@Entity
public class TeamLeader extends Angajat {

    private String abilitatiTehnice;// JEE, Spring, .NET, JS/Node.js, Ruby_Rails
    private String abilitatiManageriale;
    //
    public TeamLeader(Integer id, String name,
                      String abilitatiTehnice) {
        super(id, name, Rol.MANAGER);
        this.abilitatiTehnice = abilitatiTehnice;
    }



    // Supraincarcare
    public void setAbilitati(String abilitati, ProjectManager.TipAbilitati type) {
        if (type.equals(ProjectManager.TipAbilitati.MANAGERIALE))
            super.setAbilitati(abilitati);

        if (type.equals(TipAbilitati.TEHNICE))
            setAbilitatiTehnice(abilitati);

    }
    public enum TipAbilitati {MANAGERIALE, TEHNICE}
}
