package org.gestionare_taskuri.test;

import echipa.Angajat;
import org.example.gestionare_taskuri.AngajatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicii.AngajatService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AngajatServiceTest {


    private AngajatRepository angajatRepository; // Simulăm repository-ul


    private AngajatService angajatService; // Serviciul care va fi testat

    private Angajat angajat;

    @BeforeEach
    public void setUp() {
        // Creăm un obiect Employee pentru test
        angajat = new Angajat();
        angajat.setId(1);
        angajat.setNume("John Doe");
        angajat.setRol(Angajat.Rol.DEVELOPER);
    }

    @Test
    public void testCreateEmployee() {
        // Simulăm comportamentul repository-ului
        when(angajatRepository.save(angajat)).thenReturn(angajat);

        // Apelăm metoda din serviciu
        Angajat createdAngajat = angajatService.createAngajat(angajat);

        // Verificăm dacă obiectul returnat este corect
        assertNotNull(createdAngajat);
        assertEquals("John Doe", createdAngajat.getNume());
        assertEquals(Angajat.Rol.DEVELOPER, createdAngajat.getRol());

        // Verificăm că repository-ul a fost apelat o dată
        verify(angajatRepository, times(1)).save(angajat);
    }

    @Test
    public void testGetEmployeeById() {
        // Simulăm comportamentul repository-ului
        when(angajatRepository.findById(1)).thenReturn(Optional.of(angajat));

        // Apelăm metoda din serviciu
        Optional<Angajat> foundAngajat = angajatService.getAngajatById(1);

        // Verificăm că employee-ul a fost găsit și datele sunt corecte
        assertTrue(foundAngajat.isPresent());
        assertEquals("John Doe", foundAngajat.get().getNume());

        // Verificăm că repository-ul a fost apelat o dată
        verify(angajatRepository, times(1)).findById(1);
    }

    @Test
    public void testGetEmployeeByName() {
        // Simulăm comportamentul repository-ului
        when(angajatRepository.findByNume("John Doe")).thenReturn(Optional.of(angajat));

        // Apelăm metoda din serviciu
        Optional<Angajat> foundAngajat = angajatService.getAngajatByNume("John Doe");

        // Verificăm că employee-ul a fost găsit

        assertTrue(foundAngajat.isPresent());
        assertEquals("John Doe", foundAngajat.get().getNume());

        // Verificăm că repository-ul a fost apelat o dată
        verify(angajatRepository, times(1)).findByNume("John Doe");
    }

    @Test
    public void testGetAngajatByRol() {
        // Creăm mai mulți angajați pentru test
        Angajat angajat2 = new Angajat();
        angajat2.setId(2);
        angajat2.setNume("Jane Doe");
        angajat2.setRol(Angajat.Rol.DEVELOPER);

        when(angajatRepository.findByRol(String.valueOf(Angajat.Rol.DEVELOPER))).thenReturn(List.of(angajat, angajat2));

        // Apelăm metoda din serviciu
        List<Angajat> developers = angajatService.getAngajatByRol(Angajat.Rol.DEVELOPER);

        // Verificăm că lista returnată conține angajații corecți
        assertEquals(2, developers.size());
        assertEquals("John Doe", developers.get(0).getNume());
        assertEquals("Jane Doe", developers.get(1).getNume());

        // Verificăm că repository-ul a fost apelat o dată
        verify(angajatRepository, times(1)).findByRol(String.valueOf(Angajat.Rol.DEVELOPER));
    }

    @Test
    public void testDeleteEmployee() {
        // Apelăm metoda de ștergere
        angajatService.deleteAngajat(1);

        // Verificăm că repository-ul a fost apelat pentru a șterge angajatul
        verify(angajatRepository, times(1)).deleteById(1);
    }
}