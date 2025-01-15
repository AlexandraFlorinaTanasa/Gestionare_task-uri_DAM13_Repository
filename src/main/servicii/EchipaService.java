package org.gestionare_taskuri.servicii;
import org.gestionare_taskuri.echipa.Echipa;
import org.gestionare_taskuri.repository.EchipaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EchipaService {

    @Autowired
    private EchipaRepository echipaRepository;

    // Returnează toate echipele
    public List<Echipa> getAllEchipe() {
        return echipaRepository.findAll();
    }

    // Salvează o echipă
    public Echipa saveEchipa(Echipa echipa) {
        return echipaRepository.save(echipa);
    }

    // Creează o echipă nouă
    public Echipa createdEchipa(Echipa echipa) {
        return echipaRepository.save(echipa);
    }

    // Găsește o echipă după ID
    public Echipa getEchipaById(Integer idEchipa) {
        if (!echipaRepository.existsById(idEchipa)) {
            throw new NoSuchElementException("Echipa nu a fost găsită cu ID-ul: " + idEchipa);
        }
        return (Echipa) echipaRepository.findById(idEchipa);
    }
    // Șterge o echipă după ID
    public void deleteEchipa(Integer idEchipa) {
        if (!echipaRepository.existsById(idEchipa)) {
            throw new NoSuchElementException("Echipa nu a fost găsită cu ID-ul: " + idEchipa);
        }
        echipaRepository.deleteById(idEchipa);
    }
}
