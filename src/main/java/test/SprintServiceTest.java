package test;





import org.gestionare_taskuri.repository.SprintRepository;

import org.gestionare_taskuri.servicii.SprintService;
import org.gestionare_taskuri.task.SprintPlanning;
import org.gestionare_taskuri.task.SprintPlanningStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SprintServiceTest {

    @Mock
    private SprintRepository sprintRepository;

    @InjectMocks
    private SprintService sprintService;

    private SprintPlanning sprintPlanning;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        sprintPlanning = new SprintPlanning();
        sprintPlanning.setCodSprint(1);
        sprintPlanning.setNumeSprint("Sprint 1");
        sprintPlanning.setObiectiv("Obiectiv Sprint");
        sprintPlanning.setSprintPlanningStatus(SprintPlanningStatus.READY);
    }

    @Test
    public void testCreateSprint() {
        // Arrange
        when(sprintRepository.save(sprintPlanning)).thenReturn(sprintPlanning);

        // Act
        SprintPlanning createdSprint = sprintService.createSprint(sprintPlanning);

        // Assert
        assertNotNull(createdSprint);
        assertEquals("Sprint 1", createdSprint.getNumeSprint());
        assertEquals("Obiectiv Sprint", createdSprint.getObiectiv());
        verify(sprintRepository, times(1)).save(sprintPlanning);
    }

    @Test
    public void testGetSprintById() {
        // Arrange
        when(sprintRepository.findById(1)).thenReturn(Optional.of(sprintPlanning));

        // Act
        SprintPlanning foundSprint = sprintService.getSprintById(1);

        // Assert
        assertNotNull(foundSprint);
        assertEquals("Sprint 1", foundSprint.getNumeSprint());
        verify(sprintRepository, times(1)).findById(1);
    }

    @Test
    public void testGetSprintByIdNotFound() {
        // Arrange
        when(sprintRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> sprintService.getSprintById(1));
        assertEquals("Sprint not found with id: 1", exception.getMessage());
        verify(sprintRepository, times(1)).findById(1);
    }

    @Test
    public void testDeleteSprint() {
        // Arrange
        when(sprintRepository.existsById(1)).thenReturn(true);

        // Act
        sprintService.removeSprint(1);

        // Assert
        verify(sprintRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteSprintNotFound() {
        // Arrange
        when(sprintRepository.existsById(1)).thenReturn(false);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> sprintService.removeSprint(1));
        assertEquals("Sprint not found with id: 1", exception.getMessage());
        verify(sprintRepository, times(1)).existsById(1);
        verify(sprintRepository, never()).deleteById(1);
    }
}
