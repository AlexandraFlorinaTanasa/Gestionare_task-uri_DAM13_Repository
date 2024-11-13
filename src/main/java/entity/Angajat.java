package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor

public class Angajat
        implements Comparable<Angajat> {
    private Integer id;
    private String nume;
    private String email;
    private Rol rol;

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
        MANAGER,  DEVELOPER, ANALYST, TESTER;
    }
}
