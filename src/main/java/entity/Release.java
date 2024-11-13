package entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Release implements Serializable{
    /* internal member fields*/
    @EqualsAndHashCode.Include
    private Integer releaseId;
    private String codeName; // NEW born
    private String indicativ; // vers 1.1
    private String descriere;

    private Date dataPublicare; // dataEstimarePublicare

    private Proiect proiect;

    private List<Caracteristica> caracteristici = new ArrayList<>();

    /* constructors*/
    public Release(Integer releaseId, String codeName, String indicativ,
                   String descriere, Date dataPublicare, Proiect proiect) {
        super();
        this.releaseId = releaseId;
        this.codeName = codeName;
        this.indicativ = indicativ;
        this.descriere = descriere;
        this.dataPublicare = dataPublicare;
        this.proiect = proiect;
    }

    public Release(Integer releaseId, String codeName, Date dataPublicare,
                   Proiect proiect) {
        super();
        this.releaseId = releaseId;
        this.codeName = codeName;
        this.dataPublicare = dataPublicare;
        this.proiect = proiect;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((releaseId == null) ? 0 : releaseId.hashCode());
        return result;
    }

    public void addCaracteristica(String caracteristica) {
        Integer caracteristicaNo = this.caracteristici.size() + 1;
        Integer idCaracteristica = Integer.valueOf(this.releaseId.toString() + caracteristicaNo);
        this.caracteristici.add(new Caracteristica(idCaracteristica, caracteristica));
    }

    public void addCaracteristica(String caracteristica, String descriere) {
        //Integer idCaracteristica = (this.caracteristici.size() == 0) ? 1 : this.caracteristici.size();
        Integer caracteristicaNo = this.caracteristici.size() + 1;
        Integer idCaracteristica = Integer.valueOf(this.releaseId.toString() + caracteristicaNo);
        this.caracteristici.add(new Caracteristica(null, caracteristica, descriere));
    }
    //
    @Override
    public String toString() {
        return "\n\tRelease [releaseId=" + releaseId + ", codeName=" + codeName
                + ", indicativ=" + indicativ + ", caracteristici: " + caracteristici + "]";
    }
}