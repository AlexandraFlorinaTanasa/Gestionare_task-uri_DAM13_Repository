package org.gestionare_taskuri.task;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "backlog", schema = "public")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Backlog  {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
private int id;
@NotNull private String nume;
@NotNull private String descriere; // scop/context backlog
@NotNull private String proprietar;
    @OneToMany(mappedBy = "backlog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BacklogItem> items = new ArrayList<>();
    @CreationTimestamp
    private LocalDate creatLa;
    @UpdateTimestamp
    private LocalDate actualizatLa;




    public void addItem(BacklogItem item) {
        items.add(item);

    }

    public void removeItem(BacklogItem item) {
        items.remove(item);

    }

    @Override
    public String toString() {
        return "Backlog{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", descriere='" + descriere + '\'' +
                ", proprietar='" + proprietar + '\'' +
                ", items=" + items +
                ", creatLa=" + creatLa +
                ", actualizatLa=" + actualizatLa +
                '}';
    }
}
