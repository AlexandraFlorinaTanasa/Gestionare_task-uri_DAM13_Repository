package org.gestionare_taskuri.echipa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Getter @Setter
@Inheritance(strategy= InheritanceType.JOINED)
public class Angajat implements Comparable<Angajat> {
    @Id
    @GeneratedValue
    @NotNull
    private Integer id;
   @NotNull private String nume;
   @NotNull private String email;
   @NotNull private Rol rol;
   @NotNull private Double salariu;
     @NotNull private boolean disponibiil;



    public Angajat(Integer id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public Angajat(Integer id, String nume, String email, Rol rol, Double salariu, boolean disponibiil  ) {
        this.id = id;
        this.nume = nume;
        this.email = email;
        this.rol = rol;
        this.salariu = salariu;
        this.disponibiil = disponibiil;
    }
    // caz supra-incarcare
    private String abilitati;

    public Angajat(Integer id, String nume, Rol rol) {
    }

    @Override
    public int compareTo(Angajat other) {
        if (this.equals(other))
        return 0;
        return this.getId().compareTo(other.getId());
    }

    public enum Rol{
        MANAGER,  DEVELOPER, ANALYST, TESTER
    }

    @Override
    public String toString() {
        return "Angajat{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                ", rol=" + rol +
                ", salariu=" + salariu +
                ", disponibiil=" + disponibiil +
                ", abilitati='" + abilitati + '\'' +
                '}';
    }
}
