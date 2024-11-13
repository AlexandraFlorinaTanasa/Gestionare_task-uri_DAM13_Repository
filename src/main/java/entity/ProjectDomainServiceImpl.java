package entity;

import org.example.gestionare_taskuri.IProjectDomainService;
import org.example.gestionare_taskuri.IProjectEntityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// SDI Bean Component
//@Component("ProjectDomainService")
@Service
public class ProjectDomainServiceImpl implements IProjectDomainService {

    @Override
    public List<Caracteristica> getCaracteristiciProiect(Proiect proiect) {
        List<Caracteristica> caracteristiciProiect = new ArrayList<>();
        for (Release r : proiect.getReleases())
            caracteristiciProiect.addAll(r.getCaracteristici());
        return caracteristiciProiect;
    }

    @Override
    public Integer getCaracteristiciProiectCount(Proiect proiect) {
        List<Caracteristica> caracteristiciProiect = getCaracteristiciProiect(proiect);
        return caracteristiciProiect.size();
    }

    @Override
    public List<Caracteristica> getCaracteristiciProiect(Integer projectID) {
        Integer proiectID = null;
        Proiect proiect = entityRepository.getById(proiectID);
        return getCaracteristiciProiect(proiect);
    }

    @Override
    public Integer getCaracteristiciProiectCount(Integer projectID) {
        Integer proiectID=null;
        Proiect proiect = entityRepository.getById(proiectID);
        return getCaracteristiciProiectCount(proiect);
    }

    // Dependency
    @Autowired
    private IProjectEntityRepository entityRepository;

    //@Override
    public void setProjectEntityRepository(IProjectEntityRepository repository) {
        this.entityRepository = repository;
    }

    public ProjectDomainServiceImpl() {
        super();
    }

    public ProjectDomainServiceImpl(IProjectEntityRepository entityRepository) {
        super();
        this.entityRepository = entityRepository;
    }

    @Override
    public Caracteristica getCaracteristicaProiect(Proiect proiect, String nume) {
        List<Caracteristica> caracteristiciProiect = getCaracteristiciProiect(proiect);
        List<Caracteristica> caracteristici = caracteristiciProiect.stream().filter(f -> f.getNume().equals(nume)).collect(Collectors.toList());
        if (caracteristici.size() > 0)
            return caracteristici.get(0);

        return null;
    }

    public IProjectEntityRepository getEntityRepository() {
        return entityRepository;
    }

}
