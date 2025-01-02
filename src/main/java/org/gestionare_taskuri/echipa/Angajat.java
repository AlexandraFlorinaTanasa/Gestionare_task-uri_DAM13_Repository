package org.gestionare_taskuri.echipa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "angajat", schema = "public")
@Getter @Setter
@Inheritance(strategy= InheritanceType.JOINED)
public class Angajat implements Comparable<Angajat> {
    @Id
    @GeneratedValue
     @NotNull
    private Integer id;
   @NotBlank
   private String nume;
   @NotBlank  private String email;
    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(nullable = false)
   private Rol rol;

    @Setter
    @Column(nullable = false)
    private boolean isActive = true; // implicit activ


    public Angajat(Integer id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public Angajat(Integer id, String nume, String email, Rol rol) {
        this.id = id;
        this.nume = nume;
        this.email = email;
        this.rol = rol;
    }
    // caz supra-incarcare
    @NotBlank  @Column(nullable = false)
    private String abilitati;

    public Angajat(Integer id, String nume, Rol rol) {
        this.id = id;
        this.nume = nume;
        this.rol = rol;
    }

    @Override
    public int compareTo(Angajat other) {
        if (this==other){
        return 0;}
        return this.getId().compareTo(other.getId());
    }


    public enum Rol{
        MANAGER,  DEVELOPER, ANALYST, TESTER;
    }
}
