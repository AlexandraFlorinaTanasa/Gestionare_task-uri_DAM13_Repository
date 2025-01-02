package test;

import org.gestionare_taskuri.repository.BacklogRepository;
import org.gestionare_taskuri.servicii.BacklogService;
import org.gestionare_taskuri.task.Backlog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BacklogServiceTest {

    @Mock
    private BacklogRepository backlogRepository;

    @InjectMocks
    private BacklogService backlogService;

    private Backlog backlog;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        backlog = new Backlog();
        backlog.setId(1);
        backlog.setNume("Test Backlog");
        backlog.setDescriere("Descriere backlog");
        backlog.setProprietar("John Doe");
    }

    @Test
    public void testGetAllBacklogs() {
        // Simulăm comportamentul repository-ului
        when(backlogRepository.findAll()).thenReturn(Arrays.asList(backlog));

        // Apelăm metoda din serviciu
        var backlogs = backlogService.getAllBacklogs();

        // Verificăm rezultatele
        assertNotNull(backlogs);
        assertEquals(1, backlogs.size());
        assertEquals("Test Backlog", backlogs.get(0).getNume());

        // Verificăm că repository-ul a fost apelat o dată
        verify(backlogRepository, times(1)).findAll();
    }

    @Test
    public void testGetBacklogById() {
        // Simulăm comportamentul repository-ului
        when(backlogRepository.findById(1)).thenReturn(Optional.of(backlog));

        // Apelăm metoda din serviciu
        Backlog foundBacklog = backlogService.getBacklogById(1);

        // Verificăm rezultatele
        assertNotNull(foundBacklog);
        assertEquals("Test Backlog", foundBacklog.getNume());

        // Verificăm că repository-ul a fost apelat o dată
        verify(backlogRepository, times(1)).findById(1);
    }

    @Test
    public void testAddBacklog() {
        // Simulăm comportamentul repository-ului
        when(backlogRepository.save(backlog)).thenReturn(backlog);

        // Apelăm metoda din serviciu
        Backlog savedBacklog = backlogService.addBacklog(backlog);

        // Verificăm rezultatele
        assertNotNull(savedBacklog);
        assertEquals("Test Backlog", savedBacklog.getNume());

        // Verificăm că repository-ul a fost apelat o dată
        verify(backlogRepository, times(1)).save(backlog);
    }

    @Test
    public void testUpdateBacklog() {
        // Simulăm comportamentul repository-ului
        when(backlogRepository.findById(1)).thenReturn(Optional.of(backlog));
        when(backlogRepository.save(backlog)).thenReturn(backlog);

        // Apelăm metoda din serviciu
        backlog.setDescriere("Descriere actualizată");
        Backlog updatedBacklog = backlogService.updateBacklog(1, backlog);

        // Verificăm rezultatele
        assertNotNull(updatedBacklog);
        assertEquals("Descriere actualizată", updatedBacklog.getDescriere());

        // Verificăm că repository-ul a fost apelat
        verify(backlogRepository, times(1)).findById(1);
        verify(backlogRepository, times(1)).save(backlog);
    }

    @Test
    public void testDeleteBacklog() {
        // Simulăm comportamentul repository-ului
        when(backlogRepository.existsById(1)).thenReturn(true);

        // Apelăm metoda din serviciu
        boolean isDeleted = backlogService.deleteBacklog(1);

        // Verificăm rezultatele
        assertTrue(isDeleted);

        // Verificăm că repository-ul a fost apelat
        verify(backlogRepository, times(1)).existsById(1);
        verify(backlogRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteBacklogNotFound() {
        // Simulăm comportamentul repository-ului
        when(backlogRepository.existsById(1)).thenReturn(false);

        // Verificăm că metoda aruncă excepția corectă
        RuntimeException exception = assertThrows(RuntimeException.class, () -> backlogService.deleteBacklog(1));
        assertEquals("Backlog-ul cu ID-ul 1 nu a fost găsit.", exception.getMessage());

        // Verificăm că repository-ul a fost apelat
        verify(backlogRepository, times(1)).existsById(1);
        verify(backlogRepository, never()).deleteById(1);
    }
}
