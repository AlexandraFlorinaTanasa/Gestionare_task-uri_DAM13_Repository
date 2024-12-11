package org.gestionare_taskuri.task;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class BacklogItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @NotNull
    private int id;
  @NotNull  private String nume;
  @NotNull  private String descriere;
   @NotNull private String tip;
   @NotNull private String prioritate; //LOW, MEDIUM, HIGH
   @NotNull private String status;

    @ManyToOne
    private Backlog backlog;

    public enum Tip {
        USER_STORY, TASK, BUG;
    }

    public enum Prioritate {
        HIGH, MEDIUM, LOW;
    }

    public enum Status {
        TO_DO, IN_PROGRESS, DONE;
    }


    public void addItem(BacklogItem item) {
        items.add(item);
        item.setBacklog(this);
    }

    public void removeItem(BacklogItem item) {
        items.remove(item);
        item.setBacklog(null);
    }

}
