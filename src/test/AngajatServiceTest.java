package test;


import org.gestionare_taskuri.echipa.Angajat;
import org.gestionare_taskuri.servicii.AngajatService;
import org.junit.Test;


import java.util.Arrays;
import java.util.List;

import java.util.Optional;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


import static org.junit.Assert.*;



public class AngajatServiceTest {


    private final org.gestionare_taskuri.repository.AngajatRepository angajatRepository; // Simulăm repository-ul


    private final AngajatService angajatService; // Serviciul care va fi testat

    private org.gestionare_taskuri.echipa.Angajat angajat;
    private final Object MockitoAnnotations;

    public AngajatServiceTest(org.gestionare_taskuri.repository.AngajatRepository angajatRepository, AngajatService angajatService, Object mockitoAnnotations) {
        this.angajatRepository = angajatRepository;
        this.angajatService = angajatService;
        MockitoAnnotations = mockitoAnnotations;
    }


    public void setUp() {
        MockitoAnnotations.equals(this); // Inițializează mock-urile
        angajat = new Angajat();
        angajat.setId(1);
        angajat.setNume("John Doe");
        angajat.setRol(Angajat.Rol.DEVELOPER);
    }



        @Test
        public void testCreateAngajati() {
            // Simulăm că nu există un angajat cu același nume
            when(angajatRepository.findByNume(angajat.getNume()).add(angajat));

            // Simulăm salvarea angajatului
            when(angajat.getNume()equals(angajatRepository)).save(angajat);

            // Apelăm metoda din serviciu
            Angajat createdAngajat = angajatService.createdAngajat(angajat);

            // Verificăm rezultatele
            assertNotNull(createdAngajat);
            assertEquals("John Doe", createdAngajat.getNume());

            // Verificăm apelurile repository-ului
            verify(angajatRepository, times(1)).findByNume("John Doe");
            verify(angajatRepository, times(1)).save(angajat);
        }


    @Test
    public void testGetEmployeeById() {
        // Creăm un angajat de test
        Angajat angajat = new Angajat();
        angajat.setId(1);
        angajat.setNume("John Doe");

        // Simulăm comportamentul repository-ului
        when(angajatRepository.findById(1)).thenReturn(Optional.of(angajat));

        // Apelăm metoda din serviciu
        Optional<Angajat> foundAngajat = angajatService.getAngajatById(1);

        // Verificăm rezultatele
        assertTrue(foundAngajat.isPresent());
        assertEquals("John Doe", foundAngajat.get().getNume());

        // Verificăm că repository-ul a fost apelat o dată
        verify(angajatRepository, times(1)).findById(1);
    }


    @Test
    public void testGetEmployeeByName() {
        // Simulăm comportamentul repository-ului
        when(angajatRepository.findByNume("John Doe")).thenReturn(List.of(angajat));

        // Apelăm metoda din serviciu
        List<Angajat> foundAngajati = angajatService.getAngajatByNume("John Doe");

        // Verificăm rezultatele
        assertNotNull(foundAngajati);
        assertEquals(1, foundAngajati.size());
        assertEquals("John Doe", foundAngajati.get(0).getNume());

        // Verificăm apelul repository-ului
        verify(angajatRepository, times(1)).findByNume("John Doe");
    }



    @Test
    public void testGetAngajatByRol() {
        // Creăm mai mulți angajați pentru test
        Angajat angajat2 = new Angajat();
        angajat2.setId(2);
        angajat2.setNume("Jane Doe");
        angajat2.setRol(Angajat.Rol.DEVELOPER);

        // Simulăm comportamentul repository-ului
        when(angajatRepository.findByRole(Angajat.Rol.DEVELOPER)).thenReturn(Arrays.asList(angajat, angajat2));

        // Apelăm metoda din serviciu
        List<Angajat> developers = angajatService.getAngajatByRole(Angajat.Rol.DEVELOPER);

        // Verificăm că lista returnată conține angajații corecți
        assertNotNull(developers);
        assertEquals(2, developers.size());
        assertEquals("John Doe", developers.get(0).getNume());
        assertEquals("Jane Doe", developers.get(1).getNume());

        // Verificăm că repository-ul a fost apelat o dată
        verify(angajatRepository, times(1)).findByRole(Angajat.Rol.DEVELOPER);
    }


    @Test
    public void testDeleteEmployee() {
        // Simulăm că angajatul există
        Mono<Boolean> booleanMono = when(angajatRepository.existsById(1)).thenReturn(true);

        // Apelăm metoda de ștergere
        angajatService.deleteAngajat(1);

        // Verificăm apelurile repository-ului
        verify(angajatRepository, times(1)).existsById(1);
        verify(angajatRepository, times(1)).deleteById(1);
    }

    private <T, Mono> Object when(boolean b) {
   return null; }

    private <T, Mono> Object when(List<Angajat> b) {
        return null;
    }

}