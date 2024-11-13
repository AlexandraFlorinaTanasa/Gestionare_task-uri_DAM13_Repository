

    import org.example.gestionare_taskuri.IProjectDomainService;
    import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;


    /*
     * DOMAIN Service Tests
     * Strategy: Simple-Facade
     */

    //JUnit.5
    @SpringBootTest
    public class SimpleDomainService {
        private static Logger logger = Logger.getLogger(SimpleDomainService.class.getName());

        @Autowired
        private IProjectDomainService service;

        @Test
        public void test() {
            logger.info("Service implementation object :: " + service);
            logger.info("Service implementation class :: " + service.getClass().getName());
            //
            Integer caracteristicaCount = service.getCaracteristiciProiectCount(1);
            assertTrue(caracteristicaCount > 0, "Caracteristici not counting...");
            logger.info("Caracteristica count autowired xml:: " + caracteristicaCount);
        }
    }



