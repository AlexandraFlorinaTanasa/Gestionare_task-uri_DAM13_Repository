

package test;

import org.gestionare_taskuri.echipa.Angajat;
import org.gestionare_taskuri.servicii.AngajatService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AngajatServiceTest {

    private org.gestionare_taskuri.repository.AngajatRepository angajatRepository;
    private AngajatService angajatService;
    private Angajat angajat;

    @Before
    public void setUp() {
        angajatRepository = mock(org.gestionare_taskuri.repository.AngajatRepository.class);
        angajatService = new AngajatService(angajatRepository);
        angajat = new Angajat();
        angajat.setId(1);
        angajat.setNume("John Doe");
        angajat.setRol(Angajat.Rol.DEVELOPER);
    }

    @Test
    public void testCreateAngajati() {
        when(angajatRepository.findByNume(angajat.getNume())).thenReturn(List.of());
        when(angajatRepository.save(angajat)).thenReturn(angajat);

        Angajat createdAngajat = angajatService.createdAngajat(angajat);

        assertNotNull(createdAngajat);
        assertEquals("John Doe", createdAngajat.getNume());
        verify(angajatRepository, times(1)).findByNume("John Doe");
        verify(angajatRepository, times(1)).save(angajat);
    }

    @Test
    public void testGetEmployeeById() {
        when(angajatRepository.findById(1)).thenReturn(Optional.of(angajat));

        Optional<Angajat> foundAngajat = angajatService.getAngajatById(1);

        assertTrue(foundAngajat.isPresent());
        assertEquals("John Doe", foundAngajat.get().getNume());
        verify(angajatRepository, times(1)).findById(1);
    }

    @Test
    public void testGetEmployeeByName() {
        when(angajatRepository.findByNume("John Doe")).thenReturn(List.of(angajat));

        List<Angajat> foundAngajati = angajatService.getAngajatByNume("John Doe");

        assertNotNull(foundAngajati);
        assertEquals(1, foundAngajati.size());
        assertEquals("John Doe", foundAngajati.get(0).getNume());
        verify(angajatRepository, times(1)).findByNume("John Doe");
    }

    @Test
    public void testGetAngajatByRol() {
        Angajat angajat2 = new Angajat();
        angajat2.setId(2);
        angajat2.setNume("Jane Doe");
        angajat2.setRol(Angajat.Rol.DEVELOPER);

        when(angajatRepository.findByRole(Angajat.Rol.DEVELOPER)).thenReturn(List.of(angajat, angajat2));

        List<Angajat> developers = angajatService.getAngajatByRole(Angajat.Rol.DEVELOPER);

        assertNotNull(developers);
        assertEquals(2, developers.size());
        assertEquals("John Doe", developers.get(0).getNume());
        assertEquals("Jane Doe", developers.get(1).getNume());
        verify(angajatRepository, times(1)).findByRole(Angajat.Rol.DEVELOPER);
    }

    @Test
    public void testDeleteEmployee() {
        when(angajatRepository.existsById(1)).thenReturn(true);

        angajatService.deleteAngajat(1);

        verify(angajatRepository, times(1)).existsById(1);
        verify(angajatRepository, times(1)).deleteById(1);
    }
}
