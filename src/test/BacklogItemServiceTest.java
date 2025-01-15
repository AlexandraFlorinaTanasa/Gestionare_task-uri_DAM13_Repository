package test;
import org.gestionare_taskuri.repository.BacklogItemRepository;
import org.gestionare_taskuri.servicii.BacklogItemService;
import org.gestionare_taskuri.task.BacklogItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BacklogItemServiceTest {

    @Mock
    private BacklogItemRepository backlogItemRepository;

    @InjectMocks
    private BacklogItemService backlogItemService;

    private BacklogItem backlogItem;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        backlogItem = new BacklogItem();
        backlogItem.setId(1);
        backlogItem.setNume("Test Item");
        backlogItem.setDescriere("Descriere Test Item");
        backlogItem.setPrioritate("HIGH");
        backlogItem.setStatus("TO_DO");
    }

    @Test
    public void testGetAllBacklogItems() {
        // Simulăm comportamentul repository-ului
        when(backlogItemRepository.findAll()).thenReturn(List.of(backlogItem));

        // Apelăm metoda din serviciu
        var backlogItems = backlogItemService.getAllBacklogItems();

        // Verificăm rezultatele
        assertNotNull(backlogItems);
        assertEquals(1, backlogItems.size());
        assertEquals("Test Item", backlogItems.get(0).getNume());

        // Verificăm că repository-ul a fost apelat o dată
        verify(backlogItemRepository, times(1)).findAll();
    }

    @Test
    public void testGetBacklogItemById() {
        // Simulăm comportamentul repository-ului
        when(backlogItemRepository.findById(1L)).thenReturn(Optional.of(backlogItem));

        // Apelăm metoda din serviciu
        BacklogItem foundItem = backlogItemService.getBacklogItemById(1);

        // Verificăm rezultatele
        assertNotNull(foundItem);
        assertEquals("Test Item", foundItem.getNume());

        // Verificăm că repository-ul a fost apelat o dată
        verify(backlogItemRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetBacklogItemByIdNotFound() {
        // Simulăm comportamentul repository-ului
        when(backlogItemRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificăm că metoda aruncă excepția corectă
        RuntimeException exception = assertThrows(RuntimeException.class, () -> backlogItemService.getBacklogItemById(1));
        assertEquals("BacklogItem not found with id: 1", exception.getMessage());

        // Verificăm că repository-ul a fost apelat o dată
        verify(backlogItemRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddBacklogItem() {
        // Simulăm comportamentul repository-ului
        when(backlogItemRepository.save(backlogItem)).thenReturn(backlogItem);

        // Apelăm metoda din serviciu
        BacklogItem savedItem = backlogItemService.addBacklogItem(backlogItem);

        // Verificăm rezultatele
        assertNotNull(savedItem);
        assertEquals("Test Item", savedItem.getNume());

        // Verificăm că repository-ul a fost apelat o dată
        verify(backlogItemRepository, times(1)).save(backlogItem);
    }

    @Test
    public void testUpdateBacklogItem() {
        // Simulăm comportamentul repository-ului
        when(backlogItemRepository.existsById(1L)).thenReturn(true);
        when(backlogItemRepository.save(backlogItem)).thenReturn(backlogItem);

        // Apelăm metoda din serviciu
        backlogItem.setDescriere("Descriere Actualizată");
        BacklogItem updatedItem = backlogItemService.updateBacklogItem(1, backlogItem);

        // Verificăm rezultatele
        assertNotNull(updatedItem);
        assertEquals("Descriere Actualizată", updatedItem.getDescriere());

        // Verificăm că repository-ul a fost apelat
        verify(backlogItemRepository, times(1)).existsById(1L);
        verify(backlogItemRepository, times(1)).save(backlogItem);
    }

    @Test
    public void testUpdateBacklogItemNotFound() {
        // Simulăm comportamentul repository-ului
        when(backlogItemRepository.existsById(1L)).thenReturn(false);

        // Verificăm că metoda aruncă excepția corectă
        RuntimeException exception = assertThrows(RuntimeException.class, () -> backlogItemService.updateBacklogItem(1, backlogItem));
        assertEquals("BacklogItem not found with id: 1", exception.getMessage());

        // Verificăm că repository-ul a fost apelat o dată
        verify(backlogItemRepository, times(1)).existsById(1L);
        verify(backlogItemRepository, never()).save(backlogItem);
    }

    @Test
    public void testDeleteBacklogItem() {
        // Simulăm comportamentul repository-ului
        when(backlogItemRepository.existsById(1L)).thenReturn(true);

        // Apelăm metoda din serviciu
        backlogItemService.deleteBacklogItem(1);

        // Verificăm că repository-ul a fost apelat
        verify(backlogItemRepository, times(1)).existsById(1L);
        verify(backlogItemRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteBacklogItemNotFound() {
        // Simulăm comportamentul repository-ului
        when(backlogItemRepository.existsById(1L)).thenReturn(false);

        // Verificăm că metoda aruncă excepția corectă
        RuntimeException exception = assertThrows(RuntimeException.class, () -> backlogItemService.deleteBacklogItem(1));
        assertEquals("BacklogItem not found with id: 1", exception.getMessage());

        // Verificăm că repository-ul a fost apelat o dată
        verify(backlogItemRepository, times(1)).existsById(1L);
        verify(backlogItemRepository, never()).deleteById(1L);
    }
}
