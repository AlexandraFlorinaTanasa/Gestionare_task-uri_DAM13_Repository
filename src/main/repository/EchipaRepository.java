package org.gestionare_taskuri.repository;
import org.gestionare_taskuri.echipa.Echipa;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EchipaRepository extends JpaRepository<Echipa, Integer> {


    static Echipa count() {
        return null;
    }


    List<Echipa> findById(Integer idEchipa);
    List<Echipa> findAll();
    Echipa save(Echipa echipa);

    Echipa create(Echipa echipa);

    boolean existsById(Integer echipa);

    void deleteById(Integer echipa);


}



