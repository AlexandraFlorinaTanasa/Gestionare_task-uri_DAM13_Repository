package org.gestionare_taskuri.echipa;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Data @NoArgsConstructor
@Getter @Setter   @EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "teamleader", schema = "public")
public class TeamLeader extends Angajat {

    private String abilitatiTehnice; // JEE, Spring, .NET, JS/Node.js, Ruby_Rails

    private String abilitatiManageriale;
    public TeamLeader(Integer id, String name,
                      String abilitatiTehnice) {
        super(id, name, Rol.MANAGER);
        this.abilitatiTehnice = abilitatiTehnice;
    }

    // Polimorfism
    @Override
    public void setAbilitati(String abilitati) {
        this.setAbilitatiTehnice(abilitati);
    }

    // Supraincarcare
    public void setAbilitati(String abilitati, ProjectManager.TipAbilitati type) {
        if (type.equals(ProjectManager.TipAbilitati.MANAGERIALE)) {
            setAbilitatiManageriale(abilitati);  // Setează abilități manageriale
        } else if (type.equals(TipAbilitati.TEHNICE)) {
            setAbilitatiTehnice(abilitati);  // Setează abilități tehnice
        }
    }
    public enum TipAbilitati {MANAGERIALE, TEHNICE}
}
