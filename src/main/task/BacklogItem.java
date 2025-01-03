package org.gestionare_taskuri.task;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter @Setter @Data @NoArgsConstructor
@EqualsAndHashCode
@Table(name = "backlogitem", schema = "public")
public class BacklogItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @NotNull
    private int id;
  @Getter
  @Setter
  @NotNull  private String nume;
  @Getter
  @Setter
  @NotNull  private String descriere;
   @NotNull @Enumerated(EnumType.STRING)
   private Tip tip;
   @Enumerated(EnumType.STRING)
   private String prioritate; //LOW, MEDIUM, HIGH
   @NotNull  @Enumerated(EnumType.STRING)
   private Status status;

   // @Setter
   // @ManyToOne
    //private Backlog backlog;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrioritate(String prioritate) {
   this.prioritate = String.valueOf(prioritate.valueOf(prioritate)); }

    public void setStatus(String status) {
     this.status = Status.valueOf(status); }


    public enum Tip {
        USER_STORY, TASK, BUG
    }

    public enum Prioritate {
        HIGH, MEDIUM, LOW
    }

    public enum Status {
        TO_DO, IN_PROGRESS, DONE
    }


    public void addItem(BacklogItem item) {
        item.add(item);       // Adaugă elementul în lista de items

    }

    private void add(BacklogItem item) {
    }

    public void removeItem(BacklogItem item) {
        item.remove(item);

    }

    private void remove(BacklogItem item) {
    }


}
