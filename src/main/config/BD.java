package org.gestionare_taskuri.config;

import org.gestionare_taskuri.echipa.Angajat;
import org.gestionare_taskuri.echipa.Echipa;
import org.gestionare_taskuri.repository.AngajatRepository;
import org.gestionare_taskuri.repository.EchipaRepository;
import org.gestionare_taskuri.repository.TaskRepository;
import org.gestionare_taskuri.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;



@Configuration
public class BD {
    @Autowired(required = false)
    private AngajatRepository angajatRepository;

    @Autowired(required = false)
    private TaskRepository taskRepository;

    @Autowired(required = false)
    private EchipaRepository echipaRepository;
    @Bean
    CommandLineRunner seedDatabase() {
        return args -> {
            // Adaugam un Task
            if (taskRepository.count() == 0) {
                Task task = new Task();
                task.setNume("Task nume");
                task.setDataInceput(LocalDate.ofEpochDay(25 /2025));
                task.setDataSfarsit(LocalDate.ofEpochDay(1 / 2 /2025));
                task.setDescriere("Test client description");
                task.setPrioritate(task.getPrioritate());
                taskRepository.save(task);
                System.out.println("Client inserat cu succes!");
            }

            // Inserăm o echipă
            if (EchipaRepository.count().equals(0)){
                Echipa echipa = new Echipa();
                echipa.setIdEchipa(1);
                echipaRepository.save(echipa);
                System.out.println("Echipa" + echipa+ "inserată cu succes!");
            }
            // Inserăm un angajat
            if (angajatRepository.count() == 0) {
                Angajat angajat = new Angajat();
                angajat.setNume("Jane Doe");
                angajat.setRol(Angajat.Rol.DEVELOPER);
                angajat.setSalariu(4500.0);
                angajat.setDisponibiil(true);
                angajatRepository.save(angajat);
                System.out.println("Angajat inserat cu succes!");
            }
        };
    }
}