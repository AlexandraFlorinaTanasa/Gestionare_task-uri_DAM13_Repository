package org.example.gestionare_taskuri;

import entity.Caracteristica;
import entity.Proiect;

import java.util.List;

public interface IProjectDomainService {
    public List<Caracteristica> getCaracteristiciProiect(Proiect proiect);
    public Integer getCaracteristiciProiectCount(Proiect proiect);

    public List<Caracteristica> getCaracteristiciProiect(Integer idProiect);
    public Integer getCaracteristiciProiectCount(Integer idProiect);

    public Caracteristica getCaracteristicaProiect(Proiect proiect, String nume);


}
