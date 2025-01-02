package org.gestionare_taskuri.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.gestionare_taskuri.echipa.Angajat;

@Data
public class AngajatDTO {
    @NotNull
    private String nume;

    @NotNull
    @Email
    private String email;
@Setter
@Getter
@NotNull
    private String abilitati;
@Setter
@Getter
@NotNull
    private Angajat.Rol rol;

    public @NonNull String getNume() {
        return nume;
    }

    public void setNume(@NonNull String nume) {
        this.nume = nume;
    }

    public @NonNull String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

}