

import org.example.gestionare_taskuri.SprintRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import servicii.SprintService;
import task.SprintPlanning;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

    @SpringBootTest
    public class SprintServiceTest {


        private SprintRepository sprintRepository;
        private SprintService sprintService;

        private SprintPlanning sprintPlanning;


        public void setUp() {
            // Se creează un obiect de tip Sprint pentru teste
            sprintPlanning = new SprintPlanning();
            sprintPlanning.setCodSprint(1);
            sprintPlanning.setNumeSprint("Sprint 1");
         //   sprintPlanning.setDataInceput(2024-01-01);
          //  sprintPlanning.setDataSfarsit(2024-01-14);
        }

        @Test
        public void testCreateSprint() {
            // Simulăm comportamentul repository-ului
           when(sprintRepository.save(sprintPlanning)).thenReturn(sprintPlanning);

            // Apelăm metoda din serviciu
            SprintPlanning createdSprint = sprintService.createSprint(sprintPlanning);

            // Verificăm că metoda returnează corect entitatea creată
            assertNotNull(createdSprint);
            assertEquals("Sprint 1", createdSprint.getNumeSprint());


        }

        @Test
        public void testGetSprintById() {


            // Apelăm metoda din serviciu
            //Optional<SprintPlanning> foundSprint = sprintService.getSprintPlanningById(1);

            // Verificăm că sprint-ul este găsit și conține datele corecte
           // assertTrue(foundSprint.isPresent());
           // assertEquals("Sprint 1", foundSprint.get().getNumeSprint();
        }

        @Test
        public void testDeleteSprint() {
            // Apelăm metoda de ștergere
          //  sprintService.deleteSprint;

            // Verificăm că repository-ul a fost apelat o dată pentru a șterge sprint-ul
            verify(sprintRepository, times(1)).deleteById(1);
        }

        @Test
        public void testGetSprintByIdNotFound() {


            // Apelăm metoda de căutare
            //Optional<SprintPlanning> foundSprint = sprintService.getSprintPlanningById(1);

            // Verificăm că sprint-ul nu a fost găsit
            //assertFalse(foundSprint.isPresent());
        }
    }



