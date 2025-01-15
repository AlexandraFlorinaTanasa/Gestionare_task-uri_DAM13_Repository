package test;
import org.gestionare_taskuri.echipa.Echipa;
import org.gestionare_taskuri.repository.EchipaRepository;
import org.gestionare_taskuri.servicii.EchipaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EchipaServiceTest {

    @Mock
    private EchipaRepository echipaRepository;

    @InjectMocks
    private EchipaService echipaService;

    private Echipa echipa;

    @BeforeEach
    public void setUp() {
        echipa = new Echipa();
        echipa.setIdEchipa(1);
        echipa.setSpecializare(Echipa.Specializare.BACKEND);
    }



    @Test
    public void testGetEchipaById_Success() {
        // Pregătim comportamentul repository-ului
        when(echipaRepository.findById(1)).thenReturn(List.of(echipa));

        // Apelăm metoda de testat
        Echipa foundEchipa = echipaService.getEchipaById(1);

        // Validăm rezultatul
        assertNotNull(foundEchipa);


        // Verificăm apelurile la repository
        verify(echipaRepository, times(1)).findById(1);
    }



    @Test
    public void testDeleteEchipa_Success() {
        // Pregătim comportamentul repository-ului
        when(echipaRepository.existsById(1)).thenReturn(true);

        // Apelăm metoda de testat
        echipaService.deleteEchipa(1);

        // Verificăm apelurile la repository
        verify(echipaRepository, times(1)).existsById(1);
        verify(echipaRepository, times(1)).deleteById(1);
    }


}
