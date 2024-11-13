package entity;


import org.example.gestionare_taskuri.IProjectEntityRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.logging.Logger;

//SDI Bean Component
@Repository @Scope("singleton")
public class ProjectEntityRepositoryBase implements IProjectEntityRepository {

    private static Logger logger = Logger.getLogger(ProjectEntityRepositoryBase.class.getName());

    public ProjectEntityRepositoryBase() {
        logger.info(">>> BEAN: ProjectEntityRepositoryCDI instantiated!");
    }
    //
    private Map<Integer, Proiect> entities = new HashMap<>();
    //
    private Integer nextID = 0;
    @Override
    public Integer getNextID() {
        nextID++;
        return nextID;
    }

    @Override
    public Proiect getById(Integer id) {
        return entities.get(id);
    }

    @Override
    public Proiect get(Proiect sample) {
        return getById(sample.getProiectNo());
    }

    @Override
    public Collection<Proiect> toCollection() {
        List<Proiect> projectList = new ArrayList<>();
        projectList.addAll(this.entities.values());
        return projectList;
    }

    @Override
    public Proiect add(Proiect entity) {
        if (entity.getProiectNo() == null) {
            nextID++;
            entity.setProiectNo(nextID);
        }
        entities.put(entity.getProiectNo(), entity);
        return entity;
    }

    @Override
    public Collection<Proiect> addAll(Collection<Proiect> entities) {
        for(Proiect entity: entities)
            this.add(entity);
        return entities;
    }

    @Override
    public boolean remove(Proiect entity) {
        if (this.entities.containsValue(entity)) {
            this.entities.remove(entity.getProiectNo());
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<Proiect> entities) {
        Boolean flag =  true;
        for(Proiect entity: entities) {
            if (!this.remove(entity))
                flag = false;
        }

        return flag;
    }

    @Override
    public int size() {
        return this.entities.values().size();
    }

}
