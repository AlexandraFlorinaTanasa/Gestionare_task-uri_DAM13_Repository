package entity;

    import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

    @Data @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    @NoArgsConstructor
    public class Proiect implements Serializable, Comparable<Proiect>{
        /* internal stucture: member fields*/
        @Min(1)
        @NotNull(message = "ProiectNo is required!")
        @EqualsAndHashCode.Include
        private Integer proiectNo;

        @NotNull(message = "Proiect Name is required!")
        @Size(min=1, message = "Proiect must have an explicit name!")
        private String nume;

        @NotNull(message = "DataInceput is required!")
        @Future(message = "DataInceput must be a future date!")
        private Date dataInceput;

        private ProjectManager projectManager;

        private List<Release> releases = new ArrayList<>();

        private Release currentRelease;

        protected Integer releaseCount = 0;
        protected Integer featureCount = 0;

        /* Constructors */
        public Proiect(Integer proiectNo, String nume, Date dataInceput) {
            super();
            this.proiectNo = proiectNo;
            this.nume = nume;
            this.dataInceput= dataInceput;
        }

        public Proiect(Integer nrProiect, String numeProiect) {
            super();
            this.proiectNo = nrProiect;
            this.nume = numeProiect;
        }

        @Override
        public int compareTo(Proiect obj) {
            return this.proiectNo.compareTo(((Proiect)obj).getProiectNo());
        }

        @AssertTrue(message = "Release List must not be empty!")
        public boolean isValid() {
            if (this.releases == null || this.releases.isEmpty())
                return false;
            return true;
        }
        //
        @Override
        public String toString() {
            return "\nProject [proiectNo=" + proiectNo + ", nume=" + nume + ", dataInceput=" + dataInceput + ", releases="
                    + releases + "]";
        }
    }




