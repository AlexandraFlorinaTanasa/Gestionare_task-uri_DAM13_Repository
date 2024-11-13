package org.example.gestionare_taskuri;


import entity.Proiect;


import java.util.Collection;

public interface IProjectEntityRepository {
    public Integer getNextID() ;

    //
    public Proiect getById(Integer id);
    public Proiect get(Proiect sample);
    public Collection<Proiect> toCollection(); // getAll

    //
    public Proiect add(Proiect entity);
    public Collection<Proiect> addAll(Collection<Proiect> entities);
    public boolean remove(Proiect entity);
    public boolean removeAll(Collection<Proiect> entities);

    //
    public int size();

}
