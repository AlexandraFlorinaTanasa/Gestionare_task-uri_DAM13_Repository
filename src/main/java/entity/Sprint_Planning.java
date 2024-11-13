package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter

public class Sprint_Planning extends Task{


    private Integer codSprint;
    private String numeSprint;
    private String obiectiv;
    private String status; // ready, active,on hold,completed, cancelled



        public Sprint_Planning(Integer codSprint, String numeSprint, String obiectiv, String status) {
            super();
            this.codSprint = codSprint;
            this.numeSprint=numeSprint;
            this.obiectiv = obiectiv;
            this.status = status;
        }
    }



